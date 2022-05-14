package main.module.fire;

import java.util.ArrayList;
import java.util.List;

import main.fire.cache.CacheManager;
import main.fire.cache.CacheObject;
import main.fire.core.debug.MSCalc;

public class ModuleLoader {

	public static List<FireBirdModule> modules = createList();

	public static void addModule(FireBirdModule fbm) {
		modules.add(fbm);
	}

	public static void init() {
		modules.forEach((fbc) -> {
			fbc.init();
		});
	}

	public static boolean checkForModules() {
		System.out.println("Starting module check...");
		if (!modules.isEmpty()) {
			MSCalc m = new MSCalc();
			modules.forEach((fbc) -> {
				System.out.println("Found module: " + fbc.uniqueID() + " with version: " + fbc.version());
			});
			m.end();
			System.out.println("Took: " + m.getEnd() + " to load modules");
			return true;
		} else {
			return false;
		}

	}

	public static void cacheEvent(CacheObject obj, CacheManager m) {
		modules.forEach((fbc) -> {
			fbc.cacheEvent(obj, m);
		});
	}

	private static List<FireBirdModule> createList() {

		return new ArrayList<>();
	}

}
