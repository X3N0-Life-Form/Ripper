package knowledge;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.TreeMap;

import utils.Cypher;
import utils.CypherException;
import net.sf.sevenzipjbinding.ArchiveFormat;
import net.sf.sevenzipjbinding.IInStream;
import net.sf.sevenzipjbinding.ISevenZipInArchive;
import net.sf.sevenzipjbinding.PropID;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.SevenZipException;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;
import net.sf.sevenzipjbinding.impl.VolumedArchiveInStream;

/**
 * A nexus of knowledge.
 * @author X3N0-Life-Form
 *
 */
public class Nexus {

	/**
	 * One has a Words.
	 */
	private Map<One, Words> chamberOfWords = new TreeMap<One, Words>();
	/**
	 * Universal Truth.
	 */
	private Truth truth = new Truth();
	
	
	
	
	public Map<One, Words> getChamberOfWords() {
		return chamberOfWords;
	}
	
	public Truth getTruth() {
		return truth;
	}
	
	/**
	 * Attempts to open One and put it in the chamberOfWords if password protected.
	 * @param url
	 * @throws CypherException
	 * @throws SevenZipException
	 * @throws FileNotFoundException 
	 */
	public One openOne(String url) throws CypherException, SevenZipException, KnowledgeException, FileNotFoundException {
		// does the file exist?
		File file = new File(url);
		if (!file.exists()) {
			throw new FileNotFoundException("Specified file does not exist! (" + url + ")");
		}
		
		// well then load it
		ArchiveFormat format = Cypher.getArchiveFormat(url);
		String name = Cypher.getArchiveName(url);
		One one = new One(name);
		
		RandomAccessFile raf = new RandomAccessFile(file, "r");
		IInStream inStream = new RandomAccessFileInStream(raf);
		ISevenZipInArchive archive = SevenZip.openInArchive(format, inStream);
		one.setArchive(archive);
		if (archive.getArchiveProperty(PropID.ENCRYPTED) != null) {
			//the words
		} else {
			//put on done list
		}
		
		return one;
	}
}
