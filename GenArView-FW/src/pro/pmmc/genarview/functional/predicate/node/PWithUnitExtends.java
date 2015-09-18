package pro.pmmc.genarview.functional.predicate.node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import pro.pmmc.genarview.functional.predicate.PTypeHintPlain;
import pro.pmmc.genarview.util.ConstantsFw;
import pro.pmmc.genarview.util.GraphUtil;
import pro.pmmc.genarview.xml.xsd.dsl.TypeHintPlain;

public class PWithUnitExtends extends PTypeHintPlain<Node> {

	public PWithUnitExtends(TypeHintPlain hint) {
		super(hint);
	}

	@Override
	public boolean test(Node node) {
		Transaction tx = GraphUtil.getGraphService().beginTx();
		GraphUtil graphUtil = new GraphUtil();
		StringBuilder sb = new StringBuilder();
		sb.append("MATCH (t:Type)-[r:");
		sb.append(ConstantsFw.N_REL_GENERALIZATION);
		sb.append("]->(i:Type) WHERE i.fullName={fullName} RETURN t");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fullName", values.get("baseClassFullName"));
		List<Node> lst = graphUtil.query(sb.toString(), params);
		tx.success();
		tx.close();
		return lst != null && !lst.isEmpty();
	}
}
