package pro.pmmc.genarview.functional.predicate;

import java.util.function.Predicate;

import org.neo4j.graphdb.Node;

import pro.pmmc.genarview.xml.xsd.dsl.TypeHintMember;

public abstract class PTypeHint<T extends TypeHintMember> {

	protected T hint;
	
	public PTypeHint(T hint) {
		this.hint = hint;
		
	}

	/*
	@Override
	public boolean test(Path path) {
		if (te.getFeatureAnd() != null) {
			return te.getFeatureAnd().getFeatureAndOrFeatureOrOrWithAnnotation();
		} else if (te.getFeatureOr() != null) {
			return te.getFeatureOr().getFeatureAndOrFeatureOrOrWithAnnotation();
		}
		feature.
		return false;
	}
	*/
}
