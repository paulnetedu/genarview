package pro.pmmc.genarview.functional.function;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import pro.pmmc.genarview.util.GraphUtil;

public class FpnDecoLabeler extends FpnDeco {

	private List<String> lstLabel;
	
	public FpnDecoLabeler(List<String> label) {
		this.lstLabel = label;
	}

	@Override
	public Stream<Node> decorate(Path t, Stream<Node> strmNode) {
		// TX MODIF
		Transaction tx = GraphUtil.getGraphService().beginTx();
		strmNode.forEach(n -> {
			for (String l : lstLabel) {
				n.addLabel(DynamicLabel.label(l));
			}
		});
		tx.success();
		tx.close();
		return strmNode;
	}

}
