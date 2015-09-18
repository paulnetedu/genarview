package pro.pmmc.genarview.exception;

public class ConstructorInvocationException extends RuntimeException {

	public ConstructorInvocationException() {
		
	}
	
	public ConstructorInvocationException(String msg) {
		super(msg);
	}
	
}
