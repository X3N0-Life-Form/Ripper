package knowledge;

import java.util.ArrayList;
import java.util.List;

/**
 * The words that define One.
 * @author Words
 *
 */
public class Words extends AbstractKnowledge {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7863419261450807657L;
	
	/**
	 * The first and last words that have been spoken during the last session.
	 */
	private String[] spokenWords = new String[2];
	/**
	 * What words have been spoken in the past.
	 */
	private List<String[]> oldWords = new ArrayList<String[]>();
	/**
	 * The word that was spoken rightly.
	 */
	private String rightWord = null;
	
	public String[] getSpokenWords() {
		return spokenWords;
	}
	
	public void setFirstWord(String word) {
		spokenWords[0] = word;
	}
	
	public void setLastWord(String word) {
		spokenWords[1] = word;
	}
	
	public void storeWords() {
		oldWords.add(spokenWords);
	}
	
	public void setRightWord(String word) {
		rightWord = word;
	}
	
	public String getRightWord() {
		return rightWord;
	}

	@Override
	public String getFileExtension() {
		return ".words";
	}
}
