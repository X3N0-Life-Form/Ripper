package work;

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
public class Key implements Runnable {
	
	private One one;
	
	public Key(One one) {
		this.one = one;
	}

	@Override
	public void run() {
		try {
			extract();
		} catch (SevenZipException | IOException e) {
			//TODO: log error
		}
	}

	/**
	 * Extraction subroutine.
	 * @throws SevenZipException
	 * @throws IOException 
	 */
	public void extract() throws SevenZipException, IOException {
		one.loadArchive();
		ISevenZipInArchive archive = one.getArchive();
		int numberOfItems = archive.getNumberOfItems();
		int[] indices = Worker.getSequentialIndices(numberOfItems);
		System.out.println(one.getName());
		Worker.createFolder(one.getName());
		ExtractCallback callback = new ExtractCallback(one.getName(), one.getArchiveEntries());
		for (int i = 0; i < numberOfItems; i++) {
			archive.extract(indices, false, callback);
		}
	}

}
