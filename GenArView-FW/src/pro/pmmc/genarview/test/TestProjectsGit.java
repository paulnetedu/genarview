package pro.pmmc.genarview.test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import pro.pmmc.genarview.util.FileUtil;

public class TestProjectsGit {

	public static void main(String[] args) {
		String[] paths = getPaths();
		for (int i = 0; i < paths.length; i++) {
			String path = paths[i];
			List<Path> lstPathSource = FileUtil.generateListPath(path);
			List<Path> lstSourceDir = lstPathSource.stream()
					.filter(p -> (Files.isDirectory(p))).collect(Collectors.toList());
			List<Path> lstSourceFile = lstPathSource.stream()
					.filter(p -> (!Files.isDirectory(p) && p.toString().toUpperCase().endsWith(".JAVA"))).collect(Collectors.toList());
			int locs = lstSourceFile.stream().map(p -> FileUtil.countLines(p.toString()))
				.reduce(0, (a, b) -> a+b).intValue();
			int end = path.indexOf("\\src");
			String name = path.substring(0, end);
			name = path.substring(name.lastIndexOf("\\") + 1, name.length());
			System.out.println(
					name + "," + path + "," + (lstSourceDir.size()-1) + "," + lstSourceFile.size() + "," + locs);
		}

	}
	
	public static String[] getPaths() {
		String[] paths = new String[] {
				"C:\\Users\\paul\\git\\faces\\Proyectos\\03-security-solution\\src\\main\\java",
				"C:\\Users\\paul\\git\\camunda-consulting\\one-time-examples\\acm-showcase\\src\\main\\java",
				"C:\\Users\\paul\\git\\architect\\src\\main\\java",
				"C:\\Users\\paul\\git\\auction\\src\\main\\java",
				"C:\\Users\\paul\\git\\camunda-consulting\\one-time-examples\\bestellprozess-java-ee6\\src\\main\\java",
				"C:\\Users\\paul\\git\\canchita\\src",
				"C:\\Users\\paul\\git\\introBigData\\cassandra\\src\\main\\java",
				"C:\\Users\\paul\\git\\plato\\idp\\src\\main\\java",
				"C:\\Users\\paul\\git\\camunda-consulting\\showcases\\invoice-en\\src\\main\\java",
				"C:\\Users\\paul\\git\\netconf\\IPFIXConfig\\src",
				"C:\\Users\\paul\\git\\javaee6-sample\\src\\main\\java",
				"C:\\Users\\paul\\git\\jeedemo\\src\\main\\java",
				"C:\\Users\\paul\\git\\faces\\Proyectos\\jsf-booking\\src\\main\\java",
				"C:\\Users\\paul\\git\\jsf_eficaz\\src\\main\\java",
				"C:\\Users\\paul\\git\\plato\\kbrowser\\src\\main\\java",
				"C:\\Users\\paul\\git\\javaee7-hol\\solution\\movieplex7\\src\\main\\java",
				"C:\\Users\\paul\\git\\Musico\\musico-war\\src",
				"C:\\Users\\paul\\git\\camunda-consulting\\showcases\\order-confirmation-rules\\src\\main\\java",
				"C:\\Users\\paul\\git\\plato\\plato\\src\\main\\java",
				"C:\\Users\\paul\\git\\faces\\Proyectos\\primefaces-spring-security-hibernate\\src\\main\\java",
				"C:\\Users\\paul\\git\\master-java\\practicas\\proyecto\\project-ui\\src\\main\\java",
				"C:\\Users\\paul\\git\\faces\\Proyectos\\roo-jsf\\src\\main\\java",
				"C:\\Users\\paul\\git\\faces\\Proyectos\\scrumboard\\src\\main\\java",
				"C:\\Users\\paul\\git\\showcase\\src\\main\\java",
				"C:\\Users\\paul\\git\\socialize-workshop\\src\\main\\java",
				"C:\\Users\\paul\\git\\tcc\\src",
				"C:\\Users\\paul\\git\\ticket2rock\\src\\main\\java",
				"C:\\Users\\paul\\git\\TopicIndex\\src",
				"C:\\Users\\paul\\git\\faces\\Proyectos\\upcdew-deportivoapp\\src\\java",
				"C:\\Users\\paul\\git\\webservice\\src\\main\\java"
		};
		return paths;
	}

}
