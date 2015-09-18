package pro.pmmc.genarview.exception;

public class ValidatorXPathExpressionException extends RuntimeException {

	public ValidatorXPathExpressionException(Exception e) {
		super(e);
	}
	
	public ValidatorXPathExpressionException(String message) {
		super(message);
	}
}
