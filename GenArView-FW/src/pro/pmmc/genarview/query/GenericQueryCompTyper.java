package pro.pmmc.genarview.query;

import java.util.List;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import pro.pmmc.genarview.util.ConstantsFw;
import pro.pmmc.genarview.util.GraphUtil;

/**
 * Labels the nodes with the name of the component type and then creates the relationship between
 * the ComponentType and the nodes labeled
 * @author paul
 *
 */
public class GenericQueryCompTyper {

	protected String query;
	
	protected List<String> nodesName;
	
	protected List<String> nodesComponentType;
	
	public GenericQueryCompTyper(String query, List<String> nodesComponentType,
			List<String> nodesName) {
		this.query = query;
		this.nodesComponentType = nodesComponentType;
		this.nodesName = nodesName;
	}

	public void relQueryResults() {
		StringBuilder sb = new StringBuilder();
		sb.append(query);
		sb.append(" SET ");
		String item = null;
		for (int i = 0; i < nodesName.size(); i++) {
			item = nodesName.get(i);
			sb.append(item);
			for (int j = 0; j < nodesComponentType.size(); j++) {
				sb.append(":");
				sb.append(nodesComponentType.get(j));
			}
		}
		sb.setLength(sb.length() - 1);
		Transaction tx = GraphUtil.getGraphService().beginTx();
		GraphUtil.update(sb.toString());
		sb.setLength(0);
		sb.append("MATCH (ct:");
		sb.append(ConstantsFw.N_TYPE_COMP_TYPE);
		sb.append("), (t:");
		String str1 = sb.toString();
		for (int j = 0; j < nodesComponentType.size(); j++) {
			sb.setLength(0);
			sb.append(str1);
			sb.append(nodesComponentType.get(j));
			sb.append(") WHERE ct.name=\"");
			sb.append(nodesComponentType.get(j));
			sb.append("\" CREATE t-[r:REALIZE]->ct");
			GraphUtil.update(sb.toString());
		}
		tx.success();
		tx.close();
	}
	
}
