package pro.pmmc.genarview.reduce;

import java.util.Map.Entry;
import java.util.function.BinaryOperator;

public abstract class ReduceGeneric implements BinaryOperator< Entry<String, Object> > {

	protected String key;

	public ReduceGeneric(String key) {
		this.key = key;
	}
	
	
}
