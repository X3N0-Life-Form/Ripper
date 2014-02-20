package work;

import static org.junit.Assert.*;

import java.io.IOException;

import knowledge.Nexus;
import knowledge.One;
import net.sf.sevenzipjbinding.SevenZipException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tests.URLS;

public class KeyTests {
	
	private Key key_1;
	private One one;
	
	private Key key_2;
	private One two;
	
	private Nexus nexus;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		nexus = new Nexus();
		one = nexus.prepareOne(URLS.URL_TEST01);
		two = nexus.prepareOne(URLS.URL_TEST02);
		key_1 = new Key(one);
		key_2 = new Key(two);
	}

	@After
	public void tearDown() throws Exception {
		//TODO:remove old files & folders
	}

	@Test
	public void test_extract_password() throws SevenZipException, IOException {
		key_1.extract();
	}
	
	@Test
	public void test_extract_OK() throws SevenZipException, IOException {
		key_2.extract();
		assertTrue(true);
	}

}
