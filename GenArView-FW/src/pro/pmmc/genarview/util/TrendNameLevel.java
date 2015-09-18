package pro.pmmc.genarview.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.neo4j.cypher.ExecutionResult;
import org.neo4j.graphalgo.impl.util.MatrixUtil.DoubleVector;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;

/**
 * MATCH (wilp:WordIndexLevelPackage)-[r1:W_WILPL]->(l:Level)-[r2:LEVEL]->(p1:Package), (wilp:WordIndexLevelPackage)-[r3:W_WILPP]->(p2:Package) WITH wilp.word AS word, wilp.level AS level, wilp.index AS index, COUNT(DISTINCT p1) AS ptotal, COUNT(DISTINCT p2) AS pcur WHERE pcur >= 0.9*ptotal RETURN word, level, index, ptotal, pcur
 * @author paul
 *
 */
public class TrendNameLevel extends TrendGeneric {

	public TrendNameLevel(double percentage) {
		super(percentage);
	}

	@Override
	public void labelNodes() {
		final StringBuilder sb = new StringBuilder();
		sb.append("MATCH (wn:");
		sb.append(ConstantsFw.N_WIP_NAME_LEVEL);
		sb.append(")-[:");
		sb.append(ConstantsFw.R_WIP_NAME_LEVEL);
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
		sb.append(ConstantsFw.N_WIP_NAME_LEVEL);
		sb.append(":");
		sb.append(ConstantsFw.N_TYPE_TREND);
		sb.append(")-[:");
		sb.append(ConstantsFw.R_WIP_NAME_LEVEL);
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
		sb.append(ConstantsFw.N_WIP_NAME_LEVEL);
		sb.append("\"");
		update(sb.toString(), params);
	}

	
	
}
