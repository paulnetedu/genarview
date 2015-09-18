package pro.pmmc.genarview.visitor;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.neo4j.graphdb.Node;

public abstract class AvNodeType extends ASTVisitor {

	protected Map<String, Node> mapNodeTypes;
	
	protected String packageName;

	protected String path;
	
	public AvNodeType() {
		mapNodeTypes = new HashMap<String, Node>();
	}
	
	public AvNodeType(String path) {
		super();
		this.path = path;
	}
	
	public abstract void processTypeName(String packName, String typeName, 
			boolean isInterface, boolean isNested);
	
	public abstract void doPostVisit();
	
	public boolean visit(PackageDeclaration node) {
		packageName = node.getName().toString();
		return true;
	}
	
	public boolean visit(TypeDeclaration node) {
		if (!node.isLocalTypeDeclaration() && !(node.getParent() instanceof EnumDeclaration)) {
			String packName = (packageName == null) ? "" : packageName;
			String name = "";
			boolean isInterface = node.isInterface();
			boolean isNested = false;
			if (node.isMemberTypeDeclaration() && !(node.getParent() instanceof AnonymousClassDeclaration)) {
				TypeDeclaration td = null;
				ASTNode astNode = null;
				astNode = node.getParent(); 
				name = "";
				while (astNode instanceof TypeDeclaration) {
					td = (TypeDeclaration) astNode;
					name = td.getName().toString() + "." + name;
					astNode = astNode.getParent();
				}
				isNested = true;
				//System.out.print("member ");
			}
			name = name + node.getName().toString();
			//System.out.println(" type:" + name);
			processTypeName(packName, name, isInterface, isNested);			
		} 
		
		return true;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Map<String, Node> getMapNodeTypes() {
		return mapNodeTypes;
	}

	public void setMapNodeTypes(Map<String, Node> container) {
		this.mapNodeTypes = container;
	}

	
}
