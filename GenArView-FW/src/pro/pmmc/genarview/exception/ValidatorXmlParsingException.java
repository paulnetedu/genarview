package pro.pmmc.genarview.exception;

public class ValidatorXmlParsingException extends RuntimeException {

	public ValidatorXmlParsingException(Exception e) {
		super(e);
	}
	
	public ValidatorXmlParsingException(String message) {
		super(message);
	}
}
