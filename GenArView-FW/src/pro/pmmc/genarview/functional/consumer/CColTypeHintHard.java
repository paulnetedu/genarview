package pro.pmmc.genarview.functional.consumer;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import pro.pmmc.genarview.functional.predicate.CreatorPredicate;
import pro.pmmc.genarview.functional.predicate.PTypeHintGeneric;
import pro.pmmc.genarview.xml.xsd.dsl.TypeHintExp;
import pro.pmmc.genarview.xml.xsd.dsl.TypeHintMember;

public class CColTypeHintHard implements Consumer<TypeHintExp> {

	private List<Predicate> predicates;
	
	public CColTypeHintHard() {
		setPredicates(new LinkedList<Predicate>());
	}

	@Override
	public void accept(TypeHintExp tf) {
		TypeHintMember tFeat = tf.getHintAnd();
		tFeat = (tFeat == null) ? tf.getHintOr() : tFeat;
		CreatorPredicate creator = new CreatorPredicate();
		Predicate predicate = creator.createPTypeHint(tFeat);
		predicates.add(predicate);
	}

	public List<Predicate> getPredicates() {
		return predicates;
	}

	public void setPredicates(List<Predicate> predicates) {
		this.predicates = predicates;
	}
	
}
