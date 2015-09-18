package pro.pmmc.genarview.render;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;

import pro.pmmc.genarview.dw.sketch.dwfigure.DwDecoratorText;
import pro.pmmc.genarview.dw.sketch.dwfigure.DwFigure;
import pro.pmmc.genarview.dw.sketch.dwfigure.DwRectangle;
import pro.pmmc.genarview.util.ConstantsFw;
import pro.pmmc.genarview.util.FileUtil;
import pro.pmmc.genarview.util.GraphUtil;

public class FDwRectangle implements Function<Node, Stream<DwFigure>> {

	@Override
	public Stream<DwFigure> apply(Node node) {
		Transaction tx = GraphUtil.getGraphService().beginTx();
		Set<DwFigure> setClass = new HashSet<DwFigure>();
		Map<String, DwFigure> mapNewFigure = new HashMap<String, DwFigure>();
		final String fullName = (String) node.getProperty("fullName");
		GraphUtil gu = new GraphUtil();
		List<Node> lstNode =  gu.queryByFullname(fullName, ConstantsFw.N_TYPE_PACKAGE);
		
		lstNode.forEach(n -> {
			Iterable<Relationship> it = n.getRelationships(() -> ConstantsFw.N_REL_CONTAIN, Direction.OUTGOING);
			Iterator<Relationship> iter = it.iterator();
			while (iter.hasNext()) {
				Relationship r = iter.next();
				Node target = r.getEndNode();
				String name = target.getProperty("name").toString();
				//Class c = pack.createOwnedClass(target.getProperty("name").toString(), false);
				DwFigure figure = new DwRectangle(50, 50);
				figure = new DwDecoratorText(figure);
				figure.setName(name);
				figure.setFullName(((fullName != null && !fullName.isEmpty())
						? fullName + "." : "") + name);
				mapNewFigure.put(FileUtil.toFullNameFromUmlQualified(target.getProperty("name").toString()), 
						figure);
				
			}
		});
		tx.success();
		tx.finish();tx.close();
		mapNewFigure.values().forEach(c -> setClass.add(c));
		return setClass.stream();
	}

	
}
