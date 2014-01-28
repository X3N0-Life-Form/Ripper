package knowledge;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import net.sf.sevenzipjbinding.ArchiveFormat;
import net.sf.sevenzipjbinding.IInStream;
import net.sf.sevenzipjbinding.ISevenZipInArchive;
import net.sf.sevenzipjbinding.PropID;
import net.sf.sevenzipjbinding.PropertyInfo;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.SevenZipException;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;
import utils.Cypher;
import utils.CypherException;

/**
 * A nexus of knowledge.
 * @author X3N0-Life-Form
 *
 */
public class Nexus {

	public Set<One> getHallOfSuccess() {
		return hallOfSuccess;
	}

	/**
	 * One has a Words.
	 */
	private Map<One, Words> chamberOfWords = new TreeMap<One, Words>();
	/**
	 * One can be successful.
	 */
	private Set<One> hallOfSuccess = new TreeSet<One>();
	
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
		
		if (true) {
			chamberOfWords.put(one, new Words());
		} else {
			hallOfSuccess.add(one);
		}
		
		return one;
	}
}
