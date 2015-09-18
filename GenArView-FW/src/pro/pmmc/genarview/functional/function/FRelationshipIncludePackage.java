package pro.pmmc.genarview.functional.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.kernel.impl.core.NodeProxy;

import pro.pmmc.genarview.util.ConstantsFw;
import pro.pmmc.genarview.util.GraphUtil;

public class FRelationshipIncludePackage implements Function<Node, List<Relationship>> {

	public FRelationshipIncludePackage() {
	}
	
	@Override
	public List<Relationship> apply(Node node) {
		//synchronized (this) {
		Transaction tx = GraphUtil.getGraphService().beginTx();
		List<Relationship> lstRelation = new ArrayList<Relationship>();
		String fullName = node.getProperty("fullName").toString();
		if (fullName.lastIndexOf(".") == -1) {
			tx.success();
			tx.close();
			return lstRelation;
		}
		GraphUtil graphUtil = new GraphUtil();
		List<Node> lstNode = graphUtil.queryByFullname(fullName.substring(0, fullName.lastIndexOf(".")),
				ConstantsFw.N_TYPE_PACKAGE);
		for (Node n : lstNode) {
			lstRelation.add(
					n.createRelationshipTo(node, () -> ConstantsFw.N_REL_INCLUDE));
		}
		tx.success();
		tx.close();
		return lstRelation;
		//}
	}

}
