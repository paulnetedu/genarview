package pro.pmmc.genarview.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.neo4j.graphdb.Transaction;

import pro.pmmc.genarview.util.ClassUtil;
import pro.pmmc.genarview.util.GraphUtil;
import pro.pmmc.genarview.xml.xsd.dsl.TypeGLabel;
import pro.pmmc.genarview.xml.xsd.dsl.TypeGLabelField;
import pro.pmmc.genarview.xml.xsd.dsl.TypeGraph;

public class GraphExecutor {

	private TypeGraph typeGraph;
	
	private GraphUtil graphUtil;

	public GraphExecutor(TypeGraph typeGraph) {
		this.typeGraph = typeGraph;
		graphUtil = new GraphUtil();
	}
	
	public void execute() {
		Transaction tx = GraphUtil.getGraphService().beginTx();
		executeLabel();
		executeUpdate();
		executeRun();
		tx.success();
		tx.close();
	}

	private void executeRun() {
		if (typeGraph.getGrun() != null && !typeGraph.getGrun().isEmpty()) {
			Runnable r = null;
			for (String grun : typeGraph.getGrun()) {
				r = (Runnable) ClassUtil.createObject(grun, null);
				//(new Thread(r)).start();
				r.run();
			}
		}
	}

	private void executeUpdate() {
		if (typeGraph.getGupdate() != null && !typeGraph.getGupdate().isEmpty()) {
			for (String gupdate : typeGraph.getGupdate()) {
				graphUtil.update(gupdate);
			}
		}
	}

	private void executeLabel() {
		if (typeGraph != null && typeGraph.getGlabel() != null && !typeGraph.getGlabel().isEmpty()) {
			for (TypeGLabel glabel : typeGraph.getGlabel()) {
				if (glabel.getField() != null && !glabel.getField().isEmpty()) {
					for (TypeGLabelField gfield : glabel.getField()) {
						executeLabelField(glabel.getFor() ,gfield);
					}
				}
			}
		}
	}
	
	private void executeLabelField(String forNode, TypeGLabelField gfield) {
		final String replace = "@@@@@"; 
		final StringBuilder qAll = new StringBuilder("MATCH(p:");
		qAll.append(forNode);
		qAll.append(") RETURN DISTINCT(p.");
		qAll.append(gfield.getValue());
		qAll.append(") AS field");
		List<String> lst = graphUtil.query(qAll.toString());
		final StringBuilder qEach = new StringBuilder("MATCH(p:");
		qEach.append(forNode);
		qEach.append(") WHERE p.");
		qEach.append(gfield.getValue());
		qEach.append("={field} SET p:");
		if (gfield.getPreffix() != null) {
			qEach.append(gfield.getPreffix());
		}
		qEach.append(replace);
		if (gfield.getSuffix() != null) {
			qEach.append(gfield.getSuffix());
		}
		String uquery = qEach.toString();
		Map<String, Object> params = new HashMap<String, Object>();
		final String param = "field";
		for (Object fieldValue : lst) {
			params.put(param, fieldValue);
			graphUtil.update(uquery.replace(replace, fieldValue.toString()), params);
		}
	}
}
