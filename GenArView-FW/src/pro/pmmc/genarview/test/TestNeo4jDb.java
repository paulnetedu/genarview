package pro.pmmc.genarview.test;


import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.neo4j.cypher.ExecutionEngine;
import org.neo4j.cypher.ExecutionResult;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.impl.util.StringLogger;

import pro.pmmc.genarview.util.GraphUtil;



public class TestNeo4jDb {

	public static void main(String[] args) {
		executeTypical();
		//addNode();
		//addLabelToNode();
	}
	
	public static void executeTypical() {
		final String DB_PATH = "D:\\tools\\neo4j-community-2.1.8\\data\\genarview-basic.db";
		GraphDatabaseService graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
		registerShutdownHook( graphDb );
		Node firstNode;
		Node secondNode;
		Relationship relationship;
		ExecutionEngine engine = new ExecutionEngine( graphDb, StringLogger.SYSTEM );

		ExecutionResult result;

		try ( Transaction ignored = graphDb.beginTx() )
		{
		    //result = engine.execute( "start n=node(*) where n.message = 'Hello, ' return n, n.message" );
			String query = null;
			//query = "start n=node(*) return n, n.fullName";
			//query = "MATCH (p:FactoryMethod) RETURN p, p.fullName";
			//query = "MATCH (t:Type)--(p:Package) RETURN p, t";
			query = "MATCH (t:Type)--(a:Annotation) RETURN a.fullName,t.fullName";
			//query = "MATCH (p:Package) WHERE p.name=\"envers\" RETURN p";
			//query = "MATCH (t:Type) WHERE t.name=\"SoccerPlayer\" RETURN t";
			query = "MATCH (p:DataAccess) RETURN p.fullName";
			//query = "MATCH (p:Package) RETURN p, p.fullName";
			//query = "MATCH (t:Type) RETURN t";
			result = engine.execute(query);
		    ResourceIterator<Map<String, Object>> resit = result.javaIterator();
		    
		    System.out.println("----------------");
		    String rows = "";
		    while (resit.hasNext()) { 
		    	Map<String, Object> row = resit.next();
		        for ( Entry<String, Object> column : row.entrySet() )
			    {
			        rows += column.getKey() + ": " + column.getValue() + ";" ;
			    }
			    rows += "\n";
		    }
		    System.out.println(rows);
		    //clearDb(graphDb);
		}
				
		
	}
	public static void addNode() {
		final String DB_PATH = "D:\\tools\\neo4j-community-2.1.2\\data\\_genarview-dsl.db";
		GraphDatabaseService gs = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
		GraphUtil.registerGraphDatabaseService(gs);
		registerShutdownHook(gs);
		Node firstNode;
		Node secondNode;
		Relationship relationship;
		Transaction tx = GraphUtil.getGraphService().beginTx();
		firstNode = GraphUtil.getGraphService().createNode();
		firstNode.addLabel(DynamicLabel.label("Test1"));
		firstNode.setProperty( "fullName", "pro.pmmc.test.Hello" );
		firstNode.setProperty( "message", "Hello, " );
		tx.success();
		tx.finish();
		tx.close();
	}
	
	public static void addLabelToNode() {
		final String DB_PATH = "D:\\tools\\neo4j-community-2.1.2\\data\\_genarview-dsl.db";
		GraphDatabaseService gs = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
		GraphUtil.registerGraphDatabaseService(gs);
		registerShutdownHook(gs);
		GraphUtil graphUtil = new GraphUtil();
		List<Node> nodes = graphUtil.queryByFullname("pro.pmmc.test.Hello", "Test1" );
		for (Node n : nodes) {
			Transaction t = GraphUtil.getGraphService().beginTx();
			System.out.println("--" + n);
			n.addLabel(DynamicLabel.label("L3"));
			Iterable<Label> labels = n.getLabels();
			labels.forEach(System.out::println);
			t.success();
			t.close();
		}
	}
	
	private static void clearDb( final GraphDatabaseService graphDb ) {
		ExecutionEngine engine = new ExecutionEngine( graphDb, StringLogger.SYSTEM );
		try ( Transaction ignored = graphDb.beginTx() ) {
			engine.execute( "match n with n optional match n-[r]-() delete r,n" );
		}
	}
	
