package pro.pmmc.genarview.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.neo4j.cypher.ExecutionResult;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class GraphWordUtil extends GraphUtil {

	private final String strn = "n";
	
	private static final String qWord = "MATCH (n:" + ConstantsFw.N_WORD + ") WHERE n.word = {word} RETURN n";
	
	private static final String qWordIndex = "MATCH (n:" + ConstantsFw.N_WORD_INDEX + ") WHERE n.word={word} AND n.index={index} RETURN n";
	
	//private final String qWli = "MATCH (n:" + ConstantsFw.N_TYPE_WORD_LVI + ") WHERE n.word = {word} AND n.level={level} AND n.index={index} RETURN n";
	
	//private final String qWpli = "MATCH (n:" + ConstantsFw.N_TYPE_WORD_PLVI + ") WHERE n.word = {word} AND n.level={level} AND n.index={index} RETURN n";
	
	public Node queryWordSingle(Map<String, Object> params) {
		ExecutionResult result = engine.execute(qWord, params);
		ResourceIterator<Map<String, Object>> resIt = result.javaIterator();
		while (resIt.hasNext()) {
			return (Node) resIt.next().get(strn);
		}
		return null;
	}
	
	public Node queryWordIndexSingle(Map<String, Object> params) {
		ExecutionResult result = engine.execute(qWordIndex, params);
		ResourceIterator<Map<String, Object>> resIt = result.javaIterator();
		if (resIt.hasNext()) {
			return (Node) resIt.next().get(strn);
		}
		return null;
	}
	
	public List<Node> queryWord(String word) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "word", word);
		return querySingleColumn(qWord, params);
	}
	
	public void buildGraphWord() {
		updateForWipParent();
		updateForWip();
		updateForWipName();
		updateForWipNameLevel();
	}
	
	public void updateForWip() {
		final StringBuilder sb = new StringBuilder();
		sb.append("MATCH (wi:");
		sb.append(ConstantsFw.N_WORD_INDEX);
		sb.append(")-[r:");
		sb.append(ConstantsFw.R_WORD_INDEX_TYPE);
		sb.append("]->(t:Type)<-[rp:");
		sb.append(ConstantsFw.N_REL_CONTAIN);
		sb.append("]-(p:Package) WITH wi.word AS w1, wi.index AS i1, p AS pack, COUNT(*) AS c CREATE (n:");
		sb.append(ConstantsFw.N_WI_PACKAGE);
		sb.append(" {word:w1,index:i1,packageNameFull:pack.fullName,n:c})-[rr:");
		sb.append(ConstantsFw.R_WI_PACKAGE);
		sb.append("]->(pack)");
		GraphUtil.update(sb.toString());
	}
	
	public void updateForWipParent() {
		final StringBuilder sb = new StringBuilder();
		sb.append("MATCH (wi:");
		sb.append(ConstantsFw.N_WORD_INDEX);
		sb.append(")-[r:");
		sb.append(ConstantsFw.R_WORD_INDEX_TYPE);
		sb.append("]->(t:Type)<-[rp:");
		sb.append(ConstantsFw.N_REL_CONTAIN);
		sb.append("]-(p:Package)<-[rsp:");
		sb.append(ConstantsFw.N_REL_INCLUDE);
		sb.append("]-(sp:Package) WITH wi.word AS w1, wi.index AS i1, sp as parent1, COUNT(DISTINCT(p)) AS c CREATE (n:");
		sb.append(ConstantsFw.N_WIP_PARENT);
		sb.append(" {word:w1,index:i1,parentPackageNameFull:parent1.fullName,n:c})-[rr:");
		sb.append(ConstantsFw.R_WIP_PARENT);
		sb.append("]->(parent1)");
		GraphUtil.update(sb.toString());
	}
	
	public void updateForWipName() {
		final StringBuilder sb = new StringBuilder();
		sb.append("MATCH (wi:");
		sb.append(ConstantsFw.N_WORD_INDEX);
		sb.append(")-[r:");
		sb.append(ConstantsFw.R_WORD_INDEX_TYPE);
		sb.append("]->(t:Type)<-[rp:");
		sb.append(ConstantsFw.N_REL_CONTAIN);
		sb.append("]-(p:Package) WITH wi.word AS w1, wi.index AS i1, p.name as name1, COUNT(DISTINCT(p)) AS c CREATE (n:");
		sb.append(ConstantsFw.N_WIP_NAME);
		sb.append(" {word:w1,index:i1,packageNameSimple:name1,n:c})");
		GraphUtil.update(sb.toString());
		sb.setLength(0);
		sb.append("MATCH (wi:");
		sb.append(ConstantsFw.N_WIP_NAME);
		sb.append("),(p:");
		sb.append(ConstantsFw.N_TYPE_PACKAGE);
		sb.append("{name:wi.packageNameSimple}) CREATE (wi)-[:");
		sb.append(ConstantsFw.R_WIP_NAME);
		sb.append("]->(p)");
		GraphUtil.update(sb.toString());
	}
	
	public void updateForWipNameLevel() {
		final StringBuilder sb = new StringBuilder();
		sb.append("MATCH (wi:");
		sb.append(ConstantsFw.N_WORD_INDEX);
		sb.append(")-[r:");
		sb.append(ConstantsFw.R_WORD_INDEX_TYPE);
		sb.append("]->(t:Type)<-[rp:");
		sb.append(ConstantsFw.N_REL_CONTAIN);
		sb.append("]-(p:Package) WITH wi.word AS w1, wi.index AS i1, p.level AS l1, p.name as name1, COUNT(DISTINCT(p)) AS c CREATE (n:");
		sb.append(ConstantsFw.N_WIP_NAME_LEVEL);
		sb.append(" {word:w1,index:i1,level:l1,packageNameSimple:name1,n:c})");
		GraphUtil.update(sb.toString());
		sb.setLength(0);
		sb.append("MATCH (wi:");
		sb.append(ConstantsFw.N_WIP_NAME_LEVEL);
		sb.append("),(p:");
		sb.append(ConstantsFw.N_TYPE_PACKAGE);
		sb.append("{name:wi.packageNameSimple,level:wi.level}) CREATE (wi)-[:");
		sb.append(ConstantsFw.R_WIP_NAME_LEVEL);
		sb.append("]->(p)");
		GraphUtil.update(sb.toString());
	}
	
	public void applyTrendLabeler(TrendLabeler labeler) {
		labeler.labelNodes();
	}
	
	/**
	 * 
	 * @param levelLPercentage Percentage of occurrence by level
	 * @param levelLiPercentage Percentage of occurrence by level and index
	 * @param packPPercentage Percentage of occurrence by package
	 * @param packPiPercentage Percentage of occurrence by package, level, and index
	 */
	public void relateTrends() {
		final StringBuilder sb = new StringBuilder();
		// Set relationship between Trends
		sb.append("MATCH(tr1:");
		sb.append(ConstantsFw.N_TYPE_TREND);
		sb.append(")-->(t1:");
		sb.append(ConstantsFw.N_TYPE_TYPE);
		sb.append(")-[r]->(t2:");
		sb.append(ConstantsFw.N_TYPE_TYPE);
		sb.append(")<--(tr2:");
		sb.append(ConstantsFw.N_TYPE_TREND);
		sb.append(") WITH tr1 AS trend1, tr2 AS trend2, type(r) AS relname, COUNT(DISTINCT t1) AS c1, COUNT(DISTINCT t2) AS c2 CREATE (trend1)-[:RELATION{type:relname,typeFrom:c1, typeTo:c2}]->(trend2)");
		update(sb.toString());
		sb.setLength(0);
		sb.append("MATCH(tr:");
		sb.append(ConstantsFw.N_TYPE_TREND);
		sb.append(")-->(t:");
		sb.append(ConstantsFw.N_TYPE_TYPE);
		sb.append(") WITH tr AS trend,COUNT(DISTINCT(t)) AS c SET trend.typeCount=c");
		update(sb.toString());
		
	}

	public static void main(String[] args) {
		String pathDb = "D:\\tools\\neo4j-community-2.1.2\\data\\_genarview-dsl.db";
		GraphDatabaseService gs = new GraphDatabaseFactory()
				.newEmbeddedDatabase(pathDb);
		GraphUtil.registerGraphDatabaseService(gs);
		GraphWordUtil graphWordUtil = new GraphWordUtil();
		graphWordUtil.relateTrends();
		
	}
}
/*
 map level
4*Bo$0 : 4
4*Impl$100 : 4

map package
bean@5*Bean$0 : 5
dto@5*Dto$0 : 3
dto@5*Ejemplar$100 : 3

 */
