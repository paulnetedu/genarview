package pro.pmmc.genarview.visitor;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ArrayInitializer;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;

import pro.pmmc.genarview.dsl.DslInformer;
import pro.pmmc.genarview.util.ConstantsFw;
import pro.pmmc.genarview.util.FileUtil;
import pro.pmmc.genarview.util.GraphUtil;

public class AstVisitorFull extends AvNodeTypeNew {

	private Map<String, String> mapImports;
	
	private Map<String, Set<String>> mapMethodInvocation;
	
	private Set<String> setImportsContainers;
	
	private Map<String, Set<String>> mapAnnotClass;
	
	private Map<String, Set<String>> mapAnnotField;
	
	private Map<String, Set<String>> mapAnnotMethod;
	
	private Map<String, Set<String>> mapAnnotParam;
	
	public AstVisitorFull() {
		mapImports = new HashMap<String, String>();
		setImportsContainers = new HashSet<String>();
		setMapAnnotClass(new HashMap<String, Set<String>>());
		setMapAnnotField(new HashMap<String, Set<String>>());
		setMapAnnotMethod(new HashMap<String, Set<String>>());
		setMapAnnotParam(new HashMap<String, Set<String>>());
		setMapMethodInvocation(new HashMap<String, Set<String>>());
	}
	
	public void addAnnotClass(String fullNameType, String annotName) {
		Set<String> set = mapAnnotClass.get(fullNameType);
		if (set == null) {
			set = new HashSet<String>();
			mapAnnotClass.put(fullNameType, set);
		}
		set.add(annotName);
	}
	
	public void addAnnotField(String fullNameType, String annotName) {
		Set<String> set = mapAnnotField.get(fullNameType);
		if (set == null) {
			set = new HashSet<String>();
			mapAnnotField.put(fullNameType, set);
		}
		set.add(annotName);
	}
	
	public void addAnnotMethod(String fullNameType, String annotName) {
		Set<String> set = mapAnnotMethod.get(fullNameType);
		if (set == null) {
			set = new HashSet<String>();
			mapAnnotMethod.put(fullNameType, set);
		}
		set.add(annotName);
	}
	
	public void addAnnotParam(String fullNameType, String annotName) {
		Set<String> set = mapAnnotParam.get(fullNameType);
		if (set == null) {
			set = new HashSet<String>();
			mapAnnotParam.put(fullNameType, set);
		}
		set.add(annotName);
	}
	
	public void addMethodInvocation(String fullNameType, String methodName) {
		Set<String> set = mapMethodInvocation.get(fullNameType);
		if (set == null) {
			set = new HashSet<String>();
			mapMethodInvocation.put(fullNameType, set);
		}
		set.add(methodName);
	}
	
	// XXX
	//public void processTypeName(String packName, String typeName, boolean isInterface) {	}
	
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

	@Override
	public boolean visit(MethodInvocation methodInvocation) {
		ASTNode parentType = methodInvocation.getParent();
		while (!(parentType instanceof TypeDeclaration) && !(parentType instanceof CompilationUnit)) {
			parentType = parentType.getParent();
		}
		if (DslInformer.getInstance().hasMethodCall(methodInvocation.getName().toString())) {
			String declaredTypeFullname = retrieveDeclaredTypeFullNameBySimpleName(((TypeDeclaration)parentType).getName().toString());
			if (declaredTypeFullname != null && (parentType instanceof TypeDeclaration)) {
				addMethodInvocation(declaredTypeFullname, methodInvocation.getName().toString());
			}
			//processMethod(methodInvocation.getName().toString(), parentType);
		}
		return super.visit(methodInvocation);

	}
	
	/**
	 * Visit annotations with attributes
	 */
	public boolean visit(NormalAnnotation node) {
		ASTNode parentType = node.getParent();
		while (!(parentType instanceof TypeDeclaration) && !(parentType instanceof CompilationUnit)) {
			parentType = parentType.getParent();
		}
		//	TypeDeclaration td = (TypeDeclaration) parent;
		//System.out.println("Annot normal ----- " + node.getTypeName());
		processAnnotation(node.getTypeName().toString(), node.getParent(), parentType);
		
		return true;
	}

	/**
	 * Visit annotations with only a value, without attribute specification
	 */
	public boolean visit(SingleMemberAnnotation node) {
		ASTNode parentType = node.getParent();
		while (!(parentType instanceof TypeDeclaration) && !(parentType instanceof CompilationUnit)) {
			parentType = parentType.getParent();
		}
		//System.out.println("Annot single ----- " + node.getTypeName().toString() + " en " + node.getParent().getClass());
		processAnnotation(node.getTypeName().toString(), node.getParent(), parentType);
		return true;
	}

