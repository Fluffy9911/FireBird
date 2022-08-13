package main.fire.util;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.fire.anotations.End;
import main.fire.core.debug.Debug;
import main.fire.rendering.Renderer;
import main.utils.Extensions;
import main.utils.FileBuilder;

public class PerformanceChecker {
	public static List<String> dump;

	public static void check(Renderer r) {
		if (dump == null)
			dump = new ArrayList<>();
		Runtime runtime = Runtime.getRuntime();

		NumberFormat format = NumberFormat.getInstance();

		StringBuilder sb = new StringBuilder();
		long maxMemory = runtime.maxMemory();
		long allocatedMemory = runtime.totalMemory();
		long freeMemory = runtime.freeMemory();

		sb.append("  free memory: " + format.format(freeMemory / 1024));
		sb.append("  allocated memory: " + format.format(allocatedMemory / 1024));
		sb.append("  max memory: " + format.format(maxMemory / 1024));
		sb.append("  total free memory: " + format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024));
		sb.append("  frames: " + format.format((double) r.getFrames()));
		Debug.printInfo(sb.toString(), true);
		dump.add(sb.toString() + "  ");

	}

	@End
	public static void dump() {
		String i = "";
		if (dump != null)
			for (Iterator iterator = dump.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				i += string + "\n";
			}
		try {
			FileBuilder.createBuilder("firebird/logs/performancedumps", "dump" + Time.fileDate(), Extensions.TXT)
					.createAndWrite(i);
		} catch (Exception e) {
			Debug.debugError(PerformanceChecker.class, e);
		}
	}
}
