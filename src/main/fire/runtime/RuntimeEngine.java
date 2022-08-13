package main.fire.runtime;

import java.util.HashMap;
import java.util.Map;

import main.fire.cache.CacheObject;
import main.fire.core.Core;
import main.fire.core.debug.Debug;
import main.fire.core.debug.MSCalc;
import main.fire.rendering.SimpleDisplay;
import main.fire.util.ICore;
import main.fire.util.Status;
import main.fire.util.StatusMarker;

public class RuntimeEngine implements ICore {
	SimpleDisplay display;

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
		var a = StatusMarker.create("Runtime");
		if (Core.getInitStatus() == Status.STARTING) {
			Debug.error("Core not started fatal error occured!");
			return;
		}

		MSCalc c = new MSCalc();

		c.end();
		a.end();
	}

	@Override
	public void saveCache(CacheObject mainCache) {

	}

	public GameThread getThread(String key) {
		return threads.get(key);
	}

	@Override
	public void loadCache(CacheObject obj) {
		// TODO Auto-generated method stub

	}

	public GameThread createThread(String key) {
		GameThread t = new GameThread(key);
		threads.put(key, t);
		Debug.printInfo(t.getStatus(), true);
		return t;
	}

	public void printThreadsStatus() {
		for (Map.Entry<String, GameThread> entry : threads.entrySet()) {
			String key = entry.getKey();
			GameThread val = entry.getValue();
			Debug.printInfo(val.getStatus(), false);
		}
	}

	public void setDisplay(SimpleDisplay display) {
		this.display = display;
	}

}
