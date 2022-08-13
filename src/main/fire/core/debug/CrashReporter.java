package main.fire.core.debug;

import java.util.Iterator;
import java.util.List;

import org.reflections.Reflections;

import main.fire.core.Core;
import main.fire.util.Time;
import main.utils.Extensions;
import main.utils.FileBuilder;

public class CrashReporter {

	public static void dispatchCrash(Exception e) {
		Crash c = new Crash(e, Debug.getWrite());
		c.writeCrash(Core.CORE_CRASH_LOCATION);
	}

	public static class Crash {
		Exception e;
		List<String> log;

		public Crash(Exception e, List<String> log) {
			this.e = e;
			this.log = log;
		}

		public void writeCrash(String location) {
			String lg = "";
			for (Iterator iterator = log.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				lg += string + "\n";
			}
			lg += " Crash Reson:" + e.getMessage();
			Reflections r = new Reflections();

			FileBuilder.createBuilder(location, "crash_" + Time.fileDate(), Extensions.TXT).createAndWrite(lg);
		}

	}

}
