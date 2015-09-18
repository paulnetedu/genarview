package pro.pmmc.genarview.functional.predicate.node;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import pro.pmmc.genarview.functional.predicate.PTypeHintPlain;
import pro.pmmc.genarview.util.GraphUtil;
import pro.pmmc.genarview.xml.xsd.dsl.TypeHintPlain;

public class PWithFile extends PTypeHintPlain<Node> {

	public PWithFile(TypeHintPlain hint) {
		super(hint);
	}

	@Override
	public boolean test(Node node) {
		Transaction tx = GraphUtil.getGraphService().beginTx();
		Path path = Paths.get((String) node.getProperty("path"));
		tx.success();
		tx.close();
		final String fileName = path.getFileName().toString();
		return fileName.matches(values.get("fileName"));
	}

	
}
