package main.fire.util;

import java.io.File;

public class PathMaker {

	public static void makePath(String path) {
		File f = new File(path);
		if (f.exists())
			return;
		f.mkdirs();
	}

}
