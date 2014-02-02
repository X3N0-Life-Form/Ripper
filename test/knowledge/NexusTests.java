package knowledge;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import net.sf.sevenzipjbinding.SevenZipException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import utils.CypherException;
import utils.Necromancer;

public class NexusTests {

	public static final String URL_TEST01 = "test-resources/archives/test01.7z";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_prepareOne() throws FileNotFoundException, CypherException, SevenZipException, KnowledgeException {
		Nexus n = new Nexus();
		One test01 = n.prepareOne(URL_TEST01);
		assertNull(test01.getArchive());
		assertTrue(n.getChamberOfWords().containsKey(test01));
	}
	
	@Test
	public void test_serialization() throws FileNotFoundException, CypherException, SevenZipException, KnowledgeException {
		Nexus n = new Nexus();
		n.setName("test01_nexus");
		@SuppressWarnings("unused")
		One test01 = n.prepareOne(URL_TEST01);
		Necromancer.setPathToKnowledge("test-resources/serializedData");
		Necromancer.buryKnowledge(n);
		File file = new File("test-resources/serializedData/test01_nexus.nexus");
		assertTrue(file.exists());
		file.delete();
	}
}
