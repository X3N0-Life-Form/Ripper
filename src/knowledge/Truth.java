package knowledge;

import java.util.ArrayList;
import java.util.List;

/**
 * Saves knowledge regarding true words.
 * @author X3N0-Life-Form
 *
 */
public class Truth extends AbstractKnowledge {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4547875632622576521L;
	
	private List<String> validPasswords = new ArrayList<String>();
	
	public Truth() {
		
	}
	
	public void addValidPassword(String pass) {
		validPasswords.add(pass);
	}

	@Override
	public String getFileExtension() {
		return ".truth";
	}
}
