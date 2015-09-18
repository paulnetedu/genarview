package pro.pmmc.genarview.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReaderPlainBuffered implements ReaderPlain {

	private int bufferSize = 1024;
	
	@Override
	public String read(String filePath) throws IOException {
		StringBuilder fileData = new StringBuilder(1000);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		char[] buf = new char[bufferSize];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[bufferSize];
		}
		reader.close();
		return fileData.toString();
	}

}
