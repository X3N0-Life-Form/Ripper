package knowledge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import net.sf.sevenzipjbinding.ISevenZipInArchive;
import net.sf.sevenzipjbinding.SevenZipException;
import net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tests.URLS;
import tests.Utils;
import work.dirt.SequentialOutStream;

public class OneTests {
	
	private Nexus nexus;
	private One one;
	private File file_1;
	private File control_file_1;

	@Before
	public void setUp() throws Exception {
		nexus = new Nexus();
		one = nexus.prepareOne(URLS.TEST03_ARCHIVE);
		
		file_1 = new File(URLS.RESULTS_TEST03a_TXT);
		control_file_1 = new File(URLS.CONTROL_GROUP_TEST03a_TXT);
	}

	@After
	public void tearDown() throws Exception {
		file_1.delete();
	}
	
	@Test
	public void test_getSimpleName() {
		assertEquals("test03", one.getSimpleName());
	}

	@Test
	public void test_manualExtraction() throws SevenZipException, IOException {
		one.loadArchive("hjvo");
		ISevenZipInArchive archive = one.getArchive();
		int numberOfItems = archive.getNumberOfItems();
		SequentialOutStream sos = new SequentialOutStream(file_1);
		
		for (int j = 0; j < numberOfItems; j++) {
			ISimpleInArchiveItem item = archive.getSimpleInterface().getArchiveItem(j);
			item.extractSlow(sos, "hjvo");
		}
		
		assertTrue(file_1.exists());
		assertTrue(Utils.compareFiles(file_1, control_file_1));
	}

}
