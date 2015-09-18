package pro.pmmc.genarview.uml;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

import org.neo4j.cypher.ExecutionEngine;
import org.neo4j.cypher.ExecutionResult;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.impl.util.StringLogger;

import pro.pmmc.genarview.util.ConstantsFw;


public class GrapherNeo4j {

	private String pathDb;
	
	private GraphDatabaseService graphDb;
	
	private List<Function<Path, List<Node>>> nodeFactories;
	
	private List<Function<Node, List<Relationship>>> nodeRelationers;
	
	public GrapherNeo4j(String pathDb) {
		this.pathDb = pathDb;
		graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(pathDb);
		nodeFactories = new ArrayList<Function<Path, List<Node>>>();
		nodeRelationers = new ArrayList<Function<Node, List<Relationship>>>();
		registerShutdownHook();
		
	}
	
	public GrapherNeo4j addNodeFactory(Function<Path, List<Node>> f) {
		nodeFactories.add(f);
		return this;
	}
	
	public GrapherNeo4j addNodeRelationer(Function<Node, List<Relationship>> r) {
		nodeRelationers.add(r);
		return this;
	}

	public Node createNode() {
		Node node = null;
		try ( Transaction tx = graphDb.beginTx() ) {
			node = graphDb.createNode();
			tx.success();
			tx.close();
		}
		return node;
	}
	
	public void addNode(Path path) {
		try ( Transaction tx = graphDb.beginTx() ) {
			nodeFactories.forEach(
					f -> {
						f.apply(path);
					}
			);
			tx.success();
			tx.close();
		}
	}
	
	public List<Node> query(String queryString, Map<String, Object> params) {
		ExecutionEngine engine = new ExecutionEngine( graphDb, StringLogger.SYSTEM );
		ExecutionResult result = engine.execute(queryString, params );
		ResourceIterator<Map<String, Object>> resIt = result.javaIterator();
		List<Node> lstNode = new LinkedList<Node>();
		while (resIt.hasNext()) { 
	    	Map<String, Object> row = resIt.next();
	        for ( Entry<String, Object> column : row.entrySet() ) {
	        	if (column.getValue() instanceof Node) {
	        		lstNode.add((Node) column.getValue());
	        	} else {
	        		System.err.println("The query '" + queryString + "' is returning an object that is not a Node");
	        	}
		    }
	    }
		return lstNode;
	}
	
	public void addPackage(String fullName) {
		ExecutionEngine engine = new ExecutionEngine( graphDb, StringLogger.SYSTEM );
		ExecutionResult result;
		try ( Transaction tx = graphDb.beginTx() ) {
			int index = fullName.lastIndexOf(".");
			String parentFullName = null;
			if (index == -1) {
				index = 0;
			} else {
				parentFullName = fullName.substring(0, index++);
			}
			String name = fullName.substring(index);
			final Node nodeNew = graphDb.createNode();
			nodeNew.addLabel(DynamicLabel.label(ConstantsFw.N_TYPE_PACKAGE));
			nodeNew.setProperty("name", name);
			nodeNew.setProperty("fullName", fullName);
			if (parentFullName != null) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put( "fullName", parentFullName);
				String query = "MATCH (n:Package) WHERE n.fullName = {fullName} RETURN n";
				result = engine.execute( query, params );
				ResourceIterator<Map<String, Object>> resIt = result.javaIterator();
				List<Node> lstNode = new LinkedList<Node>();
			    while (resIt.hasNext()) { 
			    	Map<String, Object> row = resIt.next();
			        for ( Entry<String, Object> column : row.entrySet() ) {
				        lstNode.add((Node) column.getValue());
				    }
			    }
			    lstNode.forEach(n -> {
			    	Relationship relation = n.createRelationshipTo(nodeNew, () -> ConstantsFw.N_REL_CONTAIN);
			    	
			    	}
			    );
			}
		    tx.success();
		    tx.close(); 
		}
	}
	
	private void registerShutdownHook()	{
	    Runtime.getRuntime().addShutdownHook( new Thread() {
	        @Override
	        public void run()
	        {
	            graphDb.shutdown();
	        }
	    } );
	}

	public String getPathDb() {
		return pathDb;
	}

	public void setPathDb(String pathDb) {
		this.pathDb = pathDb;
	}

	public GraphDatabaseService getGraphDb() {
		return graphDb;
	}

	public void setGraphDb(GraphDatabaseService graphDb) {
		this.graphDb = graphDb;
	}
	
	
}
