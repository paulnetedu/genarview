package pro.pmmc.genarview.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.neo4j.cypher.ExecutionEngine;
import org.neo4j.cypher.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.impl.util.StringLogger;

import pro.pmmc.genarview.exception.GraphServiceNotRegisteredException;

public class GraphUtil {

	private static GraphDatabaseService graphService;

	protected static ExecutionEngine engine;

	public static void registerGraphDatabaseService(GraphDatabaseService graphService) {
		GraphUtil.graphService = graphService;
		engine = new ExecutionEngine(graphService, StringLogger.SYSTEM);
	}

	public static GraphDatabaseService getGraphService() {
		if (graphService == null) {
			throw new GraphServiceNotRegisteredException();
		}
		return graphService;
	}

	public GraphUtil() {
		
	}

	public <T> List<T> query(String queryString) {
		ExecutionResult result = engine.execute(queryString);
		return processResultSingleColumn(result);
	}
	
	public <T> List<T> query(String queryString, Map<String, Object> params) {
		ExecutionResult result = engine.execute(queryString, params);
		return processResultSingleColumn(result);
	}
	
	public static void update(String queryString) {
		engine.execute(queryString);
	}
	
	public static void update(String queryString, Map<String, Object> params) {
		engine.execute(queryString, params);
	}

	public <T> List<T> querySingleColumn(String queryString, Map<String, Object> params) {
		ExecutionResult result = engine.execute(queryString, params);
		return processResultSingleColumn(result);
	}

	public <T> List<T> processResultSingleColumn(ExecutionResult result) {
		ResourceIterator<Map<String, Object>> resIt = result.javaIterator();
		List<T> lst = new LinkedList<T>();
		while (resIt.hasNext()) {
			Map<String, Object> row = resIt.next();
			for (Entry<String, Object> column : row.entrySet()) {
				lst.add((T) column.getValue());
			}
		}
		return lst;
	}
	
	public List<Map<String, Object>> queryRows(String queryString) {
		ExecutionResult result = engine.execute(queryString);
		return processResultRows(result);
	}
	
	public List<Map<String, Object>> queryRows(String queryString, Map<String, Object> params) {
		ExecutionResult result = engine.execute(queryString, params);
		return processResultRows(result);
	}
	
	public List<Map<String, Object>> processResultRows(ExecutionResult result) {
		ResourceIterator<Map<String, Object>> resIt = result.javaIterator();
		List<Map<String, Object>> lst = new LinkedList<Map<String, Object>>();
		while (resIt.hasNext()) {
			lst.add(resIt.next());
		}
		return lst;
	}

	public Map<String, Map<String, Object>> queryMap(String queryString, String keyField) {
		ExecutionResult result = engine.execute(queryString);
		return processResultMap(result, keyField);
	}
	
	public Map<String, Map<String, Object>> queryMap(String queryString, Map<String, Object> params, String keyField) {
		ExecutionResult result = engine.execute(queryString, params);
		return processResultMap(result, keyField);
	}
	
	public Map<String, Map<String, Object>> processResultMap(ExecutionResult result, String keyField) {
		ResourceIterator<Map<String, Object>> resIt = result.javaIterator();
		Map<String, Map<String, Object>> map = new HashMap<String, Map<String, Object>>();
		while (resIt.hasNext()) {
			Map<String, Object> row = resIt.next();
			map.put(row.get(keyField).toString(), row);
		}
		return map;
	}
	
	public List<Relationship> queryRels(String queryString, Map<String, Object> params) {
		ExecutionResult result = engine.execute(queryString, params);
		ResourceIterator<Map<String, Object>> resIt = result.javaIterator();
		List<Relationship> lstRel = new LinkedList<Relationship>();
		while (resIt.hasNext()) {
			Map<String, Object> row = resIt.next();
			for (Entry<String, Object> column : row.entrySet()) {
				if (column.getValue() instanceof Relationship) {
					lstRel.add((Relationship) column.getValue());
				} else {
					System.err.println("The query '" + queryString
							+ "' is returning an object that is not a Relationship");
				}
			}
		}
		return lstRel;
	}
	
