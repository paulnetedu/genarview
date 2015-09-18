package pro.pmmc.genarview.query;

import java.util.List;

import org.neo4j.graphdb.Transaction;

import pro.pmmc.genarview.util.GraphUtil;

public class GenericQueryLabeler {

	protected String query;
	
	protected List<String> labels;
	
	protected List<String> nodesToLabel;
	
	protected List<String> relsToProp;
	
	public GenericQueryLabeler(String query, List<String> labels,
			List<String> nodesToLabel, List<String> relsToProp) {
		this.query = query;
		this.labels = labels;
		this.nodesToLabel = nodesToLabel;
		this.relsToProp = relsToProp;
	}

	public void labelQueryResults() {
		StringBuilder sb = new StringBuilder(query);
		sb.append(" SET ");
		String item = null;
		for (int i = 0; i < nodesToLabel.size(); i++) {
			for (String label : labels) {
				item = nodesToLabel.get(i);
				sb.append(item);
				sb.append(":");
				sb.append(label);
				sb.append(",");
			}
		}
		if (relsToProp != null) {
			for (int i = 0; i < relsToProp.size(); i++) {
				for (String label : labels) {
					item = relsToProp.get(i);
					sb.append(item);
					sb.append(".");
					sb.append(label);
					sb.append("=true,");
				}
			}
		}
		sb.setLength(sb.length() - 1);
		Transaction tx = GraphUtil.getGraphService().beginTx();
		GraphUtil.update(sb.toString());
		tx.success();
		tx.close();
		
	}
	
}
