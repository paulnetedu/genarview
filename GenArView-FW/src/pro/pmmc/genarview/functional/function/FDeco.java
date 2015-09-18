package pro.pmmc.genarview.functional.function;

import java.util.function.Function;

public class FDeco<T, R>  {

	protected Function<T, R> function;

	public FDeco(Function<T, R> function) {
		super();
		this.function = function;
	}

	public Function<T, R> getFunction() {
		return function;
	}

	public void setFunction(Function<T, R> function) {
		this.function = function;
	}
	
	
}
