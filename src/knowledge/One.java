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
	
	public One(String name, ArchiveFormat format, String url) {
		this.name = name;
		this.format = format;
		this.url = url;
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
	 * @throws FileNotFoundException Self explainatory.
	 * @throws SevenZipException
	 */
	public void loadArchive() throws FileNotFoundException, SevenZipException {
		File file = new File(url);
		RandomAccessFile raf = new RandomAccessFile(file, "r");
		IInStream inStream = new RandomAccessFileInStream(raf);
		ISevenZipInArchive archive = SevenZip.openInArchive(format, inStream);
		this.archive = archive;
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
}
