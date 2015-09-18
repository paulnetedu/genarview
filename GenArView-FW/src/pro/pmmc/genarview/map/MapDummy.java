package pro.pmmc.genarview.map;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.Map.Entry;

import org.neo4j.graphdb.Node;

public class MapDummy extends MapGeneric {

	public MapDummy(String key) {
		super(key);
	}

	@Override
	public Entry<String, Object> apply(Map<String, Object> mapRow) {
		return new SimpleEntry<String, Object>((String) getValueFromExpression(keyExp, mapRow),
				mapRow);
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
