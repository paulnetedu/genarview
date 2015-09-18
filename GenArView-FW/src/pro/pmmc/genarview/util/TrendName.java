package pro.pmmc.genarview.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.neo4j.cypher.ExecutionResult;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;

/**
 * MATCH (wn:WipName),(p:Package{name:wn.packageNameSimple}) WITH wn AS w, COUNT(DISTINCT(p)) AS c WHERE w.n>=0.9*c AND c>2 WITH w.word AS w, COUNT(*) AS c RETURN w ORDER BY w
 * @author paul
 *
 */
public class TrendName extends TrendGeneric {

	public TrendName(double percentage) {
		super(percentage);
	}

	@Override
	public void labelNodes() {
		final StringBuilder sb = new StringBuilder();
		sb.append("MATCH (wn:");
		sb.append(ConstantsFw.N_WIP_NAME);
		sb.append(")-[:");
		sb.append(ConstantsFw.R_WIP_NAME);
		sb.append("]->(p:");
		sb.append(ConstantsFw.N_TYPE_PACKAGE);
		sb.append(") WITH wn AS w, COUNT(DISTINCT(p)) AS c WHERE w.n>={percentage}*c AND c>2 SET w:");
		sb.append(ConstantsFw.N_TYPE_TREND);
		sb.append(",w.percentage=TOFLOAT(w.n)/c");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("percentage", percentage);
		update(sb.toString(), params);
		sb.setLength(0);
		sb.append("MATCH (wn:");
		sb.append(ConstantsFw.N_WIP_NAME);
		sb.append(":");
		sb.append(ConstantsFw.N_TYPE_TREND);
		sb.append(")-[:");
		sb.append(ConstantsFw.R_WIP_NAME);
		sb.append("]->(p:");
		sb.append(ConstantsFw.N_TYPE_PACKAGE);
		sb.append(")-[:");
		sb.append(ConstantsFw.N_REL_CONTAIN);
		sb.append("]->(t:");
		sb.append(ConstantsFw.N_TYPE_TYPE);
		sb.append(")<-[:");
		sb.append(ConstantsFw.R_WORD_INDEX_TYPE);
		sb.append("]-(wi:");
		sb.append(ConstantsFw.N_WORD_INDEX);
		sb.append("{word:wn.word,index:wn.index}) CREATE (t)<-[:");
		sb.append(ConstantsFw.R_WI_TREND_TYPE);
		sb.append("]-(wn) SET wn.trendType=\"");
		sb.append(ConstantsFw.N_WIP_NAME);
		sb.append("\"");
		update(sb.toString(), params);
	}	

}