	/**
	 * Visit annotations without attributes or values
	 */
	public boolean visit(MarkerAnnotation node) {
		//System.out.print("Annot marker ----- " + node.getTypeName());
		ASTNode parentType = node.getParent();
		while (!(parentType instanceof TypeDeclaration) && !(parentType instanceof CompilationUnit)) {
			parentType = parentType.getParent();
		}
		//System.out.println("MarkerAnnotation en " + node.getTypeName().toString() + " - " + node.getParent().getClass());
		processAnnotation(node.getTypeName().toString(), node.getParent(), parentType);
		return true;
	}

	private String retrieveAnnotationFullName(String simpleName) {
		String annotFullname = mapImports.get(simpleName);
		if (annotFullname == null) {
			GraphUtil graphUtil = new GraphUtil();
			List<Node> lst = graphUtil.queryByName(simpleName, ConstantsFw.N_TYPE_ANNOTATION);
			String tmpFullName = null;
			if (lst != null && !lst.isEmpty()) {
				for (Node node : lst) {
					tmpFullName = node.getProperty("fullName").toString();
					for (String importContainer : setImportsContainers) {
						if (tmpFullName.equals(importContainer + simpleName)) {
							mapImports.put(simpleName, tmpFullName);
							return tmpFullName;
						}
					}
					
				}
			}
		}
		return annotFullname;
	}
	
	private String retrieveDeclaredTypeFullNameBySimpleName(String simpleName) {
		for (String k : mapNodeTypes.keySet()) {
			if (k.endsWith(simpleName)) {
				return k;
			}
		}
		return null;
	}
	
	private void processAnnotation(String annotationName, ASTNode parentNode, ASTNode parentTypeNode) {
		//System.out.println("annotationName " + annotationName);
		
		if (annotationName.equals("SuppressWarnings") || annotationName.equals("Override")
				|| annotationName.equals("Deprecated")) {
			return;
		}
		if (parentTypeNode instanceof CompilationUnit) {
			//System.out.println("WARN:parentTypeNode is CompilationUnit for annotationName=" + annotationName
				//	+ " in " + path);
			return;
		}
		boolean isEmbedded = false;
		if (parentNode instanceof ArrayInitializer || (parentNode instanceof SingleMemberAnnotation)
				|| (parentNode instanceof MemberValuePair) ) {
			ASTNode parentType = parentNode.getParent();
			while ((parentType instanceof ArrayInitializer) || (parentType instanceof SingleMemberAnnotation)
			|| (parentType instanceof MemberValuePair) 
			|| (!(parentType instanceof TypeDeclaration) && !(parentType instanceof CompilationUnit))) {
				parentType = parentType.getParent();
			}
			if (!(parentType instanceof CompilationUnit)) {
				parentNode = parentType;
				isEmbedded = true;
			}
		}
		String declaredTypeFullname = retrieveDeclaredTypeFullNameBySimpleName(((TypeDeclaration)parentTypeNode).getName().toString());
		if (parentNode instanceof SingleVariableDeclaration) {
			//annotationLabel = ConstantsFw.N_TYPE_LEVEL_PARAM;
			addAnnotParam(declaredTypeFullname, annotationName);
		} else if (parentNode instanceof FieldDeclaration) {
			addAnnotField(declaredTypeFullname, annotationName);
		} else if (parentNode instanceof MethodDeclaration) {
			addAnnotMethod(declaredTypeFullname, annotationName);
		} else if (parentNode instanceof TypeDeclaration) {
			addAnnotClass(declaredTypeFullname, annotationName);
		} 
	}
	
	@Override
	public void doPostVisit() {
		GraphUtil graphUtil = new GraphUtil();
		Transaction tx = GraphUtil.getGraphService().beginTx();
		doPostAnnotation(mapAnnotClass, ConstantsFw.N_ANNOT_LEVEL_CLASS, graphUtil);
		doPostAnnotation(mapAnnotField, ConstantsFw.N_ANNOT_LEVEL_FIELD, graphUtil);
		doPostAnnotation(mapAnnotMethod, ConstantsFw.N_ANNOT_LEVEL_METHOD, graphUtil);
		doPostAnnotation(mapAnnotParam, ConstantsFw.N_TYPE_LEVEL_PARAM, graphUtil);
		Node nodeType = null;
		Node nodeMethod = null;
		for (String declaredTypeFullName : mapMethodInvocation.keySet()) {
			nodeType = graphUtil.querySingleByFullname(declaredTypeFullName, ConstantsFw.N_TYPE_TYPE);
			for (String methodName : mapMethodInvocation.get(declaredTypeFullName)) {
				nodeMethod = graphUtil.querySingleByName(methodName, ConstantsFw.N_TYPE_METHOD);
				nodeType.createRelationshipTo(nodeMethod, () -> ConstantsFw.R_TYPE_INVOKES_METHOD);
			}
		}
		tx.success();
		tx.close();
	}
	
