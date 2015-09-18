package pro.pmmc.genarview.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;

public class GraphAggregation {

	private final String qMinLevel = "MATCH(p:Package) RETURN MIN(p.level) AS value";
	
	private final String qMaxLevel = "MATCH(p:Package) RETURN MAX(p.level) AS value";
	
	private final String sLevel = "MATCH(p:Package) WHERE p.level={level} SET p:L_";
	
	private final String qPackName = "MATCH(p:Package) RETURN DISTINCT(p.name) AS packName";
	
	private final String sName = "MATCH(p:Package) WHERE p.name={packName} SET p:N_";
	
	private final String qPackage = "MATCH(p:Package) RETURN p AS pack";
//	
	private final String cRelGeneralization = "MATCH(p1:Package)-[r1:CONTAIN]->(t1:Type)-[g:GENERALIZATION]->(t2:Type)<-[r2:CONTAIN]-(p2:Package) WITH p1 AS pack1, p2 AS pack2, COUNT(*) AS q WHERE pack1<>pack2 CREATE pack1-[r:AggGeneralization{n:q}]->pack2";
	
	private final String cRelRealization = "MATCH(p1:Package)-[r1:CONTAIN]->(t1:Type)-[g:REALIZATION]->(t2:Type)<-[r2:CONTAIN]-(p2:Package) WITH p1 AS pack1, p2 AS pack2, COUNT(*) AS q WHERE pack1<>pack2 CREATE pack1-[r:AggRealization{n:q}]->pack2";
	
	private final String cRelAssociation = "MATCH(p1:Package)-[r1:CONTAIN]->(t1:Type)-[g:ASSOCIATION]->(t2:Type)<-[r2:CONTAIN]-(p2:Package) WITH p1 AS pack1, p2 AS pack2, COUNT(*) AS q WHERE pack1<>pack2 CREATE pack1-[r:AggAssociation{n:q}]->pack2";
	
	private final String cRelDependency = "MATCH(p1:Package)-[r1:CONTAIN]->(t1:Type)-[g:DEPENDENCY]->(t2:Type)<-[r2:CONTAIN]-(p2:Package) WITH p1 AS pack1, p2 AS pack2, COUNT(*) AS q WHERE pack1<>pack2 CREATE pack1-[r:AggDependency{n:q}]->pack2";
	
	private final String cRelExtends = "MATCH(p1:Package)-[r:AggGeneralization|AggRealization]->(p2:Package) WITH p1 AS pack1, p2 AS pack2, r AS rel, SUM(r.n) AS s CREATE pack1-[nr:EXTENDS{n:s}]->pack2";
//	
	private final String cRelUses = "MATCH(p1:Package)-[r:AggAssociation|AggDependency]->(p2:Package) WITH p1 AS pack1, p2 AS pack2, r AS rel, SUM(r.n) AS s WHERE NOT((pack1:Package)-[:EXTENDS]->(pack2:Package)) WITH pack1 AS pk1, pack2 AS pk2, SUM(s) AS suma CREATE pk1-[nr:USES{n:suma}]->pk2";
//	
	private final String sInExt = "MATCH(p1:Package)<-[r:EXTENDS]-(p2:Package) WITH p1 AS pack1, SUM(r.n) AS s SET pack1.inExt=s";
	
	private final String sOutExt = "MATCH(p1:Package)-[r:EXTENDS]->(p2:Package) WITH p1 AS pack1, SUM(r.n) AS s SET pack1.outExt=s";
	
	private final String sInUses = "MATCH(p1:Package)<-[r:USES]-(p2:Package) WITH p1 AS pack1, SUM(r.n) AS s SET pack1.inUses=s";
	
	private final String sOutUses = "MATCH(p1:Package)-[r:USES]->(p2:Package) WITH p1 AS pack1, SUM(r.n) AS s SET pack1.outUses=s";

	private final String sInInt = "MATCH(p1:Package) SET p1.inInt=p1.inExt+p1.inUses";
	
	private final String sOutInt = "MATCH(p1:Package) SET p1.outInt=p1.outExt+p1.outUses";
	
	private final String sNullInExt = "MATCH(p1:Package) WHERE p1.inExt IS NULL SET p1.inExt=0";
	
	private final String sNullOutExt = "MATCH(p1:Package) WHERE p1.outExt IS NULL SET p1.outExt=0";
	
	private final String sNullInUses = "MATCH(p1:Package) WHERE p1.inUses IS NULL SET p1.inUses=0";
	
	private final String sNullOutUses = "MATCH(p1:Package) WHERE p1.outUses IS NULL SET p1.outUses=0";

	private final String sNullInInt = "MATCH(p1:Package) WHERE p1.inInt IS NULL SET p1.inInt=0 ";
	
	private final String sNullOutInt = "MATCH(p1:Package) WHERE p1.outInt IS NULL SET p1.outInt=0";
	
	private final String sInOutInt = "MATCH(p1:Package) SET p1.inoutInt=p1.inInt+p1.outInt";
	
	private GraphUtil graphUtil;
	
	public GraphAggregation() {
		graphUtil = new GraphUtil();
	}
	
	public void executeUpdates() {
		Transaction tx = GraphUtil.getGraphService().beginTx();
		labelLevel();
		labelName();
		labelParents();
		graphUtil.update(cRelGeneralization);
		graphUtil.update(cRelRealization);
		graphUtil.update(cRelAssociation);
		graphUtil.update(cRelDependency);
		graphUtil.update(cRelExtends);
		graphUtil.update(cRelUses);
		graphUtil.update(sInExt);
		graphUtil.update(sOutExt);
		graphUtil.update(sInUses);
		graphUtil.update(sOutUses);
		graphUtil.update(sInInt);
		graphUtil.update(sOutInt);
		graphUtil.update(sNullInExt);
		graphUtil.update(sNullOutExt);
		graphUtil.update(sNullInUses);
		graphUtil.update(sNullOutUses);
		graphUtil.update(sNullInInt);
		graphUtil.update(sNullOutInt);
		graphUtil.update(sInOutInt);
		tx.success();
		tx.close();
	}
	
	public void labelLevel() {
		int min = graphUtil.queryInteger(qMinLevel);
		int max = graphUtil.queryInteger(qMaxLevel);
		Map<String, Object> params = new HashMap<String, Object>();
		final String param = "level";
		for (int i = min; i <= max; i++) {
			params.put(param, i);
			graphUtil.update(sLevel + i, params);
		}
	}
	
	public void labelName() {
		List<String> lst = graphUtil.query(qPackName);
		Map<String, Object> params = new HashMap<String, Object>();
		final String param = "packName";
		for (String packName : lst) {
			params.put(param, packName);
			graphUtil.update(sName + packName, params);
		}
	}
	
	public void labelParents() {
		List<Node> lst = graphUtil.query(qPackage);
		for (Node node : lst) {
			labelParentsNode(node);;
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
