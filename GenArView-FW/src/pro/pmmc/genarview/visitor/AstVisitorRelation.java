package pro.pmmc.genarview.visitor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.ArrayType;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import pro.pmmc.genarview.cache.CacheUtil;
import pro.pmmc.genarview.util.ConstantsFw;
import pro.pmmc.genarview.util.GraphUtil;

public class AstVisitorRelation extends ASTVisitor {

	private String packageName;

	private Map<String, String> mapImports;
	
	private Set<String> setImportsContainers;
	
	private Map<String, Set<String>> mapInheritance;
	
	private Map<String, Set<String>> mapRealization;
	
	private Map<String, Set<String>> mapAssociation;
	
	private Map<String, Set<String>> mapAggregation;
	
	private Map<String, Set<String>> mapDependency;
	
	public AstVisitorRelation() {
		mapImports = new HashMap<String, String>();
		setImportsContainers = new HashSet<String>();
		mapInheritance = new HashMap<String, Set<String>>();
		mapRealization = new HashMap<String, Set<String>>();
		mapAssociation = new HashMap<String, Set<String>>();
		setMapAggregation(new HashMap<String, Set<String>>());
		mapDependency = new HashMap<String, Set<String>>();
	}
	
	public boolean visit(PackageDeclaration node) {
		packageName = node.getName().toString();
		return true;
	}

	public boolean visit(ImportDeclaration node) {
		String name = node.getName().toString();
		if (node.toString().indexOf('*') != -1) {
			setImportsContainers.add(name + ".");
		} else {
			int pos = name.lastIndexOf('.');
			mapImports.put(name.substring(pos + 1), name);
		}
		
		return true;
	}

	
	public boolean visit(TypeDeclaration node) {
		String origin = getTypeFullValid(node.getName().toString());
		if (!node.isLocalTypeDeclaration() && origin != null) {
			String superType = (node.getSuperclassType() == null) ? null 
					: node.getSuperclassType().toString();
			superType = (superType == null) ? null : getTypeFullValid(superType);
			if (superType != null) {
				putInMap(origin, superType, mapInheritance);
			} else if (node.getSuperclassType() != null) {
				relateOriginImport(origin, node.getSuperclassType().toString(), ConstantsFw.N_REL_GENERALIZATION_IMPORTED);
			}
			List interfaces = node.superInterfaceTypes();
			for (Object interf : interfaces) {
				String interfaceType = interf.toString();
				interfaceType = getTypeFullValid(interfaceType);
				if (interfaceType != null) {
					putInMap(origin, interfaceType, mapRealization);
				} else {
					relateOriginImport(origin, interf.toString(), ConstantsFw.N_REL_REALIZATION_IMPORTED);
				}
			}
		}
		return true;
	}

	public boolean visit(FieldDeclaration node) {
		if (!(node.getParent() instanceof AnonymousClassDeclaration)) {
			TypeDeclaration td = (TypeDeclaration) node.getParent();
			String target = getTypeFullValid(node.getType().toString());
			String origin = getTypeFullValid(td.getName().toString());
			if (origin != null && target != null) {
				putInMap(origin, target, mapAssociation);
			} else if (origin != null && target == null) { // aggregation // MODIFIED origin == null
				if (node.getType() instanceof ArrayType) {
					ArrayType at = (ArrayType) node.getType();
					target = getTypeFullValid(at.getElementType().toString());
					if (target != null) {
						putInMap(origin, target, mapAssociation);
						putInMap(origin, target, mapAggregation);
					} else {
						relateOriginImport(origin, node.getType().toString(), ConstantsFw.N_REL_AGGREGATION_IMPORTED);
					} 
				} else if (node.getType() instanceof ParameterizedType) {
					// any class which parameterizes some software class
					ParameterizedType pt = (ParameterizedType) node.getType();
					for (Object paramClass : pt.typeArguments()) {
						target = getTypeFullValid(paramClass.toString());
						if (target != null) {
							putInMap(origin, target, mapAssociation);
							putInMap(origin, target, mapAggregation);
							break;
						}
					}
					if (target == null) {
						relateOriginImport(origin, node.getType().toString(), ConstantsFw.N_REL_AGGREGATION_IMPORTED);
					}
				} else {
					relateOriginImport(origin, node.getType().toString(), ConstantsFw.N_REL_ASSOCIATION_IMPORTED);
				}
			} 
		}
		return true;
	}

	/**
	 * 
	 * @param origin a valid full name of a type in the source
	 * @param target a full name to search in the imports
	 * @param relationName
	 */
	private void relateOriginImport(String origin, String target, String relationName) {
		String targetImport = getFullnameImport(target);
		if (targetImport != null) {
			Transaction tx = GraphUtil.getGraphService().beginTx();
			GraphUtil graphUtil = new GraphUtil();
			Node nTarget = graphUtil.querySingleByFullname(
					targetImport, ConstantsFw.N_TYPE_TYPE_IMPORTED);
			if (nTarget == null) {
				nTarget = GraphUtil.getGraphService().createNode();
				nTarget.addLabel(DynamicLabel.label(ConstantsFw.N_TYPE_TYPE_IMPORTED));
				nTarget.setProperty("fullName", targetImport);
			}
			Map<String, Node> mapTypes = CacheUtil.getCache().getMapNodes();
			Node nOrigin = mapTypes.get(origin);
			nOrigin.createRelationshipTo(nTarget, () -> relationName);
			tx.success();
			tx.close();
		}
	}

