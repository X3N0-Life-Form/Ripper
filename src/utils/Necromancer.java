package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import knowledge.Knowledge;

/**
 * Master of life and death. Can bury or resurrect knowledge if needs be.
 * @author X3N0-Life-Form
 *
 */
public class Necromancer {
	
	private static String pathToKnowledge = "./knowledge";

	/**
	 * No instantiation for you.
	 */
	private Necromancer() {}
	
	public static void buryKnowledge(Knowledge toSave) {
		String name = toSave.getName();
		String ext = toSave.getFileExtension();
		File file = new File(pathToKnowledge + "/" + name + ext);
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(toSave);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void setPathToKnowledge(String url) {
		pathToKnowledge = url;
	}
}
