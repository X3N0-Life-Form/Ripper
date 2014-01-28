package knowledge;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import net.sf.sevenzipjbinding.SevenZipException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import utils.CypherException;

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
	public void test_openOnePasswordProtected() throws FileNotFoundException, CypherException, SevenZipException, KnowledgeException {
		Nexus n = new Nexus();
		One test01 = n.openOne(URL_TEST01);
		assertNotNull(test01.getArchive());
		assertTrue(n.getChamberOfWords().containsKey(test01));
		assertFalse(n.getHallOfSuccess().contains(test01));
	}
	

}
