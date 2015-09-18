package pro.pmmc.genarview.util;

import net.objecthunter.exp4j.function.Function;

public class FormulaUtil {

	public static final Function IF_ZERO = new Function("ifZero", 2) {
	    @Override
	    public double apply(double... args) {
	        return (args[0] == 0) ? args[1] : args[0];
	    }
	};
	
	public static final Function IF_ZERO_ELSE = new Function("ifZeroElse", 3) {
	    @Override
	    public double apply(double... args) {
	        return (args[0] == 0) ? args[1] : args[2];
	    }
	};
}
