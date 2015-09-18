package pro.pmmc.genarview.visitor;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

import pro.pmmc.genarview.util.FileUtil;

public class AstVisitorSource extends ASTVisitor {

	private Map<String, Annotation> mapAnnotations;
	
	private String superclass;
	
	private Map<String, String> mapInterfaces;
	
	private Map<String, String> mapFieldTypes;

	public AstVisitorSource() {
		mapAnnotations = new HashMap<String, Annotation>();
		setMapInterfaces(new HashMap<String, String>());
		setMapFieldTypes(new HashMap<String, String>());
	}

	public boolean visit(ImportDeclaration node) {
		//System.out.println("Import ----- " + node.getName());
		return true;
	}

	public boolean visit(PackageDeclaration node) {
		return true;
	}

	public boolean visit(TypeDeclaration node) {
		//System.out.println(node.getName());
		List superinterf = node.superInterfaceTypes();
		if (superclass != null) {
			superclass = node.getSuperclassType().toString();
		}
		for (Object interf : superinterf) {
			mapInterfaces.put(interf.toString(), interf.toString());
		}
		if (node.isLocalTypeDeclaration()) {
			//System.out.print(" -- Clase local");
		}
		if (node.isMemberTypeDeclaration()) {
			//System.out.print(" --- Inner class");
		}
		//System.out.println();
		return true;
	}

	public boolean visit(FieldDeclaration node) {
		//TypeDeclaration td = (TypeDeclaration) node.getParent();
		//System.out.println("Var member ----- " + node.fragments().get(0)
		//		+ " of type " + node.getType()+ " en " + td.getName());
		mapFieldTypes.put(node.getType().toString(), node.getType().toString());
		return true;
	}

	public boolean visit(SingleVariableDeclaration node) {
		//System.out.println("Parametro ----- " + node.getName() + " of type "
			//	+ node.getType());
		/*ASTNode parent = node.getParent();
		while (!(parent instanceof TypeDeclaration)) {
			parent = parent.getParent();
		}
		TypeDeclaration td = (TypeDeclaration) parent;
		*/
		//System.out.println(" en " + td.getName());
		return true;
	}

	/**
	 * Visit annotations with attributes
	 */
	public boolean visit(NormalAnnotation node) {
		ASTNode parent = node.getParent();
		while (!(parent instanceof TypeDeclaration) && !(parent instanceof CompilationUnit)) {
			parent = parent.getParent();
		}
		if (parent instanceof TypeDeclaration) {
			TypeDeclaration td = (TypeDeclaration) parent;
			//System.out.println("Annot normal ----- " + node.getTypeName()
			//		+ " en " + td.getName());
			mapAnnotations.put(node.getTypeName().toString(), node);
		}
		return true;
	}

	/**
	 * Visit annotations with only a value, without attribute specification
	 */
	public boolean visit(SingleMemberAnnotation node) {
		ASTNode parent = node.getParent();
		while (!(parent instanceof TypeDeclaration) && !(parent instanceof CompilationUnit)) {
			parent = parent.getParent();
		}
		if (parent instanceof TypeDeclaration) {
			TypeDeclaration td = (TypeDeclaration) parent;
			mapAnnotations.put(node.getTypeName().toString(), node);
			//System.out.println("Annot single ----- " + node.getTypeName().toString()
				//	 + " en " + td.getName());
		}
		return true;
	}

	/**
	 * Visit annotations without attributes or values
	 */
	public boolean visit(MarkerAnnotation node) {
		//System.out.print("Annot marker ----- " + node.getTypeName());
		ASTNode parent = node.getParent();
		while (!(parent instanceof TypeDeclaration) && !(parent instanceof TypeDeclaration)) {
			parent = parent.getParent();
		}
		if (parent instanceof TypeDeclaration) {
			TypeDeclaration td = (TypeDeclaration) parent;
			mapAnnotations.put(node.getTypeName().toString(), node);
			//System.out.println("MarkerAnnotation en " + node.getTypeName().toString() + " - " + node.getParent().getClass());
		}
		return true;
	}

	public boolean visit(VariableDeclarationFragment node) {
		// SimpleName name = node.getName();
		// System.out.println("Declaration of '"+name + " of type " +
		// node.getParent() );

		// + " over " + node.getParent().getParent());

		return false; // do not continue to avoid usage info
	}

	public boolean visit(VariableDeclarationStatement node) {
		// SimpleName name = node.getName();
		String name = null;
		for (Iterator iter = node.fragments().iterator(); iter.hasNext();) {
			VariableDeclarationFragment fragment = (VariableDeclarationFragment) iter
					.next();
			name = fragment.toString();
		}
		ASTNode parent = node.getParent();
		while (!(parent instanceof TypeDeclaration)) {
			parent = parent.getParent();
		}
		TypeDeclaration td = (TypeDeclaration) parent;
		//System.out.println("Var local '" + name + " of type " + node.getType()
			//	+ " en " + td.getName());

		return false; // do not continue to avoid usage info
	}

	public boolean visit(MethodDeclaration node){
		ASTNode parent = node.getParent();
		while (!(parent instanceof TypeDeclaration)) {
			parent = parent.getParent();
		}
		TypeDeclaration td = (TypeDeclaration) parent;
		//System.out.println("method type " + node.getReturnType2()
		//		+ " en " + td.getName());
		return true;
	}

	public Map<String, Annotation> getMapAnnotations() {
		return mapAnnotations;
	}

	public static void main(String[] args) {
		 
	  Path path = FileSystems.getDefault().getPath("C:\\Users\\paul\\git\\tcc\\src\\edu\\fatec\\zl\\bean\\GraficosBean.java");
		//path = FileSystems.getDefault().getPath("C:\\Users\\paul\\git\\tcc\\src\\edu\\fatec\\zl\\service\\AtivoService.java");
		String source = FileUtil.getText(path);
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(source.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		AstVisitorSource visitor = new AstVisitorSource();
		cu.accept(visitor);
		Map<String, Annotation> map = visitor.getMapAnnotations();
		map.keySet().stream().forEach(System.out::println);
	}

	public String getSuperclass() {
		return superclass;
	}

	public void setSuperclass(String superclass) {
		this.superclass = superclass;
	}

	public Map<String, String> getMapInterfaces() {
		return mapInterfaces;
	}

	public void setMapInterfaces(Map<String, String> mapInterfaces) {
		this.mapInterfaces = mapInterfaces;
	}

	public Map<String, String> getMapFieldTypes() {
		return mapFieldTypes;
	}

	public void setMapFieldTypes(Map<String, String> mapFieldTypes) {
		this.mapFieldTypes = mapFieldTypes;
	}


}
