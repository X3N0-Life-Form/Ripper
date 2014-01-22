package knowledge;

import java.util.Map;
import java.util.TreeMap;

import net.sf.sevenzipjbinding.SevenZip;

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
	
	
	public void openOne(String url) {
		
	}
}
