package main.fire.core;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import main.fire.anotations.Marked;
import main.fire.cache.CacheManager;
import main.fire.cache.CacheObject;
import main.fire.core.debug.CrashReporter;
import main.fire.core.debug.Debug;
import main.fire.core.debug.MSCalc;
import main.fire.exception.CacheException;
import main.fire.game.BasicGame;
import main.fire.game.Program;
import main.fire.game.assets.AssetLoader;
import main.fire.game.state.StateManager;
import main.fire.rendering.RenderingObject;
import main.fire.resources.FileLocation;
import main.fire.resources.ResourceFinder;
import main.fire.runtime.IRun;
import main.fire.util.ICore;
import main.fire.util.IUpdateable;
import main.fire.util.Status;
import main.fire.util.StatusMarker;
import main.fire.util.Type;
import main.module.fire.ModuleLoader;

public class Core {
	// resources
	public static final String CACHE_LOCATION = "firebird/cache";
	public static final String CACHE = "main_cache";
	public static final String FIREBIRD_DATA = "firebird/data";
	public static final String ASSET_LOCATION = "main/resources/firebird";
	public static final String CORE_CRASH_LOCATION = "firebird/logs/crash_reports";
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
	public static Program program;
	private static AssetLoader INTERNAL_ASSETS = AssetLoader.create();
	public static FileLocation programLocation;

	public static void initCore() {
		try {
			StatusMarker st = StatusMarker.create("CORE");
			Debug.printInfo("Getting settings...", true);
			Debug.printInfo("Starting core Init! Core Version: " + CoreInfo.CORE_VERSION + " in " + type.toString(),
					true);
			loadInternalAssets();
			MSCalc calc = new MSCalc();
			modules = ModuleLoader.checkForModules();
			if (modules)
				ModuleLoader.init();

			cacheEvent();

			calc.end();
			Debug.printInfo("It took: " + calc.getEnd() + "ms to init the core.", false);
			for (ICore c : core) {
				c.init();
			}
			Debug.printInfo("Starting updater...", true);
			startUpdater();
			st.end();
		} catch (Exception e) {
			CrashReporter.dispatchCrash(e);
			System.out.println("CoreFailure, Exiting");
			System.out.println(e.getMessage());
			System.exit(404);
		}
	}

	public static void cacheEvent() {
		ResourceFinder.checkForFileExistance();
		Debug.printInfo("Is first time setup? " + firstTime, false);
		MAIN_CACHE = new CacheManager();
		MAIN_CACHE_OBJECT = new CacheObject();
		if (firstTime) {
			try {
				MAIN_CACHE.createCoreFileForCaching(CACHE);
			} catch (CacheException e) {
				Debug.debugError(Core.class, e);
			}
		} else {
			MAIN_CACHE.setCacheFile(ResourceFinder.getCoreCache());
			ResourceFinder.firstTimeSetupCache(MAIN_CACHE_OBJECT);
		}

		reloadCache();
		for (ICore c : core) {
			c.loadCache(MAIN_CACHE_OBJECT);
		}
		for (ICore c : core) {
			c.saveCache(MAIN_CACHE_OBJECT);
		}
		saveCache();
		if (modules)
			ModuleLoader.cacheEvent(MAIN_CACHE_OBJECT, MAIN_CACHE);
	}

	@Marked
	public static void initLists() {

		core = new ArrayList<>();
		updater = new ArrayList<>();
		INTERNAL_ASSETS = new AssetLoader();

	}

	public static boolean loadInternalAssets() {
		try {
			INTERNAL_ASSETS.addBasePath(ASSET_LOCATION);
			INTERNAL_ASSETS.addTexture("rectangle_16", "assets");
			INTERNAL_ASSETS.addTexture("rectangle_32", "assets");
			INTERNAL_ASSETS.addTexture("rectangle_64", "assets");
			INTERNAL_ASSETS.addTexture("rectangle_128", "assets");
		} catch (Exception e) {
			Debug.debugError(Core.class, e);
			return false;
		}
		return true;
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
		Debug.printInfo("Updater working", true);
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

	public static void addProgram(Program prg) {
		program = prg;

	}

	public static Program getGameProgram() {
		return program;
	}

	public static void gameStart(BasicGame game) {
		game.getProgramThread().addRun(new IRun() {

			@Override
			public void run() {
				StateManager.tick();
			}

		});
		new RenderingObject(game.getGameRenderer()) {

			@Override
			public void render(Graphics g) {
				StateManager.render(g);
			}

		};

	}

}
