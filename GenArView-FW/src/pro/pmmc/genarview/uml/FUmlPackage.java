package pro.pmmc.genarview.uml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import org.eclipse.uml2.uml.Package;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;

import pro.pmmc.genarview.cache.CacheUtil;
import pro.pmmc.genarview.util.ConstantsFw;
import pro.pmmc.genarview.util.GraphUtil;

public class FUmlPackage extends UmlPackage implements Function<Node, Package> {

	public FUmlPackage() {
		super();		
	}
	
	public FUmlPackage(String modelName) {
		super(modelName);
	}
	
	@Override
	public Package apply(Node node) {
		Transaction tx = GraphUtil.getGraphService().beginTx();
		Iterable<Relationship> it = node.getRelationships(() -> ConstantsFw.N_REL_INCLUDE, Direction.INCOMING);
		List<Relationship> lst = new ArrayList<Relationship>();
		Iterator<Relationship> iter = it.iterator();
		while (iter.hasNext()) {
			Relationship r = iter.next();
			lst.add(r);
		}
		org.eclipse.uml2.uml.Package packParent = null;
		if (lst.isEmpty()) {
			packParent = model;
		} else {
			Relationship r = lst.get(0);
			Node start = r.getStartNode();
			packParent = getOrCreatePackage((String) start.getProperty("fullName"));
		}
		String packName = (String) node.getProperty("fullName");
		Package pack = createPackage(packName, packParent);
		
		tx.success();
		tx.finish();tx.close();
		return pack;
	}

}
