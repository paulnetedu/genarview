package pro.pmmc.genarview.functional.function;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;

import pro.pmmc.genarview.util.ConstantsFw;
import pro.pmmc.genarview.util.GraphUtil;

public class FRelationshipContainType implements Function<Node, Stream<Relationship>> {

	//private Map<String, String> mapTypes;
	
	public FRelationshipContainType() {//Map<String, String> mapTypes) {
		//this.mapTypes = mapTypes;
	}
	
	@Override
	public synchronized Stream<Relationship> apply(Node node) {
		List<Relationship> lstRelation = new ArrayList<Relationship>();
		try {
		Transaction tx = GraphUtil.getGraphService().beginTx();
		String fullName = node.getProperty("fullName").toString();
		String path = node.getProperty("path").toString();
		List<Node> lstNode = queryParent(fullName, path);
		if (lstNode != null && !lstNode.isEmpty()) {
			for (Node nContainer : lstNode) {
				lstRelation.add(nContainer.createRelationshipTo(node, () -> ConstantsFw.N_REL_CONTAIN));
				processNodes(nContainer, node);
			}
		}
		tx.success();
		tx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lstRelation.stream();
	}
	
	public void processNodes(Node nPackContainer, Node nTypeContained) {
		
	}

	public List<Node> queryParent(String fullName, String path) {
		if (fullName.lastIndexOf(".") == -1) {
			return null;
		}
		String packageName = fullName.substring(0, fullName.lastIndexOf("."));
		GraphUtil graphUtil = new GraphUtil();
		return graphUtil.queryByFullnamePath(packageName, Paths.get(path).getParent().toString(), 
				ConstantsFw.N_TYPE_PACKAGE);
		
	}

}
