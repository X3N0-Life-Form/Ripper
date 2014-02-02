package knowledge;

import java.util.ArrayList;
import java.util.List;

/**
 * Saves knowledge regarding true words.
 * @author X3N0-Life-Form
 *
 */
public class Truth implements Knowledge {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4547875632622576521L;
	
	private List<String> validPasswords = new ArrayList<String>();
	
	private String name = "nameless";
	
	
	
	public Truth() {
		
	}
	
	public void addValidPassword(String pass) {
		validPasswords.add(pass);
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getFileExtension() {
		return ".truth";
	}
}
