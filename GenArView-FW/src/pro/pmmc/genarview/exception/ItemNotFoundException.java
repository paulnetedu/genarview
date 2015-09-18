package pro.pmmc.genarview.exception;

public class ItemNotFoundException extends RuntimeException {

	public ItemNotFoundException(String itemName) {
		super("The item '" + itemName + "' was not found.");
	}
	
	public ItemNotFoundException(String itemName, String innerItemName) {
		super("The item '" + innerItemName + "' was not found inside '" + itemName + "'");
	}
}
