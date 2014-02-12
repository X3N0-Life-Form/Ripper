package work.dirt;

import java.io.File;

import net.sf.sevenzipjbinding.ExtractAskMode;
import net.sf.sevenzipjbinding.ExtractOperationResult;
import net.sf.sevenzipjbinding.IArchiveExtractCallback;
import net.sf.sevenzipjbinding.ISequentialOutStream;
import net.sf.sevenzipjbinding.SevenZipException;

public class ExtractCallback implements IArchiveExtractCallback {

	private int currentIndex = -1;
	private long currentCompletionValue = 0;
	private File currentFile = null;
	private ISequentialOutStream currentOutStream = null;
	private String destinationDirectory = ".";
	private String[] archiveEntries = null;
	
	public ExtractCallback(String destinationDirectory, String[] archiveEntries) {
		this.destinationDirectory = destinationDirectory;
		this.archiveEntries = archiveEntries;
	}
	
	@Override
	public void setCompleted(long value) throws SevenZipException {
		this.currentCompletionValue = value;
	}

	@Override
	public void setTotal(long total) throws SevenZipException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ISequentialOutStream getStream(int index, ExtractAskMode extractAskMode)
			throws SevenZipException {
		switch (extractAskMode) {
		case EXTRACT:
			//TODO: log
			currentIndex = index;
			currentCompletionValue = 0;
			currentFile = new File(destinationDirectory, archiveEntries[index]);
			currentOutStream = new SequentialOutStream(currentFile);
			break;
		case SKIP:
			currentIndex = -1;
			currentCompletionValue = 0;
			currentFile = null;
			currentOutStream = null;
			break;
		case TEST:
		case UNKNOWN_ASK_MODE:
		default:
			throw new UnsupportedOperationException("Action " + extractAskMode + " is unsupported");
		}
		return currentOutStream;
	}

	@Override
	public void prepareOperation(ExtractAskMode extractAskMode) throws SevenZipException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOperationResult(ExtractOperationResult result)
			throws SevenZipException {
		// TODO Auto-generated method stub
		
	}

}
