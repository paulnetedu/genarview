package pro.pmmc.genarview.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collector;

import pro.pmmc.genarview.uml.CollectorImpl;

public class StreamUtil {

	public static<T,A,R> Collector<T,A,R> createCollector(Supplier<A> supplier,
            BiConsumer<A, T> accumulator,
            BinaryOperator<A> combiner) {
		return new CollectorImpl<T,A,R>(supplier, accumulator, combiner);
	}
	
	public static<T> Collector<List<T>,List<T>, List<T>> collectorList() {
		return createCollector(
		(Supplier<List<T>>) (() -> new ArrayList<T>()), 
		(BiConsumer<List<T>, List<T>>) List::addAll, 
		(List<T> o1, List<T> o2) -> {o1.addAll(o2); return o1;});
	}
	
	public static<T> Collector<Set<T>,Set<T>, Set<T>> collectorSet() {
		return createCollector(
		(Supplier<Set<T>>) HashSet<T>::new, 
		(BiConsumer<Set<T>, Set<T>>) Set::addAll, 
		(Set<T> o1, Set<T> o2) -> {o1.addAll(o2); return o1;});
		//.collect((Supplier<List<Node>>) ArrayList<Node>::new, 
		//	(BiConsumer<List<Node>, List<Node>>) List::addAll, 
			//(BiConsumer<List<Node>, List<Node>>) List::addAll)
	}
}
