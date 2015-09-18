package pro.pmmc.genarview.exception;

public class ClassInstantiationException extends RuntimeException {

	public ClassInstantiationException() {
		
	}
	
	public ClassInstantiationException(String className) {
		super("The class name '" + className + "' was not found.");
	}
	
}
