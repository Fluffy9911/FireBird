package main.fire.resources;

import java.io.File;

import main.fire.cache.CacheObject;
import main.fire.core.Core;
import main.fire.util.Type;

public class ResourceFinder {
	public static Type type = Core.getType();

	public static void firstTimeSetupCache(CacheObject obj) {
		if (type == Type.DEV) {
			obj.add("cache_location", Core.CACHE_LOCATION);

		}

	}

	public static File getCoreCache() {
		return (new File(Core.CACHE_LOCATION + "/" + Core.CACHE + ".fbc").exists())
				? new File(Core.CACHE_LOCATION + "/" + Core.CACHE + ".fbc")
				: new File(Core.CACHE_LOCATION + "/" + Core.CACHE + ".fbc");
	}

	public static boolean checkForFileExistance() {
		Core.firstTime = !getCoreCache().exists();
		return getCoreCache().exists();
	}

}
