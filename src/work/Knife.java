package work;

import java.io.File;
import java.io.IOException;

import work.dirt.ExtractCallback;
import knowledge.One;
import net.sf.sevenzipjbinding.ISevenZipInArchive;
import net.sf.sevenzipjbinding.SevenZipException;

/**
 * The one that opens forcibly.
 * @author X3N0-Life-Form
 *
 */
public class Knife extends Worker implements Runnable {
	
	private One one;

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
		//TODO: reload archive with password
		// attempt extraction
		// check extracted file size
		// if not good, iterate
		// else store password
		for (int i = 0; i < numberOfItems; i++) {
			archive.extract(indices, false, callback);
		}
		
		return folder;
	}
}
