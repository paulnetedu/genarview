package pro.pmmc.genarview.map;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.Map.Entry;

import org.neo4j.graphdb.Node;

public class MapSimpleValue extends MapGeneric {

	private String valueKeyExp;
	
	public MapSimpleValue(String key, String valueKey) {
		super(key);
		this.valueKeyExp = valueKey;
	}

	@Override
	public Entry<String, Object> apply(Map<String, Object> mapRow) {
		return new SimpleEntry<String, Object>((String) getValueFromExpression(keyExp, mapRow),
				getValueFromExpression(valueKeyExp, mapRow));
	}
	
	public Object getValueFromExpression(String expression, Map<String, Object> mapRow) {
		Object v = null;
		int index = expression.indexOf('.');
		if (index == -1) {
			v = mapRow.get(expression).toString();
		} else {
			String obj = null;
			String prop = null;
			obj = expression.substring(0, index);
			prop = expression.substring(index + 1);
			Node node = (Node) mapRow.get(obj);
			v = node.getProperty(prop);
		}
		return v;
	}

}
