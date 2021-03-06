package knowledge;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

import net.sf.sevenzipjbinding.ArchiveFormat;
import net.sf.sevenzipjbinding.IInStream;
import net.sf.sevenzipjbinding.ISevenZipInArchive;
import net.sf.sevenzipjbinding.PropID;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.SevenZipException;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;

/**
 * One interfaces with archive. Speaks for archive.
 * @author X3N0-Life-Form
 *
 */
public class One extends AbstractKnowledge implements Comparable<One> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3355299628533734365L;


	/**
	 * One is an archive.
	 */
	private transient ISevenZipInArchive archive = null;
	
	private ArchiveFormat format;
	private String url;
	private String passKey;
	
	public One(String name, ArchiveFormat format, String url) {
		this.name = name;
		this.format = format;
		this.url = url;
		this.passKey = "";
	}

	public ISevenZipInArchive getArchive() {
		return archive;
	}

	public void setArchive(ISevenZipInArchive archive) {
		this.archive = archive;
	}

	@Override
	public int compareTo(One o) {
		return this.name.compareTo(o.name);
	}
	
	public boolean isArchiveLoaded() {
		return archive != null;
	}
	
	/**
	 * Loads One's archive. It can then be operated on.
	 * @throws FileNotFoundException Self explanatory.
	 * @throws SevenZipException
	 */
	public void loadArchive() throws FileNotFoundException, SevenZipException {
		File file = new File(url);
		RandomAccessFile raf = new RandomAccessFile(file, "r");
		IInStream inStream = new RandomAccessFileInStream(raf);
		ISevenZipInArchive archive = SevenZip.openInArchive(format, inStream);
		this.archive = archive;
	}
	
	/**
	 * Loads One's encrypted archive.
	 * @param passKey
	 * @return Newly loaded archive.
	 * @throws FileNotFoundException
	 * @throws SevenZipException
	 */
	public ISevenZipInArchive loadArchive(String passKey) throws FileNotFoundException, SevenZipException {
		File file = new File(url);
		RandomAccessFile raf = new RandomAccessFile(file, "r");
		IInStream inStream = new RandomAccessFileInStream(raf);
		ISevenZipInArchive archive = SevenZip.openInArchive(format, inStream, passKey);
		this.archive = archive;
		this.passKey = passKey;
		return archive;
	}

	@Override
	public String getFileExtension() {
		return ".one";
	}

	/**
	 * 
	 * @return Entry names
	 * @throws SevenZipException 
	 */
	public String[] getArchiveEntries() throws SevenZipException {
		int size = 0;
		size = archive.getNumberOfItems();
		
		String[] entries = new String[size];
		for (int index = 0; index < size; index++) {
			entries[index] = archive.getStringProperty(index, PropID.PATH);
		}
		
		return entries;
	}

	@Override
	public String toString() {
		return "One [format=" + format + ", url=" + url + ", passKey="
				+ passKey + "]";
	}
}
