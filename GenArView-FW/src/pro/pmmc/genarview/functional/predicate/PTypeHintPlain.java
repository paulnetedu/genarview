package pro.pmmc.genarview.functional.predicate;

import java.util.Map;
import java.util.stream.Collectors;

import pro.pmmc.genarview.xml.xsd.dsl.TypeHintPlain;

public abstract class PTypeHintPlain<T> extends PTypeHintGeneric<T, TypeHintPlain> {

	protected Map<String, String> values; 
	
	public PTypeHintPlain(TypeHintPlain hint) {
		super(hint);
		values = hint.getValue().stream().collect(Collectors.toMap(
				v -> v.getKey(), v -> v.getValue()));
	}

	
}
