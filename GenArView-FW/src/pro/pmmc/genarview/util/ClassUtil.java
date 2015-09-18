package pro.pmmc.genarview.util;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import pro.pmmc.genarview.exception.ClassInstantiationException;
import pro.pmmc.genarview.exception.ConstructorInvocationException;

public class ClassUtil {

	public static Object createObject(String className, String[] args) {
		Class<?> theClass;
		try {
			theClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new ClassInstantiationException(className);
		}
		int nargs = ((args != null) ? args.length : 0);   
		Class<?>[] types = new Class[nargs];
		for (int i = 0; i < types.length; i++) {
			types[i] = String.class;
		}
		Object o;
		try {
			o = theClass.getConstructor(types).newInstance(args);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			StringBuilder sb = new StringBuilder();
			sb.append("Constructor not found in ").append("'").append(theClass.getName()).append("' with the parameters: ");
			for (int i = 0; i < types.length; i++) {
				sb.append("'").append(types[i].getName()).append("', ");
			}
			sb.append(" the cause message is '").append(e.getMessage()).append("'");
			throw new ConstructorInvocationException(sb.toString());
		}
		//System.out.println(o.getClass().getName());
		return o;
	}
	
	public static Object createObject(String className, String someString, 
			List<String> args1, List<String> args2) {
		Class<?> theClass;
		try {
			theClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new ClassInstantiationException(className);
		}
		Class<?>[] types = new Class[3];
		types[0] = String.class;
		types[1] = List.class;
		types[2] = List.class;
		Object o;
		try {
			o = theClass.getConstructor(types).newInstance(someString, args1, args2);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			StringBuilder sb = new StringBuilder();
			sb.append("Constructor not found in ").append("'").append(theClass.getName()).append("' with the parameters: ");
			for (int i = 0; i < types.length; i++) {
				sb.append("'").append(types[i].getName()).append("', ");
			}
			sb.append(" the cause message is '").append(e.getMessage()).append("'");
			throw new ConstructorInvocationException(sb.toString());
		}
		//System.out.println(o.getClass().getName());
		return o;
	}
	
	public static Object createObject(String className, String someString, 
			List<String> args1, List<String> args2, List<String> args3) {
		Class<?> theClass;
		try {
			theClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new ClassInstantiationException(className);
		}
		Class<?>[] types = new Class[4];
		types[0] = String.class;
		types[1] = List.class;
		types[2] = List.class;
		types[3] = List.class;
		Object o;
		try {
			o = theClass.getConstructor(types).newInstance(someString, args1, args2, args3);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			StringBuilder sb = new StringBuilder();
			sb.append("Constructor not found in ").append("'").append(theClass.getName()).append("' with the parameters: ");
			for (int i = 0; i < types.length; i++) {
				sb.append("'").append(types[i].getName()).append("', ");
			}
			sb.append(" the cause message is '").append(e.getMessage()).append("'");
			throw new ConstructorInvocationException(sb.toString());
		}
		//System.out.println(o.getClass().getName());
		return o;
	}
}
