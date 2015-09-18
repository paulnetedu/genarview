package pro.pmmc.genarview.reduce;

import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class ReduceStdev extends ReduceGeneric {

	private double sum;
	
	private List<Double> values;
	
	public ReduceStdev(String keyExp) {
		super(keyExp);
		sum = 0;
		values = new LinkedList<Double>();
	}

	@Override
	public Entry<String, Object> apply(Entry<String, Object> o1,
			Entry<String, Object> o2) {
		double d1 = Double.parseDouble(o1.getValue().toString());
		double d2 = Double.parseDouble(o2.getValue().toString());
		double stdev = 0;
		if (values.isEmpty()) {
			values.add(d1);
			values.add(d2);
			sum = d1 + d2;
			stdev = Math.sqrt(sumDifference() / 2);
		} else {
			values.add(d2);
			sum += d2;
			stdev = Math.sqrt(sumDifference() / values.size());
		}
		return new SimpleEntry<String, Object>(key, stdev);
	}
	
	public double sumDifference() {
		double diff = 0;
		double avg = sum / values.size();
		for (Double v : values) {
			diff += Math.pow(v - avg, 2);
		}
		return diff;
	}
}
