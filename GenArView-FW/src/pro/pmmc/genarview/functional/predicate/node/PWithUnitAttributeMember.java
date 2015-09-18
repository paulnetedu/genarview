package pro.pmmc.genarview.functional.predicate.node;

import org.neo4j.graphdb.Node;

import pro.pmmc.genarview.functional.predicate.PTypeHintPlain;
import pro.pmmc.genarview.xml.xsd.dsl.TypeHintPlain;

public class PWithUnitAttributeMember extends PTypeHintPlain<Node> {

	public PWithUnitAttributeMember(TypeHintPlain hint) {
		super(hint);
	}

	@Override
	public boolean test(Node node) {
		// xxx
		return false;
	}
}
