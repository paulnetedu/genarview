package pro.pmmc.genarview.uml;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Package;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;

import pro.pmmc.genarview.cache.CacheUtil;
import pro.pmmc.genarview.util.FileUtil;
import pro.pmmc.genarview.util.GraphUtil;

public class FUmlClass implements Function<Node, Class> {

	@Override
	public Class apply(Node node) {
		Transaction tx = GraphUtil.getGraphService().beginTx();
		String packName = (String) node.getProperty("fullName");
		//System.out.println("S--" + packName);
		packName = FileUtil.extractParentFullPackage(packName);
		CacheUtil cache = (CacheUtil) CacheUtil.getCache();
		
		Package pack = (Package) cache.getAux("UMLPackage", packName);
		while (pack == null && !packName.isEmpty()) {
			packName = FileUtil.extractParentFullPackage(packName);
			pack = (Package) cache.getAux("UMLPackage", packName);
		}  
		//System.out.println("P--" + pack.getQualifiedName());
		//System.out.println("N:" + node.getProperty("name"));
		Class c = pack.createOwnedClass(node.getProperty("name").toString(), false);
		//System.out.println(FileUtil.toFullNameFromUmlQualified(c.getQualifiedName()));
		return c;
	}

	
}
