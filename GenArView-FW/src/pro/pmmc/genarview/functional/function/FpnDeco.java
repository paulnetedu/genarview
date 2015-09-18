package pro.pmmc.genarview.functional.function;

import java.nio.file.Path;
import java.util.function.Function;
import java.util.stream.Stream;

import org.neo4j.graphdb.Node;

public abstract class FpnDeco implements Function<Path, Stream<Node>> {

	private Function<Path, Stream<Node>> fpn;
	
	public abstract Stream<Node> decorate(Path t, Stream<Node> strmNode);
	
	@Override
	public Stream<Node> apply(Path t) {
		Stream<Node> strmNode = fpn.apply(t);
		return decorate(t, strmNode);
	}

}
