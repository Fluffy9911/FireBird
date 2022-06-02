package main.fire.game;

import java.io.File;

import main.fire.cache.Cache;
import main.fire.cache.CacheManager;
import main.fire.cache.CacheObject;
import main.fire.cache.ICache;
import main.fire.core.Core;
import main.fire.core.debug.Debug;
import main.fire.core.debug.MSCalc;
import main.fire.game.IO.KeyManager;
import main.fire.rendering.SimpleDisplay;
import main.fire.runtime.GameThread;
import main.fire.runtime.RuntimeEngine;
import main.fire.util.Dual;
import main.fire.util.IUpdateable;
import main.fire.util.PathMaker;
import main.module.fire.ModuleLoader;

public abstract class Program implements IUpdateable, ICache {
	ProgramHolder holder;
	RuntimeEngine engine;
	String name;
	File location;
	GameThread programThread;
	SimpleDisplay display;
	KeyManager keyManager;
	Dual<CacheManager, CacheObject> cache;

	public Program(String name) {
		addModulesEvent();
		Core.initCore();
		keyManager = new KeyManager(this);
		PathMaker.makePath("firebird/cache/" + name);
		try {
			cache = Cache.createCacheDual(name + "/" + name);
			cache.getOne().populateCacheObject(cache.getTwo());
			cache.setTwo(new CacheObject());
			cache.getOne().populateCacheObject(cache.getTwo());
			loadCache(cache.getTwo());
			saveCache(cache.getTwo());

		} catch (Exception e) {
			e.printStackTrace();
			Debug.error("Failed during cache startup");
		}
		this.subscribeToUpdater(this);
		Debug.printInfo("Program starting...");
		try {
			MSCalc c = new MSCalc();
			this.engine = new RuntimeEngine();
			this.name = name;

			location = new File("firebird/cache/" + name);

			programThread = engine.createThread(name + "-thread");

			c.end();
			Debug.printInfo("It took: " + c.getEnd() + "ms to start init the program");
			Debug.printInfo("loaded modules " + ModuleLoader.modules.toString());
		} catch (Exception e) {
			Debug.debugError(getClass(), e);
		}
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				programEnd();
				saveCache(Core.MAIN_CACHE_OBJECT);

			}

		});
		Core.addProgram(this);
	}

	public void addDisplay(int width, int height) {
		System.out.println("made display");
		display = new SimpleDisplay(width, height, name, this);
		engine.setDisplay(display);
	}

	public void addModulesEvent() {

	}

	@Override
	public abstract void saveCache(CacheObject obj);

	@Override
	public abstract void loadCache(CacheObject m);

	public abstract void start();

	public abstract void loadAssets();

	public abstract void programEnd();

	public Dual<CacheManager, CacheObject> getCacheForProgram() {
		return cache;
	}

	public void cacheSaveAndLoad(ICache c, CacheObject obj) {
		getCacheForProgram().getOne().populateCacheObject(obj);
		c.saveCache(obj);
		c.loadCache(obj);
		this.getCacheForProgram().getOne().writeCacheToFile(obj);

	}

	@Override
	public void update() {
		this.saveCache(this.getCacheForProgram().getTwo());
		this.getCacheForProgram().getOne().writeCacheToFile(this.getCacheForProgram().getTwo());
	}

	public RuntimeEngine getEngine() {
		return engine;
	}

	public String getName() {
		return name;
	}

	public File getLocation() {
		return location;
	}

	public GameThread getProgramThread() {
		return programThread;
	}

	public KeyManager getKeyManager() {
		return keyManager;
	}

}
