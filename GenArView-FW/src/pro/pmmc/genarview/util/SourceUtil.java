package pro.pmmc.genarview.util;

import java.nio.file.Path;
import java.util.Map;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.neo4j.graphdb.Node;

import pro.pmmc.genarview.visitor.AstVisitorRelation;

public class SourceUtil {

	public static AstVisitorRelation visitRelationsFromPath(Path path) {
		String source = FileUtil.getText(path);
		return visitRelations(source);
	}
	
	public static AstVisitorRelation visitRelations(String source) {
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(source.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT); 
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		AstVisitorRelation visitor = new AstVisitorRelation();
		//System.out.println("to visit " + path);
		cu.accept(visitor);
		return visitor;
	}
}
