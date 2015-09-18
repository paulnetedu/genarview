package pro.pmmc.genarview.functional.function;

import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import pro.pmmc.genarview.util.ConstantsFw;
import pro.pmmc.genarview.util.FileUtil;
import pro.pmmc.genarview.util.GraphUtil;

public class FPackage implements Function<Path, Node> {

	private String basePath;
	
	public FPackage(String basePath) {
		this.basePath = basePath;
	}
	
	@Override
	public Node apply(Path path) {
		String fullName = FileUtil.toFullPackageName(path.toString(), basePath);
		String name = FileUtil.extractLastName(fullName);
		int level = StringUtils.countMatches(fullName, ".");
		if (fullName.isEmpty()) {
			level--;
		}
		level++;
		// TX MODIF
		Transaction tx = GraphUtil.getGraphService().beginTx();
		Node node = GraphUtil.getGraphService().createNode();
		node.addLabel(DynamicLabel.label(ConstantsFw.N_TYPE_PACKAGE));
		node.setProperty("name", name);
		node.setProperty("fullName", fullName);
		node.setProperty("level", level);
		node.setProperty("path", path.toString());
		tx.success();
		tx.close();
		return node;
	}

}
