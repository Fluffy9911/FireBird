package main.fire.core.debug;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.fire.anotations.End;
import main.fire.anotations.Marked;
import main.fire.util.PathMaker;
import main.fire.util.Status;

public class Debug {
	public static List<String> write;
	public static boolean debug = false;

	public static void debug() {
		debug = true;
	}

	@Marked
	public static void init() {
		write = new ArrayList<>();
	}

	public static void debugError(Class<?> c, Exception e) {
		e.printStackTrace();
		write.add(e.getMessage());
		System.err.println("Class:" + c.toString() + " is erroring");
	}

	public static void printInfo(String msg, boolean dbg) {
		if (dbg == false) {
			write.add(msg);
			System.out.println("{" + msg + "}");
		} else if (debug == true && dbg == true) {
			write.add(msg);
			System.out.println("{" + msg + "}");
		}
	}

	public static void status(Status s, String msg, boolean dbg) {

		printInfo("[Status: " + s.toString() + "]" + msg, dbg);
	}

	public static void error(String st) {
		write.add(st);
		System.err.println(st + " [ERROR]");
	}

	@End
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
