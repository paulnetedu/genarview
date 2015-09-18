package pro.pmmc.genarview.functional.consumer;

import java.util.List;
import java.util.function.Consumer;

import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import pro.pmmc.genarview.util.GraphUtil;

public class CPropComponentTypical implements Consumer<Node> {

	private List<String> lstLabel;
	
	public CPropComponentTypical(List<String> lstLabel) {
		this.lstLabel = lstLabel;
	}

	@Override
	public void accept(Node t) {
		Transaction tx = GraphUtil.getGraphService().beginTx();
		for (String label : lstLabel) {
			t.setProperty("componentType", label);
		}
		t.setProperty("base", t.getProperty("fullName").toString());
		tx.success();
		tx.close();
	}

}
