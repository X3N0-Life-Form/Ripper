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

public class KnifeTests {
	
	private Knife knife_1;
	private One one;
	private File file_1;
	private File control_file_1;
	
	private Nexus nexus;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		nexus = new Nexus();
		one = nexus.prepareOne(URLS.TEST01_ARCHIVE);
		
		knife_1 = new Knife(one, "", nexus);
		knife_1.setDestination(URLS.TEST_RESOURCES_RESULTS);
		
		file_1 = new File(URLS.RESULTS_TEST01_TXT);
		control_file_1 = new File(URLS.CONTROL_GROUP_TEST01_TXT);;
	}

	@After
	public void tearDown() throws Exception {
		file_1.delete();
	}

	@Test
	public void test_getNextPassword_basic() {
		String pass = "aaa";
		String result = Knife.getNextPassword(pass);
		assertNotNull(result);
		assertFalse(pass.equals(result));
		assertEquals("aab", result);
	}

	@Test
	public void test_getNextPassword_carry() {
		char[] pass = {
				65,
				65,
				255
		};
		String result = Knife.getNextPassword(String.copyValueOf(pass));
		assertNotNull(result);
		char[] resChar = result.toCharArray();
		assertTrue(resChar.length == pass.length);
		assertTrue(resChar[resChar.length - 1] == 0);
		assertTrue(resChar[resChar.length - 2] == 66);
	}
	
	@Test
	public void test_getNextPassword_bump() {
		char[] pass = {
				255
		};
		String result = Knife.getNextPassword(String.copyValueOf(pass));
		assertNotNull(result);
		char[] resChar = result.toCharArray();
		assertTrue(resChar.length == pass.length + 1);
		assertTrue(resChar[resChar.length - 1] == 0);
		assertTrue(resChar[resChar.length - 2] == 0);
	}
	
	@Test
	public void test_extract_basic() throws SevenZipException, IOException, WorkerException {
		knife_1.setDestination(URLS.TEST_RESOURCES_RESULTS);
		knife_1.setEndPoint("hjvw");
		knife_1.extract();
		assertTrue(file_1.exists());
		assertTrue(Utils.compareFiles(file_1, control_file_1));
	}
}