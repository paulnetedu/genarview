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

/**
 * No es un Fpn tipico: no se etiqueta despues, éste etiqueta de todas formas los componentes base y miembro,
 * ello debido a que los componentes base y miembros estan en un mismo archivo y son relacionados desde estas definiciones
 * @author paul
 *
 */
public class FpnXPathMember implements Function<Path, Stream<Node>> {

	/**
	 * eg /faces-config/managed-bean
	 */
	private String xpath;
	
	/**
	 * eg ejb-class
	 */
	private String subxpathBase;
	
	/**
	 * eg ejb-local
	 */
	private List<String> subxpathMembers;
	
	public FpnXPathMember(String xpath, String subxpathBase, ArrayList<String> subxpathMembers) {
		this.xpath = xpath;
		this.subxpathBase = subxpathBase;
		this.subxpathMembers = subxpathMembers;
	}

	@Override
	public Stream<Node> apply(Path path) {
		
		NodeList nodes = (new XmlUtil()).getNodesByXPath(path, xpath);
		List<Node> lstNodeFinal = new ArrayList<Node>();
		String fullName = null;
		GraphUtil graphUtil = new GraphUtil();
		// TX MODIF
		Transaction tx = GraphUtil.getGraphService().beginTx();
		for (int i = 0; i < nodes.getLength(); i++) {
			org.w3c.dom.Node xmlNode = nodes.item(i);
			NodeList childNodes = xmlNode.getChildNodes();
			org.w3c.dom.Node child = null;
			// FIXME query subxpaths
			fullName = null;
			for (int j = 0; j < childNodes.getLength(); j++) {
				child = childNodes.item(j);
				if (child.getNodeName().equals(subxpathBase)) {
					fullName = child.getTextContent();
					List<Node> lstNode = graphUtil.queryByFullname(fullName, ConstantsFw.N_TYPE_TYPE);
					for (Node node : lstNode) {
						node.addLabel(DynamicLabel.label(ConstantsFw.N_TYPE_COMP_BASE));
						//node.addLabel(DynamicLabel.label(ConstantsFw.N_TYPE_COMP_MEMBER));
						node.setProperty("base", fullName);
						lstNodeFinal.add(node);
					}
					break;
				}
			}
			for (int j = 0; j < childNodes.getLength(); j++) {
				child = childNodes.item(j);
				for (int j2 = 0; j2 < subxpathMembers.size(); j2++) {
					if (child.getNodeName().equals(subxpathMembers.get(j2))) {
						fullName = child.getTextContent();
						List<Node> lstNode = graphUtil.queryByFullname(fullName, ConstantsFw.N_TYPE_TYPE);
						for (Node node : lstNode) {
							node.addLabel(DynamicLabel.label(ConstantsFw.N_TYPE_COMP_MEMBER));
							node.setProperty("base", fullName);
							lstNodeFinal.add(node);
						}
					}
				}
			}
			tx.success();
			tx.close();
		}
		return lstNodeFinal.stream();
	}
	
	
}
