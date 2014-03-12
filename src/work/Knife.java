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
	private int numberOfIterations;
	private Nexus nexus;
	
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
		nexus.prepareOne(one);
		for (int i = 0; i < numberOfIterations; i++) {
			one.loadArchive(currentPass);
			for (int j = 0; j < numberOfItems; j++) {
				archive.extract(indices, false, callback);
				
				File extractedFile = callback.getCurrentFile();
				if (extractedFile.getTotalSpace() == 0) {
					extractedFile.delete();
					break;
				} else {
					passFound = true;
				}
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

	public static String getNextPassword(String currentPass) {
		// TODO Auto-generated method stub
		return null;
	}
}
