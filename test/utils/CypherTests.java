package utils;

import static org.junit.Assert.*;
import net.sf.sevenzipjbinding.ArchiveFormat;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CypherTests {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected=CypherException.class)
	public void test_getArchiveFormat_unrecognised() throws CypherException {
		String url = "e.zorg";
		Cypher.getArchiveFormat(url);
	}
	
	@Test
	public void test_getArchiveFormat_rar() throws CypherException {
		String url = "e.rar";
		ArchiveFormat result = Cypher.getArchiveFormat(url);
		assertTrue(result == ArchiveFormat.RAR);
	}
	
	@Test
	public void test_getArchiveName() {
		String url = "e.zorg";
		String name = Cypher.getArchiveName(url);
		assertEquals("e", name);
	}
}
