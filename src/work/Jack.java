package work;

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

	public static void main(String[] args) {
		dealWithArgs(args);
		
		
	}

	protected static void dealWithArgs(String[] args) {
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

	protected static void displayHelp() {
		// TODO Auto-generated method stub
		
	}

	protected static void readOptions(String fileURL) {
		// TODO Auto-generated method stub
		
	}

}
