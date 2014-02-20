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

public class KeyTests {
	
	public static final String URL_TEST01 = "test-resources/archives/test01.7z";
	private Key key;
	private One one;
	private Nexus nexus;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		nexus = new Nexus();
		one = nexus.prepareOne(URL_TEST01);
		key = new Key(one);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_extract_password() throws SevenZipException, IOException {
		key.extract();
	}

}
