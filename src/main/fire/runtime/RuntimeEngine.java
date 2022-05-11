package main.fire.runtime;

import java.util.HashMap;
import java.util.Map;

import main.fire.cache.CacheObject;
import main.fire.core.Core;
import main.fire.core.CoreIO;
import main.fire.core.debug.Debug;
import main.fire.core.debug.MSCalc;
import main.fire.rendering.SimpleDisplay;
import main.fire.util.ICore;
import main.fire.util.Status;

public class RuntimeEngine implements ICore {
	SimpleDisplay display;
	CoreIO io;
	Map<String, GameThread> threads;

	public RuntimeEngine() {
		threads = new HashMap<>();

	}

	@Override
	public String getName() {

		return "runtime-engine";
	}

	@Override
	public void init() {
		if (Core.getInitStatus() == Status.STARTING) {
			Debug.error("Core not started fatal error occured!");
			return;
		}
		Debug.printInfo("Starting runtime init!");
		MSCalc c = new MSCalc();

		c.end();
		Debug.printInfo("It took: " + c.getEnd() + "ms to start the runtime engine");

	}

	@Override
	public void saveCache(CacheObject mainCache) {

	}

	@Override
	public void loadCache(CacheObject obj) {
		// TODO Auto-generated method stub

	}

	public GameThread createThread(String key) {
		GameThread t = new GameThread(key);
		threads.put(key, t);
		return t;
	}

	public void setDisplay(SimpleDisplay display) {
		this.display = display;
	}

}
