package pro.pmmc.genarview.functional.consumer;

import java.util.function.Consumer;

public class CDeco<T> {

	protected Consumer<T> consumer;

	public CDeco(Consumer<T> consumer) {
		super();
		this.consumer = consumer;
	}

	public Consumer<T> getConsumer() {
		return consumer;
	}

	public void setConsumer(Consumer<T> consumer) {
		this.consumer = consumer;
	}
	
	

}
