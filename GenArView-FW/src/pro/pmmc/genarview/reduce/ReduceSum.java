package pro.pmmc.genarview.reduce;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

public class ReduceSum extends ReduceGeneric {

	public ReduceSum(String keyExp) {
		super(keyExp);
	}

	@Override
	public Entry<String, Object> apply(Entry<String, Object> o1,
			Entry<String, Object> o2) {
		double d1 = Double.parseDouble(o1.getValue().toString());
		double d2 = Double.parseDouble(o2.getValue().toString());
		return new SimpleEntry<String, Object>(key, d1 + d2);
	}
}
