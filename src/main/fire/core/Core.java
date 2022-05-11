package main.fire.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import main.fire.cache.CacheManager;
import main.fire.cache.CacheObject;
import main.fire.core.debug.Debug;
import main.fire.core.debug.MSCalc;
import main.fire.resources.ResourceFinder;
import main.fire.util.ICore;
import main.fire.util.IUpdateable;
import main.fire.util.Status;
import main.fire.util.Type;

public class Core {
	// resources
	public static final String CACHE_LOCATION = "firebird/cache";
	public static final String CACHE = "main_cache";
	// init needed
	public static CacheManager MAIN_CACHE;
	public static CacheObject MAIN_CACHE_OBJECT;
	public static List<ICore> core;
	public static Timer CORE_UPDATER;
	public static List<IUpdateable> updater;
	public static List<CoreModule> modules = createModules();
	public static Status STATUS = Status.NONE;
	public static Type type = Type.DEV;
	public static boolean firstTime = true;

	public static void initCore() {
		Debug.init();
		Debug.printInfo("Starting core Init! Core Version: " + CoreInfo.CORE_VERSION);
		MSCalc calc = new MSCalc();
		Debug.printInfo("Looking for modules");
		if (!modules.isEmpty()) {
			Debug.printInfo("Starting module init");
			for (int i = 0; i < modules.size(); i++) {
				MSCalc c = new MSCalc();
				modules.get(i).coreInit();
				c.end();
				Debug.printInfo("It took: " + c.getEnd() + "ms to init: " + modules.get(i).getModuleID());

			}
		}
		STATUS = Status.STARTING;
		initLists();

		cacheEvent();

		STATUS = Status.DONE;
		calc.end();
		Debug.printInfo("It took: " + calc.getEnd() + "ms to init the core.");
		for (ICore c : core) {
			c.init();
		}
		Debug.printInfo("Starting updater...");
		startUpdater();
	}

	private static List<CoreModule> createModules() {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

	public static void cacheEvent() {
		ResourceFinder.checkForFileExistance();
		Debug.printInfo("Is first time setup? " + firstTime);
		MAIN_CACHE = new CacheManager();
		MAIN_CACHE_OBJECT = new CacheObject();
		if (firstTime) {
			MAIN_CACHE.createCoreFileForCaching(CACHE);
		} else {
			MAIN_CACHE.setCacheFile(ResourceFinder.getCoreCache());
			ResourceFinder.firstTimeSetupCache(MAIN_CACHE_OBJECT);
		}
		for (int i = 0; i < modules.size(); i++) {
			modules.get(i).cacheEvent();
			modules.get(i).loadCache(MAIN_CACHE_OBJECT);
			modules.get(i).save(MAIN_CACHE_OBJECT);
		}
		reloadCache();
		for (ICore c : core) {
			c.loadCache(MAIN_CACHE_OBJECT);
		}
		for (ICore c : core) {
			c.saveCache(MAIN_CACHE_OBJECT);
		}
		saveCache();

	}

	public static void initLists() {

		core = new ArrayList<>();
		updater = new ArrayList<>();

	}

	public static void startUpdater() {
		CORE_UPDATER = new Timer();
		CORE_UPDATER.schedule(new TimerTask() {

			@Override
			public void run() {
				for (IUpdateable i : updater) {
					i.update();
				}
			}

		}, 0, 1000);
		Debug.printInfo("Updater working");
	}

	/**
	 * Clears the main cache code-side and reads it from the cache file.
	 */
	public static void reloadCache() {
		MAIN_CACHE_OBJECT = new CacheObject();
		MAIN_CACHE.populateMap(MAIN_CACHE_OBJECT);
	}

	public static void saveCache() {
		MAIN_CACHE.writeCacheToFile(MAIN_CACHE_OBJECT);
	}

	public static void startCore(CoreIO io) {
		initCore();
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
