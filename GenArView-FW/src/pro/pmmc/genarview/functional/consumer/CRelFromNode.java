package pro.pmmc.genarview.functional.consumer;

import java.util.List;
import java.util.function.Consumer;

import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import pro.pmmc.genarview.util.GraphUtil;

public class CRelFromNode implements Consumer<Node> {

	private List<String> lstComponentType;
	
	private List<Node> lstNodeFrom;
	
	private String relationshipName;
	
	public CRelFromNode(List<String> lstComponentType, List<Node> lstNodeFrom, String relationshipName) {
		this.lstComponentType = lstComponentType;
		this.lstNodeFrom = lstNodeFrom;
		this.relationshipName = relationshipName;
	}

	@Override
	public void accept(Node t) {
		Transaction tx = GraphUtil.getGraphService().beginTx();
		for (String label : lstComponentType) {
			t.addLabel(DynamicLabel.label(label));
		}
		for (Node from : lstNodeFrom) {
			from.createRelationshipTo(t, ()->relationshipName);
		}
		tx.success();
		tx.close();
	}
}