	public boolean visit(SingleVariableDeclaration node) {
		//System.out.println("SINGLE VAR " + node.getName());
		ASTNode parent = node.getParent();
		while (!(parent instanceof TypeDeclaration)) {
			parent = parent.getParent();
		}
		TypeDeclaration td = (TypeDeclaration) parent;
		
		String target = getTypeFullValid(node.getType().toString());
		String origin = getTypeFullValid(td.getName().toString());
		if (origin != null && target != null && !origin.equals(target)) {
			putInMap(origin, target, mapDependency);
		}
		return true;
	}

	public boolean visit(NormalAnnotation node) {
		ASTNode parent = node.getParent();
		while (!(parent instanceof TypeDeclaration) && !(parent instanceof CompilationUnit)) {
			parent = parent.getParent();
		}
		if (parent instanceof TypeDeclaration) {
			TypeDeclaration td = (TypeDeclaration) parent;
			String target = getTypeFullValid(node.getTypeName().toString());
			String origin = getTypeFullValid(td.getName().toString());
			if (origin != null && target != null && !origin.equals(target)) {
				putInMap(origin, target, mapDependency);
			}
		}
		return true;
	}

	public boolean visit(SingleMemberAnnotation node) {
		ASTNode parent = node.getParent();
		while (!(parent instanceof TypeDeclaration) && !(parent instanceof CompilationUnit)) {
			parent = parent.getParent();
		}
		if (parent instanceof TypeDeclaration) {
			TypeDeclaration td = (TypeDeclaration) parent;
			String target = getTypeFullValid(node.getTypeName().toString());
			String origin = getTypeFullValid(td.getName().toString());
			if (origin != null && target != null && !origin.equals(target)) {
				putInMap(origin, target, mapDependency);
			}
		}
		return true;
	}

	public boolean visit(MarkerAnnotation node) {
		ASTNode parent = node.getParent();
		while (!(parent instanceof TypeDeclaration) && !(parent instanceof CompilationUnit)) {
			parent = parent.getParent();
		}
		if (parent instanceof TypeDeclaration) {
			TypeDeclaration td = (TypeDeclaration) parent;
			String target = getTypeFullValid(node.getTypeName().toString());
			String origin = getTypeFullValid(td.getName().toString());
			if (origin != null && target != null && !origin.equals(target)) {
				putInMap(origin, target, mapDependency);
			}
		}
		return true;
	}

	public boolean visit(VariableDeclarationStatement node) {
		//System.out.println("VAR DECLAR " + node.getType());
		ASTNode parent = node.getParent();
		while (!(parent instanceof TypeDeclaration)) {
			parent = parent.getParent();
		}
		TypeDeclaration td = (TypeDeclaration) parent;
		String target = getTypeFullValid(node.getType().toString());
		String origin = getTypeFullValid(td.getName().toString());
		if (origin != null && target != null && !origin.equals(target)) {
			putInMap(origin, target, mapDependency);
		}
		return false; // do not continue to avoid usage info
	}

	public boolean visit(MethodDeclaration node){
		ASTNode parent = node.getParent();
		while (!(parent instanceof TypeDeclaration)) {
			parent = parent.getParent();
		}
		TypeDeclaration td = (TypeDeclaration) parent;
		if (node.getReturnType2() != null) { // Avoid constructors
			String target = getTypeFullValid(node.getReturnType2().toString());
			String origin = getTypeFullValid(td.getName().toString());
			if (origin != null && target != null && !origin.equals(target)) {
				putInMap(origin, target, mapDependency);
			}
		}
		return true;
	}

	public String getTypeFullValid(String typeName) {
		String fullName = null;
		Map<String, Node> mapTypes = CacheUtil.getCache().getMapNodes();
		if (typeName.indexOf(".") == -1) {
			fullName = mapImports.get(typeName);
			if (fullName == null) {
				for (String container : setImportsContainers) {
					if (mapTypes.get(container + typeName) != null) {
						fullName = container + typeName;
						break;// added
					}
				}
				fullName = (fullName == null) ? packageName + "." + typeName : fullName;
			}
		} else {
			fullName = typeName;
		}
		fullName = (mapTypes.get(fullName) != null) ? fullName : null;
		return fullName;
	}
	
	public String getFullnameImport(String typeName) {
		String fullName = null;
		if (typeName.indexOf(".") == -1) {
			fullName = mapImports.get(typeName);
		} else {
			fullName = typeName;
		}
		return fullName;
	}
	
	public void putInMap(String origin, String target, Map<String, Set<String>> map) {
		Set<String> set = map.get(origin);
		if (set == null) {
			set = new HashSet<String>();
			map.put(origin, set);
		}
		set.add(target);
	}

	public Map<String, Set<String>> getMapInheritance() {
		return mapInheritance;
	}

	public Map<String, Set<String>> getMapRealization() {
		return mapRealization;
	}

	public Map<String, Set<String>> getMapAssociation() {
		return mapAssociation;
	}

	public Map<String, Set<String>> getMapDependency() {
		return mapDependency;
	}

	public Map<String, Set<String>> getMapAggregation() {
		return mapAggregation;
	}

	public void setMapAggregation(Map<String, Set<String>> mapAggregation) {
		this.mapAggregation = mapAggregation;
	}
	
	
}
