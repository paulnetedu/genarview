package pro.pmmc.genarview.graph;

import java.util.Iterator;
import java.util.List;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

import pro.pmmc.genarview.util.GraphUtil;

public class GrunPackageChildren implements Runnable {

	private GraphUtil graphUtil;
	
	public GrunPackageChildren() {
		graphUtil = new GraphUtil();
	}
	
	@Override
	public void run() {
		//System.out.println("RUNNING GRUN");
		final String qPackage = "MATCH(p:Package) RETURN p AS pack";
		List<Node> lst = graphUtil.query(qPackage);
		for (Node node : lst) {
			labelParentsNode(node);
		}
	}
	
	public void labelParentsNode(Node node) {
		Iterable<Relationship> iterable = node.getRelationships(() -> "INCLUDE", Direction.INCOMING);
		Iterator<Relationship> it = iterable.iterator();
		String labelP = "P_";
		String labelU = "U_";
		final String pFullname = "fullName";
		while (it.hasNext()) {
			Relationship r = it.next();
			Node parent = r.getStartNode();
			node.addLabel(DynamicLabel.label(labelP + parent.getProperty(pFullname)));
			node.addLabel(DynamicLabel.label(labelU + parent.getProperty(pFullname)));
			labelUppersNode(node, parent);
		}
	}
	
	public void labelUppersNode(Node node, Node upperCurrent) {
		Iterable<Relationship> iterable = upperCurrent.getRelationships(() -> "INCLUDE", Direction.INCOMING);
		Iterator<Relationship> it = iterable.iterator();
		String label = "U-";
		final String pFullname = "fullName";
		while (it.hasNext()) {
			Relationship r = it.next();
			Node upper = r.getStartNode();
			node.addLabel(DynamicLabel.label(label + upper.getProperty(pFullname)));
			labelUppersNode(node, upper);
		}
	}

}
