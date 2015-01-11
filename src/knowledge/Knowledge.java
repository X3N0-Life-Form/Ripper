package knowledge;

import java.io.Serializable;

/**
 * Bits of knowledge must be conserved.
 * @author X3N0-Life-Form
 *
 */
public interface Knowledge extends Serializable {
	
	public void setName(String name);
	
	/**
	 * All Knowledge must have a name or url.
	 * @return
	 */
	public String getName();
	
	/**
	 * If a Knowledge's name is a url, it is good to know its actual name.
	 * @return
	 */
	public String getSimpleName();
	
	/**
	 * All Knowledge must provide its file extension, if it is to be saved.
	 * @return String .something
	 */
	public String getFileExtension();

}
