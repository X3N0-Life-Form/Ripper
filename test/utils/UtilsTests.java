package utils;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import tests.URLS;
import tests.Utils;

public class UtilsTests {
	
	private File f1 = new File(URLS.CONTROL_GROUP_TEST01_TXT);
	private File f2 = new File(URLS.CONTROL_GROUP_TEST01_TXT);
	
	@Test
	public void test_compareFiles() {
		assertTrue(Utils.compareFiles(f1, f2));
	}
}
