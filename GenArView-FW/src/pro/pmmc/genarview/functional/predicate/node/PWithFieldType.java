package pro.pmmc.genarview.functional.predicate.node;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import pro.pmmc.genarview.functional.predicate.PTypeHintPlain;
import pro.pmmc.genarview.util.FileUtil;
import pro.pmmc.genarview.util.GraphUtil;
import pro.pmmc.genarview.visitor.AstVisitorSource;
import pro.pmmc.genarview.xml.xsd.dsl.TypeHintPlain;

public class PWithFieldType extends PTypeHintPlain<Node> {

	public PWithFieldType(TypeHintPlain hint) {
		super(hint);
	}

	@Override
	public boolean test(Node node) {
		Transaction tx = GraphUtil.getGraphService().beginTx();
		Path path = Paths.get((String) node.getProperty("path"));
		tx.success();
		tx.close();
		//System.out.println("------" + path);
		String source = FileUtil.getText(path);
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(source.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		AstVisitorSource visitor = new AstVisitorSource();
		cu.accept(visitor);
		Map<String, String> map = visitor.getMapFieldTypes();
		final String fieldExp = values.get("fieldTypeName"); 
		for (String k : map.keySet()) {
			if (k.matches(fieldExp)) {
				return true;
			}
		}
		return false;
	}

}
