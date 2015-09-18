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

import pro.pmmc.genarview.util.ConstantsFw;
import pro.pmmc.genarview.util.FileUtil;
import pro.pmmc.genarview.util.GraphUtil;

public class FUmlClassCopy implements Function<Package, Set<Class>> {

	@Override
	public Set<Class> apply(Package pack) {
		Transaction tx = GraphUtil.getGraphService().beginTx();
		Set<Class> lstClass = new HashSet<Class>();
		Map<String, Class> mapNewClass = new HashMap<String, Class>();
		GraphUtil gu = new GraphUtil();
		List<Node> lstNode =  gu.queryByFullname(FileUtil.toFullNameFromUmlQualified(pack.getQualifiedName()),
				ConstantsFw.N_TYPE_PACKAGE);
		lstNode.forEach(n -> {
			Iterable<Relationship> it = n.getRelationships(() -> ConstantsFw.N_REL_CONTAIN, Direction.OUTGOING);
			Iterator<Relationship> iter = it.iterator();
			while (iter.hasNext()) {
				Relationship r = iter.next();
				Node target = r.getEndNode();
				Class c = pack.createOwnedClass(target.getProperty("name").toString(), false);
				mapNewClass.put(FileUtil.toFullNameFromUmlQualified(c.getQualifiedName()), c);
				
			}
		});
		tx.success();
		tx.finish();tx.close();
		mapNewClass.values().forEach(c -> lstClass.add(c));
		return lstClass;
	}

	
}
