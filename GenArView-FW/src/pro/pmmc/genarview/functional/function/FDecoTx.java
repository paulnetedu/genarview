package pro.pmmc.genarview.functional.function;

import java.util.function.Function;

import org.neo4j.graphdb.Transaction;

import pro.pmmc.genarview.util.GraphUtil;

public class FDecoTx<T, R> extends FDeco<T, R> implements Function<T, R> {

	public FDecoTx(Function<T, R> function) {
		super(function);
	}

	@Override
	public R apply(T t) {
		Transaction tx = GraphUtil.getGraphService().beginTx();
		R r = function.apply(t);
		tx.success();
		tx.close();
		return r;
	}

}
