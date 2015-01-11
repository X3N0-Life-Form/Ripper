package utils;

import net.sf.sevenzipjbinding.ArchiveFormat;

/**
 * Utilities to unlock One's secrets
 * @author X3N0-Life-Form
 *
 */
public class Cypher {

	public static ArchiveFormat getArchiveFormat(String url) throws CypherException {
		String ext = url.substring(url.lastIndexOf('.'));
		switch (ext) {
		case ".zip":
			return ArchiveFormat.ZIP;
		case ".rar":
			return ArchiveFormat.RAR;
		case ".7z":
			return ArchiveFormat.SEVEN_ZIP;
		case ".tar":
			return ArchiveFormat.TAR;
		default:
			throw new CypherException("Archive extension unrecognised: " + ext);
		}
	}
	
	public static String getArchiveName(String url) {
		return url.substring(0, url.lastIndexOf('.'));
	}
	
}