	private static void registerShutdownHook( final GraphDatabaseService graphDb )	{
	    // Registers a shutdown hook for the Neo4j instance so that it
	    // shuts down nicely when the VM exits 
	    Runtime.getRuntime().addShutdownHook( new Thread()
	    {
	        @Override
	        public void run()
	        {
	            graphDb.shutdown();
	        }
	    } );
	}
	
}
// OPTIONAL MATCH (t:Type)-[:IMPLEMENTS]-(tt:Type) RETURN t
/*
MATCH N RETURN N
MATCH (N:Package) return N 
MATCH (t:Type)-[:EXTEND]-(tt:Type) RETURN t
MATCH (N:Type) return N

 
MATCH (node:Package)
WITH COUNT(node.name) as counter, node.name AS name
WHERE counter > 1
RETURN counter, name
ORDER BY counter DESC

MATCH (node:Package)
WITH COUNT(node.name) as counter, node.name AS name
WHERE counter > 1
MATCH (parent:Package)-->(n{name:name}) 
RETURN parent, name

MATCH (node:Package)
WITH COUNT(node.name) as counter, node.name AS name
WHERE counter > 1
MATCH (parent:Package)-->(n{name:name}) 
RETURN parent

OPTIONAL MATCH (root:Package)
WHERE NOT ()-[:INCLUDE]->(root)
RETURN root

MATCH (node:Word)
WITH COUNT(node.word) as counter, node.word AS word
WHERE counter > 1
RETURN counter, word
ORDER BY counter DESC

MATCH (w:WordLevelIndex)-[:W_IN_TYPE]-(t:Type)-[:CONTAIN]-(p:Package)
WITH COUNT(p.level) as counter, p.level AS level
WHERE counter > 1
RETURN counter, level
ORDER BY counter DESC


MATCH (p:Package)
WHERE p.level = 1
RETURN id(p)

MATCH (p:Package)
RETURN p.name, p.level, count(*)

START n=node(61)
MATCH (w:WordLevelIndex)-[:W_IN_TYPE]-(t:Type)-[:CONTAIN]-(p:Package)-[:INCLUDE*1..300]-(n)
WITH p.name as pack, w.word as word, w.level as level, COUNT(*) as counter
WHERE counter > 1
RETURN pack, word,level,counter

MATCH (p:Package)
RETURN p.level, count(*)

MATCH (w:WordLevelIndex)-[:W_IN_TYPE]-(t:Type)-[:CONTAIN]-(p:Package)
WITH w.word as word, w.level as level, COUNT(*) as counter
WHERE counter > 1
RETURN word,level,counter

START n=node(63)
MATCH (w:Word)-[:W_IN_PACKAGE]-(p:Package)-[:INCLUDE*1..300]-(n) XXX
WITH p.name as pack, p.level as level, w.word as word, COUNT(*) as counter
WHERE counter > 1
RETURN pack, level,word,counter

MATCH path=(t1:Type)-[*..1]-(t2:Type), (wli1:WordLevelIndex)--(t1:Type), (wli2:WordLevelIndex)--(t2:Type) 
WHERE wli1.word='Bean' AND wli2.word='Dto'
RETURN STR(path)

MATCH path=(t1:Type)-[*1..1]-(t2:Type), (wli1:WordLevelIndex)--(t1:Type), (wli2:WordLevelIndex)--(t2:Type) 
WHERE wli1.word='Bean' AND wli2.word='Dto'
RETURN COUNT(DISTINCT t1), COUNT(DISTINCT t2)

MATCH (tr1:Trend)--(t1:Type)-->(t2:Type)--(tr2:Trend)
WITH tr1.word AS tr1, tr1.level AS level1, tr1.index AS index1, tr1.packageName AS pack1, tr2.word as tr2, tr2.level AS level2, tr2.index AS index2, tr2.packageName AS pack2, count(DISTINCT t1) as c1, count(DISTINCT t2) as c2
RETURN tr1,level1,index1,pack1,tr2,c1,c2
ORDER BY tr1

MATCH (pack:Package)--(t:Type)--(wli:WordLevelIndex)--(w:Word), (tp:Type)--(pack:Package) 
WITH w.word AS word, pack.fullName AS packFullName, COUNT(DISTINCT t) AS countOccur, COUNT(DISTINCT tp) AS totalTypes 
WHERE countOccur>= 0.5* totalTypes RETURN word, packFullName, countOccur, totalTypes

MATCH (tr1:Trend)-->(t1:Type), (tr2:Trend)-->(t2:Type),(t1:Type)-[r]->(t2:Type) 
WITH tr1.word AS tn1, tr2.word AS tn2, type(r) AS r, labels(tr1) AS rn1, labels(tr2) AS rn2, COUNT(*) AS c 
WHERE NOT(tn1=tn2) RETURN tn1, tn2, r, rn1, rn2, c ORDER BY tn1, tn2

 */
