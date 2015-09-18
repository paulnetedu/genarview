package pro.pmmc.genarview.util;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {

	public static String[] extractWords(String someString) {
		return StringUtils.splitByCharacterTypeCamelCase(someString);
	}
	
	public static void main(String[] args) {
		String s = null;
		s = "HelloWorldBean";
		print(extractWords(s));
		s = "EJBWorldPAGE";
		print(extractWords(s));
		s = "faces-config";
		print(extractWords(s));
	}
	
	public static void print(String[] words) {
		for (int i = 0; i < words.length; i++) {
			System.out.println(words[i]);
		}
		System.out.println("---------");
	}
}
