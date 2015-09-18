package pro.pmmc.genarview.functional.predicate;

import java.util.function.Predicate;

import org.neo4j.graphdb.Node;

import pro.pmmc.genarview.xml.xsd.dsl.TypeHintMember;


public abstract class PTypeHintGeneric<T, U extends TypeHintMember> extends PTypeHint<U> implements Predicate<T> {

	public PTypeHintGeneric(U hint) {
		super(hint);
		
	}

}
