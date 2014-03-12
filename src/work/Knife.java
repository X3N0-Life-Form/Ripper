package work;

import java.io.File;
import java.io.IOException;

import knowledge.Nexus;
import knowledge.One;
import net.sf.sevenzipjbinding.ISevenZipInArchive;
import net.sf.sevenzipjbinding.SevenZipException;
import work.dirt.ExtractCallback;

/**
 * The one that opens forcibly.
 * @author X3N0-Life-Form
 *
 */
public class Knife extends Worker implements Runnable {
	
	private One one;
	private String startingPoint = "";
	private int numberOfIterations = 500;
	private Nexus nexus;
	private String endPoint = null;
	
	public Knife(One one, String startingPoint, Nexus nexus) {
		this.one = one;
		this.startingPoint = startingPoint;
		this.nexus = nexus;
	}

	@Override
	public void run() {
		try {
			extract();
		} catch (SevenZipException | IOException | WorkerException e) {
			//TODO: log error
		}
	}

	/**
	 * Extraction subroutine.
	 * @return Destination directory
	 * @throws SevenZipException
	 * @throws IOException Destination directory could not be created.
	 * @throws WorkerException Destination folder not set.
	 */
	public File extract() throws SevenZipException, IOException, WorkerException {
		if (destination == null) {
			throw new WorkerException("Destination folder is not set!");
		}
		
		one.loadArchive();
		File folder = createFolder(one);
		if (!folder.exists()) {
			throw new IOException("Destination directory could not be created.");
		}
		
		ISevenZipInArchive archive = one.getArchive();
		int numberOfItems = archive.getNumberOfItems();
		int[] indices = Worker.getSequentialIndices(numberOfItems);
		ExtractCallback callback = new ExtractCallback(folder.getAbsolutePath(), one.getArchiveEntries());
		// Here is the part that is different from Key //
		
		String currentPass = startingPoint;
		boolean passFound = false;
		nexus.prepareOne(one);//might be redundant? TODO: look into this
		if (endPoint == null) {
			System.out.println("Preparing to run " + numberOfIterations + " attempts from " + currentPass);
		} else {
			System.out.println("Preparing to run to " + endPoint + " from " + currentPass);
		}
		for (int i = 0; i < numberOfIterations && !currentPass.equals(endPoint); i++) {
			System.out.print("\nTrying password " + currentPass);
			one.loadArchive(currentPass);
			for (int j = 0; j < numberOfItems; j++) {
				archive.extract(indices, false, callback);
				
				File extractedFile = callback.getCurrentFile();
				if (extractedFile.length() == 0) {
					System.out.print("\t- KO (attempt #" + i + ")");
					extractedFile.delete();
					break;
				} else {
					System.out.print("\t- OK");
					passFound = true;
				}
				System.out.println();
			}
			
			if (passFound) {
				nexus.getTruth().addValidPassword(currentPass);
				break;
			} else {
				currentPass = getNextPassword(currentPass);
			}
		}
		
		if (!passFound) {
			nexus.saveWords(one, startingPoint, currentPass);
		}
		
		return folder;
	}

	/**
	 * 
	 * @param currentPass
	 * @return password that comes right after currentPass
	 */
	public static String getNextPassword(String currentPass) {
		char[] string = currentPass.toCharArray();
		boolean bump = true;
		for (int i = string.length - 1; i >= 0; i--) {
			if (string[i] < 255) {
				string[i]++;
				bump = false;
				break;
			} else {
				string[i] = 0;
			}
		}
		
		String result = String.copyValueOf(string);
		if (bump) {
			char o = 0;
			result = o + result;
		}
		return result;
	}
	
	public void setNumberOfIterations(int numberOfIterations) {
		this.numberOfIterations = numberOfIterations;
	}
	
	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
		numberOfIterations = Integer.MAX_VALUE;
	}
}
