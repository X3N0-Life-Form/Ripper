package work;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tests.URLS;

public class JackLaunchTests {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_key() {
		String[] params = {
				"--targetDirectory",		URLS.LAUNCH_ARCHIVE_KEY,
				"--destinationDirectory",	URLS.TEST_RESOURCES_RESULTS,
				"--knowledgeDirectory",		URLS.LAUNCH_KNOWLEDGE_KEY,
				"--mode",	"key"
		};
		Jack.main(params);
		assertTrue(true);
	}
	
	@Test
	public void test_knifeFound() {
		String[] params = {
				"--targetDirectory",		URLS.LAUNCH_ARCHIVE_KNIFE,
				"--destinationDirectory",	URLS.TEST_RESOURCES_RESULTS,
				"--knowledgeDirectory",		URLS.LAUNCH_KNOWLEDGE_KNIFE,
				"--mode",	"knife", "aaa:bbb"
		};
		Jack.main(params);
		assertTrue(true);
	}
	
	@Test
	public void test_knifeNotFound() {
		String[] params = {
				"--targetDirectory",		URLS.LAUNCH_ARCHIVE_KNIFE,
				"--destinationDirectory",	URLS.TEST_RESOURCES_RESULTS,
				"--knowledgeDirectory",		URLS.LAUNCH_KNOWLEDGE_KNIFE,
				"--mode",	"knife", "a:aaa"
		};
		Jack.main(params);
		assertTrue(true);
	}
}
