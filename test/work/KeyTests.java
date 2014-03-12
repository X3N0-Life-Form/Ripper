package work;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import knowledge.Nexus;
import knowledge.One;
import net.sf.sevenzipjbinding.SevenZipException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tests.URLS;
import tests.Utils;

public class KeyTests {
	
	private Key key_1;
	private One one;
	private File file_1;
	private File control_file_1;
	
	private Key key_2;
	private One two;
	private File file_2;
	private File control_file_2;
	
	private Nexus nexus;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		nexus = new Nexus();
		one = nexus.prepareOne(URLS.TEST01_ARCHIVE);
		two = nexus.prepareOne(URLS.TEST02_ARCHIVE);
		
		key_1 = new Key(one);
		key_2 = new Key(two);
		key_1.setDestination(URLS.TEST_RESOURCES_RESULTS);
		key_2.setDestination(URLS.TEST_RESOURCES_RESULTS);
		
		file_1 = new File(URLS.RESULTS_TEST01_TXT);
		control_file_1 = new File(URLS.CONTROL_GROUP_TEST01_TXT);;
		
		file_2 = new File(URLS.RESULTS_TEST02_TXT);
		control_file_2 = new File(URLS.CONTROL_GROUP_TEST02_TXT);
	}

	@After
	public void tearDown() throws Exception {
		file_1.delete();
		file_2.delete();
	}

	@Test
	public void test_extract_password_KO() throws SevenZipException, IOException, WorkerException {
		key_1.setDestination(URLS.TEST_RESOURCES_RESULTS);
		key_1.extract();
		assertTrue(file_1.exists());
		assertFalse(Utils.compareFiles(file_1, control_file_1));
	}
	
	@Test
	public void test_extract_OK() throws SevenZipException, IOException, WorkerException {
		key_2.extract();
		assertTrue(file_2.exists());
		assertTrue(Utils.compareFiles(file_2, control_file_2));
	}

}
