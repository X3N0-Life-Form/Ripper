package knowledge;

import java.io.Serializable;

/**
 * Bits of knowledge must be conserved.
 * @author X3N0-Life-Form
 *
 */
public interface Knowledge extends Serializable {
	
	public void setName(String name);
	public String getName();
	/**
	 * All Knowledge must provide its file extension, if it is to be saved.
	 * @return String .something
	 */
	public String getFileExtension();

}
