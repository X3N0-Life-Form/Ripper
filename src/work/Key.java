package work;

import java.io.FileNotFoundException;

import work.dirt.ExtractCallback;
import net.sf.sevenzipjbinding.ISevenZipInArchive;
import net.sf.sevenzipjbinding.SevenZipException;
import knowledge.One;

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
			one.loadArchive();
			ISevenZipInArchive archive = one.getArchive();
			int numberOfItems = archive.getNumberOfItems();
			int[] indices = Worker.getSequentialIndices(numberOfItems);
			ExtractCallback callback = new ExtractCallback(one.getName(), one.getArchiveEntries());
			for (int i = 0; i < numberOfItems; i++) {
				archive.extract(indices, false, callback);
			}
		} catch (FileNotFoundException | SevenZipException e) {
			//TODO: log error
		}
	}

}
