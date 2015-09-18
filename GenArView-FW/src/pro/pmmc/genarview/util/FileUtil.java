package pro.pmmc.genarview.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class FileUtil {

	public static int countLines(String filename) {
	    InputStream is = null;
	    try {
	    	is = new BufferedInputStream(new FileInputStream(filename));
	        byte[] c = new byte[1024];
	        int count = 0;
	        int readChars = 0;
	        boolean empty = true;
	        while ((readChars = is.read(c)) != -1) {
	            empty = false;
	            for (int i = 0; i < readChars; ++i) {
	                if (c[i] == '\n') {
	                    ++count;
	                }
	            }
	        }
	        return (count == 0 && !empty) ? 1 : count;
	    } catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
	        try {
	        	if (is != null) {
	        		is.close();
	        	}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
	    }
	}
	
	public static List<Path> generateListPath(String strPath) {
		return generateListPath(Paths.get(strPath));
	}
	
	public static List<Path> generateListPath(Path path) {
		DirectoryStream<Path> directoryStream = null;
		try {
			directoryStream = Files.newDirectoryStream(path);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		final List<Path> lstSource = new LinkedList<Path>();
		directoryStream.forEach(p -> {try {
			Files.walk(p).forEach(lstSource::add);
		} catch (Exception e) {throw new RuntimeException(e);
		}});
		return lstSource;
	}
	
	public static List<Path> generateListDir(String strPath) {
		return generateListDir(Paths.get(strPath));
	}
	
	public static List<Path> generateListDir(Path path) {
		DirectoryStream<Path> directoryStream = null;
		try {
			directoryStream = Files.newDirectoryStream(path);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		final List<Path> lstSource = new LinkedList<Path>();
		directoryStream.forEach(p -> {try {
			Files.walk(p).filter(Files::isDirectory).forEach(lstSource::add);
		} catch (Exception e) {throw new RuntimeException(e);
		}});
		return lstSource;
	}
	
	public static List<Path> generateListFile(String strPath) {
		return generateListFile(Paths.get(strPath));
	}
	
	public static List<Path> generateListFile(Path path) {
		DirectoryStream<Path> directoryStream = null;
		try {
			directoryStream = Files.newDirectoryStream(path);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		final List<Path> lstSource = new LinkedList<Path>();
		directoryStream.forEach(p -> {try {
			Files.walk(p).filter(f -> !Files.isDirectory(f)).forEach(lstSource::add);
		} catch (Exception e) {throw new RuntimeException(e);
		}});
		return lstSource;
	}
	
	public static String getText(Path path) {
		return getText(path.toString());
	}
	
	public static String getText(String path) {
		ReaderPlainBuffered rp = new ReaderPlainBuffered();
		String source = null;
    	try {
			source = rp.read(path);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
    	return source;
	}
	
	public static String getText(String filePath, String fileName) {
		ReaderPlainBuffered rp = new ReaderPlainBuffered();
		String source = null;
    	try {
			source = rp.read(filePath + "\\" + fileName);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
    	return source;
	}
	
	public static boolean hasFileExtension(String fileName, String extension) {
		boolean result = false;
		int index = fileName.toUpperCase().lastIndexOf(".");
		if ((extension == null || extension.trim().isEmpty()) && index == -1) {
			result = true;
		} else {
			String ext = fileName.substring(index + 1).toUpperCase();
			result = (extension.toUpperCase().equals(ext));
		}
		return result;
		
	}
	
	public static String extractLastName(String packageFullName) {
		int index = packageFullName.lastIndexOf(".");
		String parentFullName = null;
		if (index == -1) {
			index = 0;
		} else {
			parentFullName = packageFullName.substring(0, index++);
		}
		return packageFullName.substring(index);
	}
	
	public static String extractParentFullPackage(String packageFullName) {
		String parentFullName = null;
		int index = packageFullName.lastIndexOf(".");
		if (index != -1) {
			parentFullName = packageFullName.substring(0, index);
		}
		return parentFullName;
	}
	
	public static String toFullNameFromUmlQualified(String qualifiedName) {
		String fullName = qualifiedName.replaceAll("::", ".");
		fullName = fullName.substring(fullName.indexOf(".") + 1);
		return fullName;
	}
	
	public static String toFullPackageName(String directory, String basePath) {
		String fullName = directory.substring(basePath.length());
		fullName = (fullName.startsWith("\\")) ? fullName.substring(1) : fullName;
		fullName = fullName.replaceAll("\\\\", ".");
		return fullName;
	}
	
	public static void main(String[] args) {
		List<Path> lst = FileUtil.generateListPath("C:\\Users\\paul\\git\\tcc\\src");
		for(Path p : lst) {
			System.out.println(p);
		}
	}
}
