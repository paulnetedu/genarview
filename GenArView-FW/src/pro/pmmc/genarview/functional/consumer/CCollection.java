package pro.pmmc.genarview.functional.consumer;

import java.util.Collection;
import java.util.function.Consumer;

public class CCollection<T> implements Consumer<T> {

	private Collection<T> collection;
	
	public CCollection(Collection<T> collection) {
		super();
		this.collection = collection;
	}

	@Override
	public void accept(T t) {
		collection.add(t);
	}
	
	public static Consumer<?> createCCollection(Collection<?> collection) {
		return new CCollection<>(collection);
	}
}
