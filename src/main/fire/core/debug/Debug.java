package main.fire.core.debug;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.fire.util.PathMaker;

public class Debug {
	public static List<String> write;

	public static void init() {
		write = new ArrayList<>();
	}

	public static void debugError(Class<?> c, Exception e) {
		e.printStackTrace();
		write.add(e.getMessage());
		System.err.println("Class:" + c.toString() + " is erroring");
	}

	public static void printInfo(String msg) {
		write.add(msg);
		System.out.println(msg + " [INFO]");
	}

	public static void error(String st) {
		write.add(st);
		System.err.println(st + " [ERROR]");
	}

	public static void write() throws IOException {
		PathMaker.makePath("firebird/logs");

		File f = new File("firebird/logs/log_" + System.currentTimeMillis() + ".txt");

		f.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(f));

		for (String st : write) {
			writer.write(st);
			writer.newLine();
		}
		writer.close();

	}

}
