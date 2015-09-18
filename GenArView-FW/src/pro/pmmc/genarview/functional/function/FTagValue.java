package pro.pmmc.genarview.functional.function;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.w3c.dom.NodeList;

import pro.pmmc.genarview.util.GraphUtil;
import pro.pmmc.genarview.util.XmlUtil;

public class FTagValue implements Function<Path, Stream<Node>> {

	private String tag;
	
	public FTagValue(String tag) {
		this.tag = tag;
	}

	@Override
	public Stream<Node> apply(Path path) {
		Transaction tx = GraphUtil.getGraphService().beginTx();
		NodeList nodes = (new XmlUtil()).getNodesByTag(path, tag);
		List<Node> lstNode = new ArrayList<Node>();
		for (int i = 0; i < nodes.getLength(); i++) {
			org.w3c.dom.Node xmlNode = nodes.item(i);
			Node node = GraphUtil.getGraphService().createNode();
			node.addLabel(DynamicLabel.label("Component"));
			node.setProperty("name", xmlNode.getTextContent());
			lstNode.add(node);
		}
		// TX MODIF
		tx.success();
		tx.close();
		return lstNode.stream();
	}
	
	
}
