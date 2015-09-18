package pro.pmmc.genarview.functional.function;

import java.util.function.Function;
import java.util.stream.Stream;

import org.neo4j.graphdb.Node;

public abstract class FnnDeco implements Function<Node, Stream<Node>> {

	private Function<Node, Stream<Node>> fpn;
	
	public abstract Stream<Node> decorate(Node t, Stream<Node> strmNode);
	
	@Override
	public Stream<Node> apply(Node t) {
		Stream<Node> strmNode = fpn.apply(t);
		return decorate(t, strmNode);
	}
}
