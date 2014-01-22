package knowledge;

import net.sf.sevenzipjbinding.ISevenZipInArchive;

/**
 * One interfaces with archive. Speaks for archive.
 * @author X3N0-Life-Form
 *
 */
public class One {

	/**
	 * One is an archive.
	 */
	private ISevenZipInArchive archive;
	/**
	 * One has a name.
	 */
	private String name;
	
	public One(ISevenZipInArchive archive, String name) {
		this.archive = archive;
		this.name = name;
	}
	
	public String getName() {
		return name;		
	}
	
}
