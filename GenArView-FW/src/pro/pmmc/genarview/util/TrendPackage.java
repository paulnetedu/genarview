package pro.pmmc.genarview.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MATCH (wip:WordIndexPackage)-[r1:W_WIPP]->(p:Package)-[r2:CONTAIN]->(t:Type) WITH wip.word AS word, wip.index AS index, wip.packageNameFull AS packName, p.sizeType AS ptotal, wip.n AS pcur, COUNT(*) AS c WHERE ptotal>1 AND pcur >= 0.9*ptotal RETURN word, index, packName, ptotal, pcur
 * @author paul
 *
 */
public class TrendPackage extends TrendGeneric {

	public TrendPackage(double percentage) {
		super(percentage);
	}

	@Override
	public void labelNodes() {
		final StringBuilder sb = new StringBuilder();
		sb.append("MATCH (wn:");
		sb.append(ConstantsFw.N_WI_PACKAGE);
		sb.append(")-[:");
		sb.append(ConstantsFw.R_WI_PACKAGE);
		sb.append("]->(p:");
		sb.append(ConstantsFw.N_TYPE_PACKAGE);
		sb.append(") WHERE p.sizeType > 2 AND wn.n>={percentage}*p.sizeType SET wn:");
		sb.append(ConstantsFw.N_TYPE_TREND);
		sb.append(",wn.percentage=TOFLOAT(wn.n)/p.sizeType");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("percentage", percentage);
		update(sb.toString(), params);
		sb.setLength(0);
		sb.append("MATCH (wn:");
		sb.append(ConstantsFw.N_WI_PACKAGE);
		sb.append(":");
		sb.append(ConstantsFw.N_TYPE_TREND);
		sb.append(")-[:");
		sb.append(ConstantsFw.R_WI_PACKAGE);
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
		sb.append(ConstantsFw.N_WI_PACKAGE);
		sb.append("\"");
		update(sb.toString(), params);
	}

}
