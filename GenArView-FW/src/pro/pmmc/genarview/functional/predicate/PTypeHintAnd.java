package pro.pmmc.genarview.functional.predicate;

import java.util.List;
import java.util.function.Predicate;

import org.neo4j.graphdb.Node;

import pro.pmmc.genarview.xml.xsd.dsl.TypeHintAnd;
import pro.pmmc.genarview.xml.xsd.dsl.TypeHintMember;

public class PTypeHintAnd<T> extends PTypeHintGeneric<T, TypeHintAnd> implements Predicate<T> {

	public PTypeHintAnd(TypeHintAnd hint) {
		super(hint);
	}

	@Override
	public boolean test(T value) {
		List<TypeHintMember> lstTf = hint.getHintAndOrHintOrOrHint();
		boolean compliant = lstTf.stream().reduce(true, 
				(v, tf) -> v && CreatorPredicate.createPTypeHint(tf).test(value),
				(v1, v2) -> v1 && v2);
		return compliant;
	}

	

	
}
