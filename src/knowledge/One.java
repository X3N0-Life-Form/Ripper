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
	private transient ISevenZipInArchive archive = null;
	
	
	/**
	 * One has a name.
	 */
	private String name;
	
	public One(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public ISevenZipInArchive getArchive() {
		return archive;
	}

	public void setArchive(ISevenZipInArchive archive) {
		this.archive = archive;
	}
	
}
