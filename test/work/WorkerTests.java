package work;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import knowledge.Nexus;
import knowledge.One;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tests.URLS;

public class WorkerTests {
	
	private Worker worker; 
	
	private One one;
	private Nexus nexus;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		nexus = new Nexus();
		one = nexus.prepareOne(URLS.URL_TEST01);
		worker = new Key(one);
		worker.setDestination(URLS.TEST_RESOURCES_RESULTS);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateFolderStandard() throws IOException {
		assertTrue(worker.getDestination().endsWith("/"));
		File folder = worker.createFolder("folder-creation-test");
		assertTrue(folder.exists());
		folder.delete();
	}
	
	@Test
	public void testCreateFolderOne() throws IOException {
		File folder = worker.createFolder(one);
		assertTrue(folder.exists());
		assertTrue(folder.getAbsolutePath().endsWith(worker.getDestination() + "test01"));
		folder.delete();
	}

}
