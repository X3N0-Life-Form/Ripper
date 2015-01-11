package work;

import java.io.File;
import java.io.IOException;

import knowledge.Nexus;
import knowledge.One;
import net.sf.sevenzipjbinding.ExtractOperationResult;
import net.sf.sevenzipjbinding.ISevenZipInArchive;
import net.sf.sevenzipjbinding.SevenZipException;
import work.dirt.SequentialOutStream;

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
	 * Prints a message, either evey loop or every 1% of iterations.
	 * @param counter Loop counter, important if you are in EVERY_PERCENT mode.
	 * @param message Message to print.
	 */
	public void printif(int counter, String message) {
		switch (printMode) {
		case EACH_LOOP:
			System.out.print(message);
			break;
		case EVERY_PERCENT:
			if (counter % ((double) numberOfIterations / (double) 100) == 0)
				System.out.print(message);
			break;
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
		//int[] indices = Worker.getSequentialIndices(numberOfItems);
		//ExtractCallback callback = new ExtractCallback(folder.getAbsolutePath(), one.getArchiveEntries());
		// Here is the part that is different from Key //
		
		String currentPass = startingPoint;
		boolean passFound = false;
		nexus.prepareOne(one);//might be redundant? TODO: look into this
		
		calculateNumberOfIterations();
		if (numberOfIterations == 0) {
			numberOfIterations = Integer.MAX_VALUE;
		}
		if (endPoint == null) {
			System.out.println("Preparing to run " + numberOfIterations + " attempts from " + currentPass);
		} else {
			System.out.println("Preparing to run to " + endPoint + " from " + currentPass);
		}
		
		for (int i = 0; i <= numberOfIterations && !currentPass.equals(endPoint); i++) {
			printif(i, "\nTrying password " + currentPass);
			//callback.setTextPassword(currentPass);
			archive = one.loadArchive(currentPass);
			for (int j = 0; j < numberOfItems; j++) {
				String itemPath = archive.getSimpleInterface().getArchiveItem(j).getPath();
				File file = new File(destination + one.getSimpleName() + "/" + itemPath);
				SequentialOutStream outstream = new SequentialOutStream(file);
				//archive.extract(indices, false, callback);
				ExtractOperationResult result = archive.extractSlow(j, outstream, currentPass);
				
				//File extractedFile = callback.getCurrentFile();
				if (result != ExtractOperationResult.OK/*extractedFile.length() == 0*/) {
					printif(i, "\t- KO " + result + " (attempt #" + (i + 1) + "/" + numberOfIterations + ")");
					//extractedFile.delete();
					break;
				} else if (!passFound) {
					printif(i, "\t- OK");
					passFound = true;
				}
				printif(i, "\n");
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
	
	public int getNumberOfIterations() {
		return numberOfIterations;
	}
	
	/**
	 * Calculates how much iterations it would take to get from
	 * starting point to end point.
	 */
	protected void calculateNumberOfIterations() {
		if (endPoint == null || startingPoint == null)
			return;
		int epi = endPoint.length() - 1;
		int spi = startingPoint.length() - 1;
		numberOfIterations = 0;
		int[] diffs = new int[endPoint.length()];
		
		// Gather intel
		for (int i = 0; i < endPoint.length(); i++) {
			int diff = 256;
			if (epi >= 0 && spi >= 0) {
				diff = endPoint.charAt(epi) - startingPoint.charAt(spi);
			} else if (spi < 0) {
				diff = endPoint.charAt(epi);
			}
			diffs[i] = diff;
			epi--;
			spi--;
		}
		
		// Use intel
		for (int i = 0; i < endPoint.length(); i++) {
			numberOfIterations += diffs[i] * Math.pow(256, i);
		}
	}
	
	
	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
		calculateNumberOfIterations();
	}
	
	

	public void setStartingPoint(String startingPoint) {
		this.startingPoint = startingPoint;
		calculateNumberOfIterations();
	}
}
