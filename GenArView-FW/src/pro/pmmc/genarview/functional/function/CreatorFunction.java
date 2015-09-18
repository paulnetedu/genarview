package pro.pmmc.genarview.functional.function;

import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.neo4j.graphdb.Node;

import pro.pmmc.genarview.xml.xsd.dsl.TypeFunction;



public class CreatorFunction {

	@SuppressWarnings({"rawtypes", "unchecked"})
	public Function createFunction(TypeFunction tf) {
		List lstArg = new LinkedList();
		return createFunction(tf, lstArg);
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	public Function createFunction(TypeFunction tf, Object param1) {
		List lstArg = new LinkedList();
		lstArg.add(param1);
		return createFunction(tf, lstArg);
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	public Function createFunction(TypeFunction tf, Object param1, Object param2) {
		List lstArg = new LinkedList();
		lstArg.add(param1);
		lstArg.add(param2);
		return createFunction(tf, lstArg);
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	public Function createFunction(TypeFunction tf, List lstArg) {
		List<List<String>> lstLst = new LinkedList<List<String>>();
		//lstArg.clear();
		if (tf.getArg() != null && !tf.getArg().isEmpty()) { // String
			lstArg.addAll(tf.getArg());
		}
		if (tf.getArgs() != null && !tf.getArgs().isEmpty()) { // List<String>
			lstLst = tf.getArgs().stream().map(a -> a.getArg()).collect(Collectors.toList());
			lstArg.addAll(lstLst);
		}
		String fullName = tf.getClazz();
		try {
			Function f = null;
			Class classF = Class.forName(fullName);
			if (lstArg == null || lstArg.isEmpty()) {
				f = (Function) classF.newInstance();
			} else {
				Class[] types = new Class[lstArg.size()];
				for (int i = 0; i < lstArg.size(); i++) {
					types[i] = lstArg.get(i).getClass();
					//System.out.println(types[i].getName());
				}
				f = (Function) classF.getConstructor(types).newInstance(lstArg.toArray());
			}
			return f;
		} catch (ClassNotFoundException e) {throw new RuntimeException(e);
		} catch (InstantiationException e) {throw new RuntimeException(e);
		} catch (IllegalAccessException e) {throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {throw new RuntimeException(e);
		} catch (InvocationTargetException e) {throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {throw new RuntimeException(e);
		} catch (SecurityException e) {throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	public BinaryOperator createBinaryOperator(TypeFunction tf, Object param1) {
		List lstArg = new LinkedList();
		lstArg.add(param1);
		return createBinaryOperator(tf, lstArg);
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	public BinaryOperator createBinaryOperator(TypeFunction tf, List lstArg) {
		List<List<String>> lstLst = new LinkedList<List<String>>();
		//lstArg.clear();
		if (tf.getArg() != null && !tf.getArg().isEmpty()) { // String
			lstArg.addAll(tf.getArg());
		}
		if (tf.getArgs() != null && !tf.getArgs().isEmpty()) { // List<String>
			lstLst = tf.getArgs().stream().map(a -> a.getArg()).collect(Collectors.toList());
			lstArg.addAll(lstLst);
		}
		String fullName = tf.getClazz();
		try {
			BinaryOperator bo = null;
			Class classF = Class.forName(fullName);
			if (lstArg == null || lstArg.isEmpty()) {
				bo = (BinaryOperator) classF.newInstance();
			} else {
				Class[] types = new Class[lstArg.size()];
				for (int i = 0; i < lstArg.size(); i++) {
					types[i] = lstArg.get(i).getClass();
					//System.out.println(types[i].getName());
				}
				bo = (BinaryOperator) classF.getConstructor(types).newInstance(lstArg.toArray());
			}
			return bo;
		} catch (ClassNotFoundException e) {throw new RuntimeException(e);
		} catch (InstantiationException e) {throw new RuntimeException(e);
		} catch (IllegalAccessException e) {throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {throw new RuntimeException(e);
		} catch (InvocationTargetException e) {throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {throw new RuntimeException(e);
		} catch (SecurityException e) {throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	public static Function<Map<String, Object>, Map<String, Object>> createFmap(String fullName, List lstArg) {
		try {
			Function<Map<String, Object>, Map<String, Object>> f = null;
			Class classF = Class.forName(fullName);
			if (lstArg == null || lstArg.isEmpty()) {
				f = (Function<Map<String, Object>, Map<String, Object>>) classF.newInstance();
			} else {
				Class[] types = new Class[lstArg.size()];
				for (int i = 0; i < lstArg.size(); i++) {
					types[i] = lstArg.get(i).getClass();
					//System.out.println(types[i].getName());
				}
				f = (Function<Map<String, Object>, Map<String, Object>>) classF.getConstructor(types).newInstance(lstArg.toArray());
			}
			return f;
		} catch (ClassNotFoundException e) {throw new RuntimeException(e);
		} catch (InstantiationException e) {throw new RuntimeException(e);
		} catch (IllegalAccessException e) {throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {throw new RuntimeException(e);
		} catch (InvocationTargetException e) {throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {throw new RuntimeException(e);
		} catch (SecurityException e) {throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	public static Function<Path, Stream<Node>> createFpn(String fullName, List lstArg) {
		try {
			Function<Path, Stream<Node>> f = null;
			Class classF = Class.forName(fullName);
			if (lstArg == null || lstArg.isEmpty()) {
				f = (Function<Path, Stream<Node>>) classF.newInstance();
			} else {
				Class[] types = new Class[lstArg.size()];
				for (int i = 0; i < lstArg.size(); i++) {
					types[i] = lstArg.get(i).getClass();
				}
				f = (Function<Path, Stream<Node>>) classF.getConstructor(types).newInstance(lstArg.toArray());
			}
			return f;
		} catch (ClassNotFoundException e) {throw new RuntimeException(
					(new StringBuilder(fullName)).append(" could not be instantiated with ").append(lstArg.size()).append(" arguments: ").append(lstArg).toString());
		} catch (InstantiationException e) {throw new RuntimeException((new StringBuilder(fullName)).append(" could not be instantiated with ").append(lstArg.size()).append(" arguments: ").append(lstArg).toString());
		} catch (IllegalAccessException e) {throw new RuntimeException((new StringBuilder(fullName)).append(" could not be instantiated with ").append(lstArg.size()).append(" arguments: ").append(lstArg).toString());
		} catch (IllegalArgumentException e) {throw new RuntimeException((new StringBuilder(fullName)).append(" could not be instantiated with ").append(lstArg.size()).append(" arguments: ").append(lstArg).toString());
		} catch (InvocationTargetException e) {throw new RuntimeException((new StringBuilder(fullName)).append(" could not be instantiated with ").append(lstArg.size()).append(" arguments: ").append(lstArg).toString());
		} catch (NoSuchMethodException e) {throw new RuntimeException((new StringBuilder(fullName)).append(" could not be instantiated with ").append(lstArg.size()).append(" arguments: ").append(lstArg).toString());
		} catch (SecurityException e) {throw new RuntimeException((new StringBuilder(fullName)).append(" could not be instantiated with ").append(lstArg.size()).append(" arguments: ").append(lstArg).toString());
		}
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	public static Function<Path, Stream<Path>> createFpp(String fullName, List lstArg) {
		try {
			Function<Path, Stream<Path>> f = null;
			Class classF = Class.forName(fullName);
			if (lstArg == null || lstArg.isEmpty()) {
				f = (Function<Path, Stream<Path>>) classF.newInstance();
			} else {
				Class[] types = new Class[lstArg.size()];
				for (int i = 0; i < lstArg.size(); i++) {
					types[i] = lstArg.get(i).getClass();
				}
				f = (Function<Path, Stream<Path>>) classF.getConstructor(types).newInstance(lstArg.toArray());
			}
			return f;
		} catch (ClassNotFoundException e) {throw new RuntimeException(e);
		} catch (InstantiationException e) {throw new RuntimeException(e);
		} catch (IllegalAccessException e) {throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {throw new RuntimeException(e);
		} catch (InvocationTargetException e) {throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {throw new RuntimeException(e);
		} catch (SecurityException e) {throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	public static Function<Node, Stream<Node>> createFnn(String fullName, List lstArg) {
		try {
			Function<Node, Stream<Node>> f = null;
			Class classF = Class.forName(fullName);
			if (lstArg == null || lstArg.isEmpty()) {
				f = (Function<Node, Stream<Node>>) classF.newInstance();
			} else {
				Class[] types = new Class[lstArg.size()];
				for (int i = 0; i < lstArg.size(); i++) {
					types[i] = lstArg.get(i).getClass();
					//System.out.println(types[i].getName());
				}
				f = (Function<Node, Stream<Node>>) classF.getConstructor(types).newInstance(lstArg.toArray());
			}
			return f;
		} catch (ClassNotFoundException e) {throw new RuntimeException(e);
		} catch (InstantiationException e) {throw new RuntimeException(e);
		} catch (IllegalAccessException e) {throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {throw new RuntimeException(e);
		} catch (InvocationTargetException e) {throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {throw new RuntimeException(e);
		} catch (SecurityException e) {throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) {
		List lstArg = new ArrayList();
		lstArg.add("/web-app/ejb-ref");
		lstArg.add("home");
		List<String> lst = new ArrayList<String>();
		lst.add("remote");
		lstArg.add(lst);
		System.out.println(lstArg);
		CreatorFunction.createFpn("pro.pmmc.genarview.function.FpnXPathMember", lstArg);
	}
}
