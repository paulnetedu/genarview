package pro.pmmc.genarview.map;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import org.eclipse.jdt.internal.core.SetVariablesOperation;
import org.neo4j.graphdb.Node;

import pro.pmmc.genarview.util.FormulaUtil;
import pro.pmmc.genarview.xml.xsd.dsl.TypeFormula;

public class MapFormula extends MapGeneric {

	public MapFormula(String keyExp, TypeFormula formula) {
		super(keyExp, formula);
	}

	@Override
	public Entry<String, Object> apply(Map<String, Object> mapRow) {
		return new SimpleEntry<String, Object>((String) getValueFromExpression(keyExp, mapRow),
				evaluateFormula(mapRow));
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
