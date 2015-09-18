package pro.pmmc.genarview.functional.predicate;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import org.neo4j.graphdb.Node;

import pro.pmmc.genarview.xml.xsd.dsl.TypeHintMember;
import pro.pmmc.genarview.xml.xsd.dsl.TypeHintOr;

public class PTypeHintOr<T> extends PTypeHintGeneric<T, TypeHintOr> implements Predicate<T> {

	public PTypeHintOr(TypeHintOr feature) {
		super(feature);
	}

	@Override
	public boolean test(T value) {
		List<TypeHintMember> lstTf = hint.getHintAndOrHintOrOrHint();
		boolean compliant = lstTf.stream().reduce(false, 
				(v, tf) -> v || CreatorPredicate.createPTypeHint(tf).test(value),
				(v1, v2) -> v1 || v2);
		return compliant;
	}

}
