package pro.pmmc.genarview.functional.predicate;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Predicate;

import pro.pmmc.genarview.exception.ClassInstantiationException;
import pro.pmmc.genarview.exception.ConstructorInvocationException;
import pro.pmmc.genarview.xml.xsd.dsl.TypeHintAnd;
import pro.pmmc.genarview.xml.xsd.dsl.TypeHintMember;
import pro.pmmc.genarview.xml.xsd.dsl.TypeHintOr;
import pro.pmmc.genarview.xml.xsd.dsl.TypeHintPlain;
import pro.pmmc.genarview.xml.xsd.dsl.TypeHintSet;

public class CreatorPredicate {
	///*
	
	public static Predicate createPTypeHint(TypeHintMember typeFeature) {
		Class<?> predicateClass;
		if (typeFeature instanceof TypeHintAnd) {
			predicateClass = PTypeHintAnd.class;
		} else if (typeFeature instanceof TypeHintOr) {
			predicateClass = PTypeHintOr.class;
		} else {
			TypeHintPlain typeHintPlain = (TypeHintPlain) typeFeature;
			String predicateFullName = typeHintPlain.getClazz();
			try {
				predicateClass = Class.forName(predicateFullName);
			} catch (ClassNotFoundException e) {
				throw new ClassInstantiationException(predicateFullName);
			}
		}
		Class<?>[] types = new Class[1];
		types[0] = typeFeature.getClass();
		Object o;
		try {
			o = predicateClass.getConstructor(types).newInstance(typeFeature);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			StringBuilder sb = new StringBuilder();
			sb.append("Constructor not found in ").append("'").append(predicateClass.getName()).append("' with the parameters: ");
			for (int i = 0; i < types.length; i++) {
				sb.append("'").append(types[i].getName()).append("', ");
			}
			sb.append(" the cause message is '").append(e.getMessage()).append("'");
			throw new ConstructorInvocationException(sb.toString());
		}
		//System.out.println(o.getClass().getName());
		return (Predicate) o;
	}//*/
	
	public static void main(String[] args) throws Exception {
		TypeHintSet with = new TypeHintSet();
		Predicate tf = CreatorPredicate.createPTypeHint(with);
		
		System.out.println(tf.getClass());
		
	}
}
