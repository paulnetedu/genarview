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

import pro.pmmc.genarview.util.ConstantsFw;
import pro.pmmc.genarview.util.GraphUtil;
import pro.pmmc.genarview.util.XmlUtil;


public class FpnXPath implements Function<Path, Stream<Node>> {

	private String xpath;
	
	public FpnXPath(String xpath) {
		this.xpath = xpath;
	}

	@Override
	public Stream<Node> apply(Path path) {
		
		NodeList nodes = (new XmlUtil()).getNodesByXPath(path, xpath);
		List<Node> lstNodeFinal = new ArrayList<Node>();
		String fullName = null;
		GraphUtil graphUtil = new GraphUtil();
		for (int i = 0; i < nodes.getLength(); i++) {
			org.w3c.dom.Node xmlNode = nodes.item(i);
			fullName = xmlNode.getTextContent();
			List<Node> lstNode = graphUtil.queryByFullname(fullName, ConstantsFw.N_TYPE_TYPE);
			for (Node node : lstNode) {
				/*Transaction tx = GraphUtil.getGraphService().beginTx();
				node.addLabel(DynamicLabel.label(ConstantsFw.N_TYPE_COMP_BASE));
				node.addLabel(DynamicLabel.label(ConstantsFw.N_TYPE_COMP_MEMBER));
				node.setProperty("base", fullName);
				tx.success();
				tx.close();
				*/
				lstNodeFinal.add(node);
			}
		}
		return lstNodeFinal.stream();
	}
	
	
}
