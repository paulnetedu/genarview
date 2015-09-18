package pro.pmmc.genarview.functional.function;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;

import pro.pmmc.genarview.util.ConstantsFw;
import pro.pmmc.genarview.util.GraphUtil;

public class FnnRelationship implements Function<Node, Stream<Node>> {

	private String relationshipName;
	
	public FnnRelationship(String relationshipName) {
		this.relationshipName = relationshipName;
	}

	@Override
	public Stream<Node> apply(Node node) {
		List<Node> lstNode = new ArrayList<Node>();
		// TX MODIF
		Transaction tx = GraphUtil.getGraphService().beginTx();
		Iterable<Relationship> it = node.getRelationships(() -> relationshipName, Direction.OUTGOING);
		Iterator<Relationship> iter = it.iterator();
		while (iter.hasNext()) {
			Relationship r = iter.next();
			Node target = r.getEndNode();
			target.addLabel(DynamicLabel.label(ConstantsFw.N_TYPE_COMP_MEMBER));
			target.setProperty("base", node.getProperty("fullName").toString());
			lstNode.add(target);
		}
		tx.success();
		tx.close();
		return lstNode.stream();
	}

	
}
