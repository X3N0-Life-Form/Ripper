package work;

import java.io.File;
import java.io.IOException;

/**
 * A Worker makes stuff happen.
 * @author X3N0-Life-Form
 *
 */
public abstract class Worker {

	protected String destination;
	
	public void setDestination(String url) {
		this.destination = url;
	}

	/**
	 * 
	 * @param numberOfItems
	 * @return int[] containing 1, 2, 3, ..., numberOfItems.
	 */
	public static int[] getSequentialIndices(int numberOfItems) {
		int[] indices = new int[numberOfItems];
		for (int i = 0; i < numberOfItems; i++) {
			indices[i] = i;
		}
		return indices;
	}

	/**
	 * Creates the specified folder.
	 * @param name
	 * @throws IOException
	 */
	public static void createFolder(String name) throws IOException {
		if (!name.endsWith("/")) {//TODO:stuff with destination (see above)
			name += "/";
		}
		File folder = new File(name);
		if (folder.exists() && !folder.isDirectory()) {
			folder.mkdir();
		}
	}
	
}
