package work;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import knowledge.Nexus;
import knowledge.One;
import net.sf.sevenzipjbinding.ArchiveFormat;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tests.URLS;
import utils.Necromancer;

public class JackTests {
	
	@BeforeClass
	public static void generateKnowledge() {
		File nexusFile = new File(URLS.TEST_RESOURCES_KNOWLEDGE + "/test-nexus.nexus");
		if (!nexusFile.exists()) {
			Nexus nexus = new Nexus();
			One one = new One("test-one", ArchiveFormat.ZIP, "noURL");
			nexus.prepareOne(one);
			nexus.setName("test-nexus");
			nexus.saveWords(one, "a", "b");
			Necromancer.setPathToKnowledge(URLS.TEST_RESOURCES_KNOWLEDGE);
			Necromancer.buryKnowledge(nexus);
		}
	}
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_extractOption() {
		String option = Jack.extractOption("#some option:\ttest   ");
		assertEquals("test", option);
	}
	
	@Test
	public void test_readOptions_OK() throws IOException {
		Jack.readOptions("optionsSample.ini");
		assertEquals("test-resources/results", Jack.getDestinationDirectory());
		assertEquals("test-resources/archives", Jack.getTargetDirectory());
		assertEquals("test-resources/serializedData", Jack.getKnowledgeDirectory());
		assertEquals(8, Jack.getNumberOfThreads());
		assertTrue(Jack.getTargets().contains("test02.7z"));
		assertTrue(Jack.getTargets().contains("test03.zip"));
		assertTrue(Jack.getMode() == Jack.ExtractMode.KNIFE);
		assertEquals("aaa", Jack.getRange()[0]);
		assertEquals("bbb", Jack.getRange()[1]);
	}
	
	@Test
	public void test_knowledgeToString() throws ClassNotFoundException, IOException {
		Jack.setKnowledgeDirectory(URLS.TEST_RESOURCES_KNOWLEDGE);
		String s = Jack.knowledgeToString();
		System.out.println(s);
		assertTrue(s.contains("test-nexus"));
	}
	
	//TODO:
	//prepare knife
	//get knowledge name
	//find targets
}
