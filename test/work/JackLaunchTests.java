package work;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tests.URLS;
import tests.Utils;

public class JackLaunchTests {

	private File key1, key2, key3;
	private File knife1, knife2a, knife2b, knife3, knife4;
	private File knife1_control, knife2a_control, knife2b_control, knife3_control, knife4_control;
	
	@Before
	public void setUp() throws Exception {
		key1 = new File(URLS.LAUNCH_RESULT_KEY + "/key/key.txt");
		key2 = new File(URLS.LAUNCH_RESULT_KEY + "/key2/key2.txt");
		key3 = new File(URLS.LAUNCH_RESULT_KEY + "/key3/key3.txt");
		
		knife1 = new File(URLS.LAUNCH_RESULT_KNIFE + "/knife1/knife.txt");
		knife2a = new File(URLS.LAUNCH_RESULT_KNIFE + "/knife2/knife2a.txt");
		knife2b = new File(URLS.LAUNCH_RESULT_KNIFE + "/knife2/knife2b.txt");
		knife3 = new File(URLS.LAUNCH_RESULT_KNIFE + "/knife3/knife3.txt");
		knife4 = new File(URLS.LAUNCH_RESULT_KNIFE + "/knife4/knife4_different.txt");
		
		knife1_control = new File(URLS.LAUNCH_CONTROL_KNIFE + "/knife.txt");
		knife2a_control = new File(URLS.LAUNCH_CONTROL_KNIFE + "/knife2a.txt");
		knife2b_control = new File(URLS.LAUNCH_CONTROL_KNIFE + "/knife2b.txt");
		knife3_control = new File(URLS.LAUNCH_CONTROL_KNIFE + "/knife3.txt");
		knife4_control = new File(URLS.LAUNCH_CONTROL_KNIFE + "/knife4_different.txt");
	}

	@After
	public void tearDown() throws Exception {
		//TODO:delete files
	}

	@Test
	public void test_key() {
		String[] params = {
				"--targetDirectory",		URLS.LAUNCH_ARCHIVE_KEY,
				"--destinationDirectory",	URLS.LAUNCH_RESULT_KEY,
				"--knowledgeDirectory",		URLS.LAUNCH_KNOWLEDGE_KEY,
				"--mode",	"key"
		};
		Jack.main(params);
		assertTrue(key1.exists());
		assertTrue(key2.exists());
		assertTrue(key3.exists());
	}
	
	@Test
	public void test_knifeFound() {
		String[] params = {
				"--targetDirectory",		URLS.LAUNCH_ARCHIVE_KNIFE,
				"--destinationDirectory",	URLS.LAUNCH_RESULT_KNIFE,
				"--knowledgeDirectory",		URLS.LAUNCH_KNOWLEDGE_KNIFE,
				"--mode",	"knife", "aaa:bbb"
		};
		Jack.main(params);
		assertTrue(knife1.exists());
		assertTrue(knife2a.exists());
		assertTrue(knife2b.exists());
		assertTrue(knife3.exists());
		assertTrue(knife4.exists());
		
		assertTrue(Utils.compareFiles(knife1, knife1_control));
		assertTrue(Utils.compareFiles(knife2a, knife2a_control));
		assertTrue(Utils.compareFiles(knife2b, knife2b_control));
		assertTrue(Utils.compareFiles(knife3, knife3_control));
		assertTrue(Utils.compareFiles(knife4, knife4_control));
	}
	
	@Test
	public void test_knifeNotFound() {
		String[] params = {
				"--targetDirectory",		URLS.LAUNCH_ARCHIVE_KNIFE,
				"--destinationDirectory",	URLS.LAUNCH_RESULT_KNIFE,
				"--knowledgeDirectory",		URLS.LAUNCH_KNOWLEDGE_KNIFE,
				"--mode",	"knife", "a:aaa"
		};
		Jack.main(params);
		assertFalse(true);
	}
}