	public void doPostAnnotation(Map<String, Set<String>> mapAnnot, String annotLevelLabel, GraphUtil graphUtil) {
		for (String declaredTypeFullName : mapAnnot.keySet()) {
			Node nodeType = graphUtil.querySingleByFullname(declaredTypeFullName, ConstantsFw.N_TYPE_TYPE);
			for (String annotFullName : mapAnnot.get(declaredTypeFullName)) {
				doPostAnnotation(nodeType, annotFullName, annotLevelLabel, graphUtil);
			}
		}
	}

	public void doPostAnnotation(Node nodeType, String annotationName, String annotationLevelLabel, GraphUtil graphUtil) {
		String annotFullName = null;
		annotFullName = (annotationName.indexOf('.') != -1) ? annotationName
				: retrieveAnnotationFullName(annotationName);
		if (annotFullName == null || nodeType == null) {
			//System.out.println("WARN: annotfullName=" + annotFullName + ",annotName=" + annotationName + ",nodeType=" + nodeType + ", in " + path);
			return;
		}
		Node nodeAnnot = graphUtil.querySingleByFullname(annotFullName, annotationLevelLabel);
		if (nodeAnnot == null) {
			nodeAnnot = GraphUtil.getGraphService().createNode();
			nodeAnnot.addLabel(DynamicLabel.label(ConstantsFw.N_TYPE_ANNOTATION));
			nodeAnnot.addLabel(DynamicLabel.label(annotationLevelLabel));
			nodeAnnot.setProperty("name", annotationName);
			nodeAnnot.setProperty("fullName", annotFullName);
		} 
		nodeType.createRelationshipTo(nodeAnnot, () -> ConstantsFw.R_TYPE_HAS_ANNOTATION);
	}
	
	public static void main(String[] args) {
		System.out.println(TypeDeclaration.class.getSuperclass().getSuperclass());
		Path path = FileSystems
				.getDefault()
				.getPath(
				//"C:\\Users\\paul\\git\\webservice\\src\\main\\java\\au\\edu\\mq\\cbms\\unicarbkb\\webservices\\rest\\MemberResourceRESTService.java");
				"C:\\Users\\paul\\git\\_jee\\singleton\\jboss-basic\\src\\org\\jboss\\as\\test\\integration\\jpa\\hibernate\\envers\\SoccerPlayer.java");
		// path =
		// FileSystems.getDefault().getPath("C:\\Users\\paul\\git\\tcc\\src\\edu\\fatec\\zl\\service\\AtivoService.java");
		String source = FileUtil.getText(path);
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(source.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		AstVisitorFull visitor = new AstVisitorFull();
		cu.accept(visitor);
		
	}

	public Map<String, Set<String>> getMapMethodInvocation() {
		return mapMethodInvocation;
	}

	public void setMapMethodInvocation(Map<String, Set<String>> mapMethodInvocation) {
		this.mapMethodInvocation = mapMethodInvocation;
	}

	public Map<String, Set<String>> getMapAnnotClass() {
		return mapAnnotClass;
	}

	public void setMapAnnotClass(Map<String, Set<String>> mapAnnotClass) {
		this.mapAnnotClass = mapAnnotClass;
	}

	public Map<String, Set<String>> getMapAnnotField() {
		return mapAnnotField;
	}

	public void setMapAnnotField(Map<String, Set<String>> mapAnnotField) {
		this.mapAnnotField = mapAnnotField;
	}

	public Map<String, Set<String>> getMapAnnotMethod() {
		return mapAnnotMethod;
	}

	public void setMapAnnotMethod(Map<String, Set<String>> mapAnnotMethod) {
		this.mapAnnotMethod = mapAnnotMethod;
	}

	public Map<String, Set<String>> getMapAnnotParam() {
		return mapAnnotParam;
	}

	public void setMapAnnotParam(Map<String, Set<String>> mapAnnotParam) {
		this.mapAnnotParam = mapAnnotParam;
	}
}
