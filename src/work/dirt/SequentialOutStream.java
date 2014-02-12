package work.dirt;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import net.sf.sevenzipjbinding.ISequentialOutStream;
import net.sf.sevenzipjbinding.SevenZipException;

public class SequentialOutStream implements ISequentialOutStream, Closeable {
	
	private FileOutputStream fos;
	
	/**
	 * Creates the specified file and opens up an output stream.
	 * @param file
	 */
	public SequentialOutStream(File file) {
		try {
			file.createNewFile();
			fos = new FileOutputStream(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int write(byte[] data) throws SevenZipException {
		if (data.length == 0) {
			return 0;
		}
		
		try {
			fos.write(data);
		} catch (IOException e) {
			throw new SevenZipException("IOException:" + e);
		}
		return data.length;
	}

	@Override
	public void close() throws IOException {
		fos.close();
	}

}
