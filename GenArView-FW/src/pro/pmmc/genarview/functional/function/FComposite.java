package pro.pmmc.genarview.functional.function;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import org.neo4j.graphdb.Node;

public class FComposite implements Function<Path, List<Node>> {

	private List<Function<Path, List<Node>>> nodeFactories;
	
	public FComposite() {
		nodeFactories = new ArrayList<Function<Path, List<Node>>>();
	}
	
	public FComposite add(Function<Path, List<Node>> nodeFactory) {
		nodeFactories.add(nodeFactory);
		return this;
	}
	
	@Override
	public List<Node> apply(Path t) {
		List<Node> lst = null;
		lst = nodeFactories.stream().map(nf -> nf.apply(t)).collect(
				(Supplier<List<Node>>) ArrayList<Node>::new, 
				List::addAll, 
				List::addAll);
		return lst;
	}

}
