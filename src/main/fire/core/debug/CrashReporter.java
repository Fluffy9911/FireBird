package main.fire.core.debug;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import main.fire.core.Core;
import main.fire.resources.FileLocation;
import main.fire.util.PathMaker;
import main.fire.util.Time;
import main.utils.Extensions;
import main.utils.FileBuilder;

public class CrashReporter {
	public static String default_location = "firebird/logs";

	public static String getDefault_location() {
		return default_location;
	}

	public static void setDefault_location(String default_location) {
		CrashReporter.default_location = default_location;
	}

	public static void dispatchCrash(Exception e) {
		Crash c = new Crash(e, Debug.getWrite());
		try {
			c.writeCrash(default_location);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.exit(5);
	}

	public static class Crash {
		Exception e;
		List<String> log;

		public Crash(Exception e, List<String> log) {
			this.e = e;
			this.log = log;
		}

		public void writeCrash(String location) throws FileNotFoundException {
			String lg = "";
			for (Iterator iterator = log.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				lg += string + "\n";
			}

			FileLocation fl = new FileLocation(location, "crash_trace_" + Time.fileDate(), Extensions.TXT);
			PathMaker.makePath(location);

			PrintWriter pw = new PrintWriter(fl.getAsFile());
			e.printStackTrace(pw);
			pw.close();

			FileBuilder.createBuilder(location, "crash_" + Time.fileDate(), Extensions.TXT).createAndWrite(lg);
		}

	}

	public static void dispatchCrash(String string, Exception e) {
		Crash c = new Crash(e, Debug.getWrite());
		Debug.error(string);
		try {
			c.writeCrash(Core.CORE_CRASH_LOCATION);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
