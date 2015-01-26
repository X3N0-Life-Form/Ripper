package work;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import knowledge.Nexus;

/**
 * Main entry point. Launches everything.
 * @author X3N0-Life-Form
 *
 */
public class Jack {
	
	/**
	 * Singleton class, do not instantiate.
	 */
	private Jack() {}
	
	public enum ExtractMode {
		KEY,
		KNIFE,
		EVE
	}
	
	protected enum ReadMode {
		OPTION,
		TARGET
	}
	private static ReadMode readMode = ReadMode.OPTION;
	
	
	private static String targetDirectory;
	private static List<String> targets = new ArrayList<String>();
	private static String destinationDirectory;
	private static String knowledgeDirectory;
	
	private static int numberOfThreads = 1;

	/**
	 * Main entry point.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			dealWithArgs(args);
			//TODO: do shit with args
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Interprets the program's arguments.
	 * @param args Argument list.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	protected static void dealWithArgs(String[] args) throws IOException, ClassNotFoundException {
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
			case "--displayKnowledge":
				System.out.println(knowledgeToString());
				break;
			}
			
		}
	}
	
	/**
	 * Analyzes knowledge and translates it as a String. Requires the knowledge directory to be set.
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static String knowledgeToString() throws IOException, ClassNotFoundException { //TODO: maybe delay knowledge display until we get the knowledge directory.
		String res = "";
		File folder = new File(knowledgeDirectory);
		for (File currentFile : folder.listFiles()) {
			FileInputStream fis = new FileInputStream(currentFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			String ext = currentFile.getName().substring(currentFile.getName().lastIndexOf('.'));
			switch (ext) {
			case ".nexus":
				Nexus nexus = (Nexus) ois.readObject();
				res += nexus;
				break;
			default:
				res += "Unrecognised knowledge: " + ext;
				break;
			}
			ois.close();
		}
		return res;
	}

	public static void displayHelp() {
		// TODO Auto-generated method stub
		
	}
	
	public static void printRecap() {
		System.out.println("Target directory: " + targetDirectory);
		System.out.println("Destination directory: " + destinationDirectory);
		System.out.println("Knowledge directory: " + knowledgeDirectory);
		System.out.println("Targets:");
		for (String target : targets) {
			System.out.println("\t" + target);
		}
	}

	/**
	 * Reads an option file and sets attributes accordingly.
	 * @param fileURL URL to option file.
	 * @throws IOException
	 */
	public static void readOptions(String fileURL) throws IOException {
		File file = new File(fileURL);
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader reader = new BufferedReader(isr);
		String line = null;
		while ((line = reader.readLine()) != null) {
			if (line.startsWith("#")) readMode = ReadMode.OPTION;
			
			System.out.println(line);
			if (readMode == ReadMode.TARGET) {
				targets.add(line.trim());
			} else if (line.startsWith("#target-directory:")) {
				targetDirectory = extractOption(line);
			} else if (line.startsWith("#destination-directory:")) {
				destinationDirectory = extractOption(line);
			} else if (line.startsWith("#knowledge-directory:")) {
				knowledgeDirectory = extractOption(line);
			} else if (line.startsWith("#targets"))  {
				readMode = ReadMode.TARGET;
			} else if (line.startsWith("#threads:")) {
				numberOfThreads = Integer.parseInt(extractOption(line));
			} else {
				System.out.println("Unrecognised option: " + line);
			}
			
		}
		reader.close();
	}
	
	/**
	 * 
	 * @param line
	 * @return
	 */
	protected static String extractOption(String line) {
		return line.substring(line.indexOf(":") + 1).trim();
	}
	
	///
	/// Getters/Setters
	///
	
	public static String getTargetDirectory() {
		return targetDirectory;
	}
	
	public static String getDestinationDirectory() {
		return destinationDirectory;
	}
	
	public static String getKnowledgeDirectory() {
		return knowledgeDirectory;
	}
	
	public static int getNumberOfThreads() {
		return numberOfThreads;
	}
	
	public static List<String> getTargets() {
		return targets;
	}

}
