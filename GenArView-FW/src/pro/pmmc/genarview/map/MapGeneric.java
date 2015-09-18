package pro.pmmc.genarview.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import pro.pmmc.genarview.xml.xsd.dsl.TypeFormula;
import pro.pmmc.genarview.xml.xsd.dsl.TypeFormulaCondition;

public abstract class MapGeneric implements Function<Map<String, Object>, Entry<String, Object>> {

	protected String keyExp;

	protected Expression formulaExp;
	
	protected List<Expression> lstConditionExp;
	
	protected List<Expression> lstConditionExpVal;
	
	protected List<Expression> lstConditionVal;
	
	protected TypeFormula formula;
	
	public MapGeneric(String keyExp) {
		this.keyExp = keyExp;
	}

	public MapGeneric(String keyExp, TypeFormula formula) {
		this.keyExp = keyExp;
		this.formula = formula;
		buildFormula();
	}

	protected void buildFormula() {
		ExpressionBuilder builder = null;
		ExpressionBuilder builder2 = null;
		ExpressionBuilder builder3 = null;
		builder = new ExpressionBuilder(formula.getExp());
		for (String var : formula.getVar()) {
			builder.variable(var);
		}
		//builder.function(FormulaUtil.IF_ZERO);
		//builder.function(FormulaUtil.IF_ZERO_ELSE);
        formulaExp = builder.build();
        List<TypeFormulaCondition> lstCondition = formula.getCondition();
        if (lstCondition != null && !lstCondition.isEmpty()) {
        	lstConditionExp = new ArrayList<Expression>();
        	lstConditionExpVal = new ArrayList<Expression>();
        	lstConditionVal = new ArrayList<Expression>();
        	for (TypeFormulaCondition typeCondition : lstCondition) {
        		builder = new ExpressionBuilder(typeCondition.getExp());
        		builder2 = new ExpressionBuilder(typeCondition.getExpVal());
        		builder3 = new ExpressionBuilder(typeCondition.getValue());
        		for (String var : formula.getVar()) {
        			builder.variable(var);
        			builder2.variable(var);
        			builder3.variable(var);
        		}
        		lstConditionExp.add(builder.build());
        		lstConditionExpVal.add(builder2.build());
        		lstConditionVal.add(builder3.build());
			}
        }
	}

	protected double evaluateFormula(Map<String, Object> mapRow) {
		double value = 0;
		if (lstConditionExp != null && !lstConditionExp.isEmpty()) {
			double value2 = 0;
			double conditionExp = 0;
			double conditionExpVal = 0;
			Expression exp = null;
			TypeFormulaCondition condition = null;
			for (String var : formula.getVar()) {
				for (int i = 0; i < lstConditionExp.size(); i++) {
					value = Double.parseDouble(mapRow.get(var).toString());
					exp = lstConditionExp.get(i);
					exp.setVariable(var, value);
					exp = lstConditionExpVal.get(i);
					exp.setVariable(var, value);
					exp = lstConditionVal.get(i);
					exp.setVariable(var, value);
				}
			}
			for (int i = 0; i < lstConditionExp.size(); i++) {
				condition = formula.getCondition().get(i);
				exp = lstConditionExp.get(i);
				value = exp.evaluate();
				exp = lstConditionExpVal.get(i);
				value2 = exp.evaluate();
				if (condition.getType().equals("equalTo") && value == value2) {
					exp = lstConditionVal.get(i);
					return exp.evaluate();
				} else if (condition.getType().equals("notEqualTo") && value != value2) {
					exp = lstConditionVal.get(i);
					return exp.evaluate();
				} else if (condition.getType().equals("lesserThan") && value < value2) {
					exp = lstConditionVal.get(i);
					return exp.evaluate();
				} else if (condition.getType().equals("lesserEqualTo") && value <= value2) {
					exp = lstConditionVal.get(i);
					return exp.evaluate();
				} else if (condition.getType().equals("greaterThan") && value > value2) {
					exp = lstConditionVal.get(i);
					return exp.evaluate();
				} else if (condition.getType().equals("greaterEqualTo") && value >= value2) {
					exp = lstConditionVal.get(i);
					return exp.evaluate();
				}
			}
		} 
		for (String var : formula.getVar()) {
			formulaExp.setVariable(var, Double.parseDouble(mapRow.get(var).toString()));
		}
		return formulaExp.evaluate();
	}
}
