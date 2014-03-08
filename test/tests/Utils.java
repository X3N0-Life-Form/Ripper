package tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utils {

	public static BufferedReader getBufferedReader(String filename)
			throws FileNotFoundException {
		File file = new File(filename);
		return getBufferedReader(file);
	}
	
	public static BufferedReader getBufferedReader(File file) throws FileNotFoundException {
		FileInputStream fist = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fist);
		BufferedReader buffer = new BufferedReader(isr);
		return buffer;
	}
	
	public static boolean compareFiles(File file_1, File file_2) {
		try {
			BufferedReader br1 = getBufferedReader(file_1);
			BufferedReader br2 = getBufferedReader(file_1);
			String line_1 = br1.readLine();
			String line_2 = br2.readLine();
			
			if (line_1 == null) {
				return false;
			}
			
			while (line_1 != null) {
				if (!line_1.equals(line_2)) {
					return false;
				}
				line_1 = br1.readLine();
				line_2 = br2.readLine();
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	
}
