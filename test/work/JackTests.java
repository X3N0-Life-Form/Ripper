package work;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JackTests {
	

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
		assertEquals("serializedData", Jack.getKnowledgeDirectory());
		assertEquals(8, Jack.getNumberOfThreads());
		assertTrue(Jack.getTargets().contains("test02.7z"));
		assertTrue(Jack.getTargets().contains("test03.zip"));
	}
	
	@Test
	public void test_displayKnowledge() {
		fail("Not implemented.");
	}
}
