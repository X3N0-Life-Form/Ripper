package work;

import net.sf.sevenzipjbinding.ArchiveFormat;
import knowledge.Many;

/**
 * The one that creates.
 * @author X3N0-Life-form
 *
 */
public class Eve implements Runnable {
	
	@SuppressWarnings("unused")
	private Many many;
	@SuppressWarnings("unused")
	private ArchiveFormat format;
	
	public Eve(Many many) {
		this.many = many;
	}
	
	public void setArchiveFormat(ArchiveFormat format) {
		this.format = format;
	}

	@Override
	public void run() {
		//TODO: find something to compress an archive
	}

}
