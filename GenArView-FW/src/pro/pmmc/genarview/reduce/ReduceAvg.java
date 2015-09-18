package pro.pmmc.genarview.reduce;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

public class ReduceAvg extends ReduceGeneric {

	private double sum;
	
	private long count;
	
	public ReduceAvg(String keyExp) {
		super(keyExp);
		sum = 0;
		count = 0;
	}

	@Override
	public Entry<String, Object> apply(Entry<String, Object> o1,
			Entry<String, Object> o2) {
		double d1 = Double.parseDouble(o1.getValue().toString());
		double d2 = Double.parseDouble(o2.getValue().toString());
		if (count == 0) {
			count = 1;
			sum = d1;
		}
		count++;
		sum += d2;
		return new SimpleEntry<String, Object>(key, sum / count);
	}
}
