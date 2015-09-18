package pro.pmmc.genarview.uml;

import java.util.HashMap;	
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Package;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;

import pro.pmmc.genarview.cache.CacheUtil;
import pro.pmmc.genarview.util.FileUtil;
import pro.pmmc.genarview.util.GraphUtil;

public abstract class CRelation implements Consumer<Node> {

	public abstract void accept(Class c, Node node);
	
	public abstract String getRelationshipType();

	@Override
	public void accept(Node n) {
		GraphUtil gu = new GraphUtil();
		Transaction tx = GraphUtil.getGraphService().beginTx();
		String fullName = (String) n.getProperty("fullName");
		Class c = (Class) CacheUtil.getCache().getAux("UMLClass", fullName);
		Iterable<Relationship> it = n.getRelationships(() -> getRelationshipType(), Direction.OUTGOING);
		tx.close();
		Iterator<Relationship> iter = it.iterator();
		while (iter.hasNext()) {
			Relationship r = iter.next();
			Node target = r.getEndNode();
			accept(c, target);
		}	
	}

}
