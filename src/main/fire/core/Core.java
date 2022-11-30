package main.fire.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import main.fire.anotations.Marked;
import main.fire.cache.CacheManager;
import main.fire.cache.CacheObject;
import main.fire.core.debug.CrashReporter;
import main.fire.core.debug.Debug;
import main.fire.file.ConfigFile;
import main.fire.file.SaveFile;
import main.fire.resources.FileLocation;
import main.fire.util.ICore;
import main.fire.util.IUpdateable;
import main.fire.util.Status;
import main.fire.util.Type;
import main.utils.Extensions;

public class Core {
	// resources
	public static final String CACHE_LOCATION = "firebird/cache";
	public static final String CACHE = "main_cache";
	public static final String FIREBIRD_DATA = "firebird/data";
	public static final String ASSET_LOCATION = "main/resources/firebird";
	public static final String CORE_CRASH_LOCATION = "firebird/logs/crash_reports";
	public static final Executor EXECUTOR = Executors.newCachedThreadPool();
	public static final Scanner scanner = new Scanner(System.in);
	// init needed
	public static CacheManager MAIN_CACHE;
	public static CacheObject MAIN_CACHE_OBJECT;
	public static List<ICore> core;
	public static Timer CORE_UPDATER;
	public static List<IUpdateable> updater;
	public static boolean modules = false;
	public static Status STATUS = Status.NONE;
	public static Type type = Type.DEV;
	public static boolean firstTime = true;
	public static final ConfigFile CORE_DATA_FILE = new ConfigFile(
			new SaveFile(new FileLocation(CACHE_LOCATION, "firebird_data", Extensions.JSON)));

	public static void initCore() {

		try {

			Debug.printInfo("Starting core Init! Core Version: " + CoreInfo.CORE_VERSION, true);
			Debug.printInfo("Getting Settings...", CoreInfo.PRINT_INFO);

		} catch (Exception e) {
			CrashReporter.dispatchCrash(e);
			System.out.println("CoreFailure, Exiting");
			System.out.println(e.getMessage());
			System.exit(404);
		}
	}

	@Marked
	public static void initLists() {

		core = new ArrayList<>();
		updater = new ArrayList<>();

	}

	/**
	 * Clears the main cache code-side and reads it from the cache file.
	 */
	public static void reloadCache() {
		MAIN_CACHE_OBJECT = new CacheObject();
		MAIN_CACHE.populateCacheObject(MAIN_CACHE_OBJECT);
	}

	public static void saveCache() {
		MAIN_CACHE.writeCacheToFile(MAIN_CACHE_OBJECT);
	}

	public static Status getInitStatus() {
		return STATUS;
	}

	public static void setProduction() {
		type = Type.PROD;
	}

	public static Type getType() {

		return type;
	}

}
