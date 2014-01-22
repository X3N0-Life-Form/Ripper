package knowledge;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Save knowledge regarding Ones and Words. Lacks accuracy.
 * @author X3N0-Life-Form
 *
 */
public class Truth implements Serializable {
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
}
