package main.fire.core.debug;

import java.util.List;

import main.fire.anotations.Marked;
import main.fire.util.Utils;

public class PrintManager {
	public static List<String> PRINT_BUFFER;

	@Marked
	public static void initManager() {
		PRINT_BUFFER = Utils.creatArrayList();

	}

}
