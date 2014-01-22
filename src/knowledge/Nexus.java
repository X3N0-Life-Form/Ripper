package knowledge;

import java.util.Map;
import java.util.TreeMap;

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
	private Truth truth;
	
	public Map<One, Words> getTruths() {
		return chamberOfWords;
	}
}
