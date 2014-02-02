package knowledge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import net.sf.sevenzipjbinding.ArchiveFormat;
import net.sf.sevenzipjbinding.SevenZipException;
import utils.Cypher;
import utils.CypherException;

/**
 * A nexus of knowledge. Contains {@link Words}, {@link Truth} and successful {@link One}s.
 * @author X3N0-Life-Form
 *
 */
public class Nexus extends AbstractKnowledge {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6079721389844172520L;

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
	
	public Set<One> getHallOfSuccess() {
		return hallOfSuccess;
	}
	
	/**
	 * Puts One into the chamber of words.
	 * @param url
	 * @throws CypherException
	 * @throws SevenZipException
	 * @throws FileNotFoundException 
	 */
	public One prepareOne(String url) throws CypherException, SevenZipException, KnowledgeException, FileNotFoundException {
		// does the file exist?
		File file = new File(url);
		if (!file.exists()) {
			throw new FileNotFoundException("Specified file does not exist! (" + url + ")");
		}
		
		// well then get basic info and save it
		ArchiveFormat format = Cypher.getArchiveFormat(url);
		String name = Cypher.getArchiveName(url);
		One one = new One(name, format, url);
		
		chamberOfWords.put(one, new Words());
		
		return one;
	}

	

	@Override
	public String getFileExtension() {
		return ".nexus";
	}
	
}