	public List<Node> queryByFullname(String fullName, String nodeType) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "fullName", fullName);
		String queryString = "MATCH (n:" + nodeType + ") WHERE n.fullName = {fullName} RETURN n";
		return querySingleColumn(queryString, params);
	}
	
	public Node querySingleByFullname(String fullName, String nodeType) {
		Node node = null;
		List<Node> lst = queryByFullname(fullName, nodeType);
		if (lst != null && !lst.isEmpty()) {
			node = lst.get(0);
		}
		return node;
	}
	
	public List<Node> queryByPath(String path, String nodeType) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "path", path);
		String queryString = "MATCH (n:" + nodeType + ") WHERE n.path = {path} RETURN n";
		return querySingleColumn(queryString, params);
	}
	
	public Node querySingleByPath(String path, String nodeType) {
		Node node = null;
		List<Node> lst = queryByPath(path, nodeType);
		if (lst != null && !lst.isEmpty()) {
			node = lst.get(0);
		}
		return node;
	}
	
	public List<Node> queryByName(String name, String nodeType) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "name", name);
		String queryString = "MATCH (n:" + nodeType + ") WHERE n.name = {name} RETURN n";
		return querySingleColumn(queryString, params);
	}
	
	public Node querySingleByName(String name, String nodeType) {
		Node node = null;
		List<Node> lst = queryByName(name, nodeType);
		if (lst != null && !lst.isEmpty()) {
			node = lst.get(0);
		}
		return node;
	}
	
	public List<Node> queryByFullnamePath(String fullName, String path, String nodeType) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "fullName", fullName);
		params.put( "path", path);
		String queryString = "MATCH (n:" + nodeType + ") WHERE n.fullName = {fullName} AND n.path = {path} RETURN n";
		return querySingleColumn(queryString, params);
	}
	
	public boolean existsRelationship(long id1, long id2, String relType) {
		final String queryString = "START a=node({id1}), b=node({id2}) MATCH a-[r:" 
				+ relType + "]-b RETURN r";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "id1", id1);
		params.put( "id2", id2);
		return !queryRels(queryString, params).isEmpty();
	}
	
	public List<Long> queryPackageRoot() {
		final String queryString = "MATCH (p:Package) WHERE p.level = 1 RETURN id(p)";
		return query(queryString);
	}
	
	public <T> Map<String, Long> queryEntryLong(String queryString) {
		ExecutionResult result = engine.execute(queryString);
		return processResultEntryLong(result);
	}
	
	public Map<String, Long> queryEntryLong(String queryString, Map<String, Object> params) {
		ExecutionResult result = engine.execute(queryString, params);
		return processResultEntryLong(result);
	}
	
	public static Long queryLong(String queryString) {
		ExecutionResult result = engine.execute(queryString);
		return processResultSingleLong(result);
	}
	
	public static Long queryLong(String queryString, Map<String, Object> params) {
		ExecutionResult result = engine.execute(queryString, params);
		return processResultSingleLong(result);
	}
	
	public Map<String, Long> processResultEntryLong(ExecutionResult result) {
		ResourceIterator<Map<String, Object>> resIt = result.javaIterator();
		Map<String, Long> map = new HashMap<String, Long>();
		final String item = "item";
		final String value = "value";
		while (resIt.hasNext()) {
			Map<String, Object> row = resIt.next();
			map.put(row.get(item).toString(), Long.parseLong(row.get(value).toString()));
		}
		return map;
	}
	
	public static Long processResultSingleLong(ExecutionResult result) {
		ResourceIterator<Map<String, Object>> resIt = result.javaIterator();
		Long value = null;
		final String strValue = "value";
		while (resIt.hasNext()) {
			Map<String, Object> row = resIt.next();
			value = Long.parseLong(row.get(strValue).toString());
		}
		return value;
	}
	
	public static Integer processResultSingleInteger(ExecutionResult result) {
		ResourceIterator<Map<String, Object>> resIt = result.javaIterator();
		Integer value = null;
		final String strValue = "value";
		while (resIt.hasNext()) {
			Map<String, Object> row = resIt.next();
			value = (Integer) row.get(strValue);
		}
		return value;
	}
	
	public <T> Map<String, Integer> queryEntryInteger(String queryString) {
		ExecutionResult result = engine.execute(queryString);
		return processResultEntryInteger(result);
	}
	
	public Map<String, Integer> queryEntryInteger(String queryString, Map<String, Object> params) {
		ExecutionResult result = engine.execute(queryString, params);
		return processResultEntryInteger(result);
	}
	
	public static Integer queryInteger(String queryString) {
		ExecutionResult result = engine.execute(queryString);
		return processResultSingleInteger(result);
	}
	
	public static Integer queryInteger(String queryString, Map<String, Object> params) {
		ExecutionResult result = engine.execute(queryString, params);
		return processResultSingleInteger(result);
	}
	
	public Map<String, Integer> processResultEntryInteger(ExecutionResult result) {
		ResourceIterator<Map<String, Object>> resIt = result.javaIterator();
		Map<String, Integer> map = new HashMap<String, Integer>();
		final String item = "item";
		final String value = "value";
		while (resIt.hasNext()) {
			Map<String, Object> row = resIt.next();
			map.put(row.get(item).toString(), Integer.parseInt(row.get(value).toString()));
		}
		return map;
	}
	
	public <T> Map<String, Double> queryEntryDouble(String queryString) {
		ExecutionResult result = engine.execute(queryString);
		return processResultEntryDouble(result);
	}
	
	public Map<String, Double> queryEntryDouble(String queryString, Map<String, Object> params) {
		ExecutionResult result = engine.execute(queryString, params);
		return processResultEntryDouble(result);
	}
	
	public static Double queryDouble(String queryString) {
		ExecutionResult result = engine.execute(queryString);
		return processResultSingleDouble(result);
	}
	
	public static Double queryDouble(String queryString, Map<String, Object> params) {
		ExecutionResult result = engine.execute(queryString, params);
		return processResultSingleDouble(result);
	}
	
	public Map<String, Double> processResultEntryDouble(ExecutionResult result) {
		ResourceIterator<Map<String, Object>> resIt = result.javaIterator();
		Map<String, Double> map = new HashMap<String, Double>();
		final String item = "item";
		final String value = "value";
		while (resIt.hasNext()) {
			Map<String, Object> row = resIt.next();
			map.put(row.get(item).toString(), Double.parseDouble(row.get(value).toString()));
		}
		return map;
	}
	
	public static Double processResultSingleDouble(ExecutionResult result) {
		ResourceIterator<Map<String, Object>> resIt = result.javaIterator();
		Double value = null;
		final String strValue = "value";
		while (resIt.hasNext()) {
			Map<String, Object> row = resIt.next();
			value = Double.parseDouble(row.get(strValue).toString());
		}
		return value;
	}
	public static String queryString(String queryString) {
		ExecutionResult result = engine.execute(queryString);
		return processResultSingleString(result);
	}
	
	public static String queryString(String queryString, Map<String, Object> params) {
		ExecutionResult result = engine.execute(queryString, params);
		return processResultSingleString(result);
	}
	
	public Map<String, String> processResultEntryString(ExecutionResult result) {
		ResourceIterator<Map<String, Object>> resIt = result.javaIterator();
		Map<String, String> map = new HashMap<String, String>();
		final String item = "item";
		final String value = "value";
		while (resIt.hasNext()) {
			Map<String, Object> row = resIt.next();
			map.put(row.get(item).toString(), (String) row.get(value));
		}
		return map;
	}
	
	public static String processResultSingleString(ExecutionResult result) {
		ResourceIterator<Map<String, Object>> resIt = result.javaIterator();
		String value = null;
		final String strValue = "value";
		while (resIt.hasNext()) {
			Map<String, Object> row = resIt.next();
			value = (row.get(strValue) != null) ? row.get(strValue).toString() : "";
		}
		return value;
	}
	
	public static List<String> queryStrings(String queryString) {
		ExecutionResult result = engine.execute(queryString);
		return processResultStrings(result);
	}
	
	public static List<String> queryStrings(String queryString, Map<String, Object> params) {
		ExecutionResult result = engine.execute(queryString, params);
		return processResultStrings(result);
	}
	
	public static List<String> processResultStrings(ExecutionResult result) {
		final List<String> lst = new LinkedList<String>();
		ResourceIterator<Map<String, Object>> resIt = result.javaIterator();
		final String strValue = "value";
		while (resIt.hasNext()) {
			Map<String, Object> row = resIt.next();
			lst.add(row.get(strValue).toString());
		}
		return lst;
	}

	public static void buildPackageLevel() {
		countPackageSizeType();
		countPackageSizePackage();
		createLevel();
		relateLevelPackage();
		countLevelSizePackage();
		countLevelSizeType();
	}
	
	public static void countPackageSizeType() {
		final String query = "MATCH (tp:Type)<--(pack:Package) WITH pack AS pack, COUNT(DISTINCT tp) AS count SET pack.sizeType=count";
		update(query);
	}
	
	public static void countPackageSizePackage() {
		final String query = "MATCH (p:Package)<-[r:INCLUDE]-(pack:Package) WITH pack AS pack, COUNT(DISTINCT p) AS count SET pack.sizePackage=count";
		update(query);
	}
	
	public static void createLevel() {
		final String query = "MATCH (pack:Package) WITH pack.level AS nlevel, COUNT(*) AS count CREATE (l:Level {level:nlevel})";
		update(query);
	}
	
	public static void relateLevelPackage() {
		final String query = "MATCH (pack:Package),(level:Level) WHERE pack.level=level.level CREATE (level)-[r:LEVEL]->(pack)";
		update(query);
	}
	
	public static void countLevelSizePackage() {
		final String query = "MATCH (p:Package)<-[r:LEVEL]-(l:Level) WITH l AS level, COUNT(DISTINCT p) AS count SET level.sizePackage=count";
		update(query);
	}
	
	public static void countLevelSizeType() {
		final String query = "MATCH (l:Level)-[r:LEVEL]->(p:Package)-[r2:CONTAIN]->(t:Type) WITH l AS level, COUNT(DISTINCT t) AS count SET level.sizeType=count";
		update(query);
	}
	
	
	
}
