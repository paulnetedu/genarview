package pro.pmmc.genarview.functional.consumer;

import java.util.function.Consumer;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import pro.pmmc.genarview.util.GraphUtil;

public class CDecoTx<T> extends CDeco<T> implements Consumer<T> {

	public CDecoTx(Consumer<T> consumer) {
		super(consumer);
	}

	@Override
	public void accept(T t) {
		Transaction tx = GraphUtil.getGraphService().beginTx();
		consumer.accept(t);
		tx.success();
		tx.close();
	}

	

	
}
