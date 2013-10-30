package rest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Truth implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4547875632622576521L;
	
	private List<String> validPasswords;
	
	public Truth() {
		validPasswords = new ArrayList<String>();
	}
	
	public void addValidPassword(String pass) {
		validPasswords.add(pass);
	}
}
