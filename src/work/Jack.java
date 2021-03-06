package work;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.sevenzipjbinding.SevenZipException;
import utils.CypherException;
import utils.Necromancer;
import knowledge.KnowledgeException;
import knowledge.Nexus;
import knowledge.One;

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
	private static ExtractMode extractMode;
	
	protected enum ReadMode {
		OPTION,
		TARGET
	}
	private static ReadMode readMode = ReadMode.OPTION;
	
	
	private static String targetDirectory;
	private static List<String> targets = new ArrayList<String>();
	private static String destinationDirectory;
	private static String knowledgeDirectory;
	private static String[] range = new String[2];
	
	private static int numberOfThreads = 1;
	private static List<Worker> workers;
	

	/**
	 * Main entry point.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			dealWithArgs(args);
			findTargets();
			printRecap();
			// Final object creations
			workers = new ArrayList<Worker>(numberOfThreads);
			Nexus nexus;
			File f_nexus = new File(knowledgeDirectory + "/" + getKnowledgeName());
			if (knowledgeDirectory != null && f_nexus.exists()) {
				System.out.println("Knowledge: Found a nexus file: " + f_nexus.getName());
				Necromancer.setPathToKnowledge(knowledgeDirectory);
				nexus = (Nexus) Necromancer.unEarthKnowledge(getKnowledgeName());
			} else {
				System.out.println("Knowledge: No nexus file at " + f_nexus.getName());
				nexus = new Nexus();
			}
			
			// Extraction subroutine
			for (String target : targets) {
				System.out.println("Preparing to extract target " + target);
				One one = nexus.prepareOne(targetDirectory + "/" + target);
				switch (extractMode) {
				case KEY:
					Key key = new Key(one);
					workers.add(key);
					break;
				case KNIFE:
					for (int i = 0; i < numberOfThreads; i++) {
						Knife knife = prepareKnife(one, nexus);
						workers.add(knife);
					}
					break;
				case EVE:
					System.out.println("EVE mode not implemented");
					break;
				}
				
				for (Worker worker : workers) {
					Thread t = new Thread(worker);
					t.start();
				}
			}
		} catch (IOException | ClassNotFoundException | CypherException | SevenZipException | KnowledgeException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Finds targets at the target directory & add the to the targets list.
	 */
	protected static void findTargets() {
		File folder = new File(targetDirectory);
		targets.addAll(Arrays.asList(folder.list()));
	}

	/**
	 * Get the knowledge's name, according to the target directory name.
	 * @return directory name + ".nexus" file extension.
	 */
	public static String getKnowledgeName() {
		return targetDirectory.substring(targetDirectory.lastIndexOf('/')) + ".nexus";
	}

	/**
	 * Prepares a Knife according to Jack's parameters, namely the chosen range, number of threads & nexus data.
	 * @param one The one we are preparing to knife.
	 * @param nexus May contain data regarding past knifing attempts.
	 * @return
	 */
	protected static Knife prepareKnife(One one, Nexus nexus) {
		// TODO Auto-generated method stub
		return null;
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
			case "--displayKnowledge":
				System.out.println(knowledgeToString());
				break;
			case "--option-file":
				readOptions(args[++i]);
				break;
			case "--targetDirectory":
				targetDirectory = args[++i];
				break;
			case "--knowledgeDirectory":
				knowledgeDirectory = args[++i];
				break;
			case "--destinationDirectory":
				destinationDirectory = args[++i];
				break;
			case "--mode":
				extractMode = identifyMode(args[++i]);
				if (extractMode == ExtractMode.KNIFE)
					range = identifyRange(args[++i]);
				break;
			default:
				System.out.println("Unrecognised argument: " + args[i]);
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
		String res = "Our Knowledge:";
		File folder = new File(knowledgeDirectory);
		for (File currentFile : folder.listFiles()) {
			res += "\n";
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
		System.out.println("# Beginning recap");
		System.out.println("Target directory:\t" + targetDirectory);
		System.out.println("Destination directory:\t" + destinationDirectory);
		System.out.println("Knowledge directory:\t" + knowledgeDirectory);
		System.out.println("Targets:");
		for (String target : targets) {
			System.out.println("\t" + target);
		}
		System.out.println("# End recap");
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
			} else if (line.startsWith("#extract-mode:")) {
				extractMode = identifyMode(extractOption(line));
			} else if (line.startsWith("#range:")) {
				range = identifyRange(extractOption(line));
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
	
	/**
	 * Defaults to {@link ExtractMode}.KEY
	 * @param line
	 * @return
	 */
	protected static ExtractMode identifyMode(String line) {
		switch (line) {
		case "eve":
			return ExtractMode.EVE;
		case "key":
			return ExtractMode.KEY;
		case "knife":
			return ExtractMode.KNIFE;
		default:
			return ExtractMode.KEY;
		}
	}
	
	protected static String[] identifyRange(String line) {
		String[] range = new String[2];
		range[0] = line.substring(0, line.indexOf(":")).trim();
		range[1] = line.substring(line.indexOf(":") + 1).trim();
		return range;
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
	
	public static void setKnowledgeDirectory(String url) {
		knowledgeDirectory = url;
	}
	
	public static int getNumberOfThreads() {
		return numberOfThreads;
	}
	
	public static List<String> getTargets() {
		return targets;
	}
	
	public static String[] getRange() {
		return range;
	}
	
	public static ExtractMode getMode() {
		return extractMode;
	}

}
