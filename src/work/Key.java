package work;

import java.io.File;
import java.io.IOException;

import knowledge.One;
import net.sf.sevenzipjbinding.ISevenZipInArchive;
import net.sf.sevenzipjbinding.SevenZipException;
import work.dirt.ExtractCallback;

/**
 * The one that opens.
 * @author X3N0-Life-Form
 *
 */
public class Key extends Worker {
	
	private One one;
	
	public Key(One one) {
		this.one = one;
	}

	@Override
	public void run() {
		try {
			System.out.println("[Key] Starting Key thread for " + one.getName());
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
		
		archive.extract(indices, false, callback);
		
		return folder;
	}

}
