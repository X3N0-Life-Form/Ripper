package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
		File folder = new File(pathToKnowledge);
		if (!folder.exists()) {
			folder.mkdirs();
		}
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
	
	public static Knowledge unEarthKnowledge(String toLoad) throws IOException, ClassNotFoundException {
		File file = new File(pathToKnowledge + "/" + toLoad);
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Knowledge res = (Knowledge) ois.readObject();
		ois.close();
		return res;
	}

	public static void setPathToKnowledge(String url) {
		pathToKnowledge = url;
	}
}
