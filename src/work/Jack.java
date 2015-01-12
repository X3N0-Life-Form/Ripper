package work;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Main entry point. Launches everything.
 * @author X3N0-Life-Form
 *
 */
public class Jack {
	
	public enum Mode {
		KEY,
		KNIFE,
		EVE
	}
	
	private static String targetDirectory;
	private static List<String> targets;
	private static String destinationDirectory;
	private static String knowledgeDirectory;

	public static void main(String[] args) {
		try {
			dealWithArgs(args);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	protected static void dealWithArgs(String[] args) throws IOException {
		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			
			switch (arg) {
			case "--help":
				displayHelp();
				break;
			case "--option-file":
				readOptions(args[++i]);
				break;
			case "--targetDirectory":
				targetDirectory = args[++i];
				break;
			}
			
		}
	}

	public static void displayHelp() {
		// TODO Auto-generated method stub
		
	}
	
	public static void printRecap() {
		
	}

	public static void readOptions(String fileURL) throws IOException {
		File file = new File(fileURL);
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader reader = new BufferedReader(isr);
		String line = null;
		while ((line = reader.readLine()) != null) {
			if (line.startsWith("#target-directory:")) {
				
			} else if (line.startsWith("#destination-directory:")) {
				
			} else if () {
				
			}
			
		}
	}

}
