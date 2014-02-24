package work;

import java.io.File;
import java.io.IOException;

import knowledge.One;

/**
 * A Worker makes stuff happen.
 * @author X3N0-Life-Form
 *
 */
public abstract class Worker {

	protected String destination;
	
	public void setDestination(String url) {
		this.destination = url;
		if (!destination.endsWith("/")) {
			destination += "/";
		}
	}
	
	public String getDestination() {
		return destination;
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
	 * Creates the specified folder at the destination directory.
	 * @param name
	 * @throws IOException
	 */
	public File createFolder(String name) throws IOException {
		if (!name.endsWith("/")) {
			name += "/";
		}
		File folder = new File(destination + name);
		folder.mkdirs();
		return folder;
	}

	/**
	 * Creates a folder for the One at the destination directory.
	 * @param one
	 * @return
	 * @throws IOException
	 */
	public File createFolder(One one) throws IOException {
		String trimedUrl = one.getName();
		int i = trimedUrl.lastIndexOf("/");
		if (trimedUrl.endsWith("/")) {
			trimedUrl = trimedUrl.substring(0, i);
			i = trimedUrl.lastIndexOf("/");
		}
		trimedUrl = trimedUrl.substring(++i);
		return createFolder(trimedUrl);
	}
	
}
