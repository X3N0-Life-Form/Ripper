package persistance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Passwords implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4547875632622576521L;
	
	private List<String> passwords;
	
	public Passwords() {
		passwords = new ArrayList<String>();
	}
	
	
}
