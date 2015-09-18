package pro.pmmc.genarview.util;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import pro.pmmc.genarview.exception.NotValidPathException;

public class PathsUtil {

	private Map<String, Map<String, Path>> sourcePaths;
	
	private static PathsUtil lastPathsUtil;
	
	public PathsUtil() {
		sourcePaths = new HashMap<String, Map<String, Path>>();
		lastPathsUtil = this;
	}
	
	public Collection<String> getGroupKeys() {
		return sourcePaths.keySet(); 
	}
	
	public Collection<String> getSourceKeys(String groupKey) {
		return sourcePaths.get(groupKey).keySet(); 
	}
	
	public Path getPathFromSources(String strPath, String separator, String extension) {
		for(String groupKey : sourcePaths.keySet()) {
			for(String k : sourcePaths.get(groupKey).keySet()) {
				try {
					Path path = getPathFromSources(groupKey, k, strPath, separator, extension);
					return path;
				} catch (NotValidPathException e) {}
			}
		}
		throw new NotValidPathException("Under the sources, cannot be found the path " + strPath);
	}
	
	public Path getPathFromSources(String groupKey, String sourceKey, String strPath, String separator, String extension) {
		Path path = null;
		Path sourcePath = sourcePaths.get(groupKey).get(sourceKey);
		String fullPath = sourcePath.toString() + "\\" + strPath.replaceAll("\\" + separator, "\\\\")
				+ extension;
		path = Paths.get(fullPath);
		if (!(new File(path.toString())).exists()) {
			throw new NotValidPathException("Cannot be found the path " + path.toString());
		}
		return path;
	}
	
	public List<Path> getDirectories() {
		Stream<Path> stream = null;
		Stream<Path> streamTmp = null;
		for (String groupKey : sourcePaths.keySet()) {
			for (String sourceKey : sourcePaths.get(groupKey).keySet()) {
				streamTmp = getDirectories(groupKey, sourceKey).stream();
				stream = (stream != null) ? Stream.concat(stream, streamTmp) : streamTmp; 
			}
		}
		return stream.collect(Collectors.toList());
	}
	
	public List<Path> getDirectories(String groupKey) {
		Stream<Path> stream = null;
		Stream<Path> streamTmp = null;
		for (String sourceKey : sourcePaths.get(groupKey).keySet()) {
			streamTmp = FileUtil.generateListDir(sourcePaths.get(groupKey).get(sourceKey)).stream();
			stream = (stream != null) ? Stream.concat(stream, streamTmp) : streamTmp; 
		}
		return stream.collect(Collectors.toList());
	}
	
	public List<Path> getDirectories(String groupKey, String sourceKey) {
		return FileUtil.generateListDir(sourcePaths.get(groupKey).get(sourceKey));
	}
	
	public List<Path> getDirectories(Collection<Path> paths) {
		return paths.parallelStream().flatMap(p -> FileUtil.generateListDir(p).stream())
			.collect(Collectors.toList());
	}

	public List<Path> getFiles() {
		Stream<Path> stream = null;
		Stream<Path> streamTmp = null;
		for (String groupKey : sourcePaths.keySet()) {
			for (String sourceKey : sourcePaths.get(groupKey).keySet()) {
				streamTmp = getFiles(groupKey, sourceKey).stream();
				stream = (stream != null) ? Stream.concat(stream, streamTmp) : streamTmp; 
			}
		}
		return stream.collect(Collectors.toList());
	}
	
	public List<Path> getFiles(String groupKey) {
		Stream<Path> stream = null;
		Stream<Path> streamTmp = null;
		for (String sourceKey : sourcePaths.get(groupKey).keySet()) {
			streamTmp = FileUtil.generateListFile(sourcePaths.get(groupKey).get(sourceKey)).stream();
			stream = (stream != null) ? Stream.concat(stream, streamTmp) : streamTmp; 
		}
		return stream.collect(Collectors.toList());
	}
	
	public List<Path> getFiles(String groupKey, String sourceKey) {
		return FileUtil.generateListFile(sourcePaths.get(groupKey).get(sourceKey));
	}
	
	public List<Path> getFiles(Collection<Path> paths) {
		return paths.parallelStream().flatMap(p -> FileUtil.generateListFile(p).stream())
			.collect(Collectors.toList());
	}
	
	public Path getSourcePath(String groupKey, String sourceKey) {
		return sourcePaths.get(groupKey).get(sourceKey);
	}

	public void putSourcePath(String groupKey, String sourceKey, String strPath) {
		Map<String, Path> map = sourcePaths.get(groupKey);
		if (map == null) {
			map = new HashMap<String, Path>();
			sourcePaths.put(groupKey, map);
		}
		map.put(sourceKey, Paths.get(strPath));
	}
	
	public static void main(String[] args) {
		String strPath = "C:\\Users\\paul\\git\\CursoJsfHibernate\\src\\java";
		PathsUtil pathUtil = new PathsUtil();
		//pathUtil.putSourcePath("source", strPath);
		Path path = pathUtil.getPathFromSources("com.jonathan.curso1.dao.UsuarioDAO", ".", ".java");
		System.out.println(path);
	}

	public static PathsUtil getLastPathsUtil() {
		return lastPathsUtil;
	}

	
}
