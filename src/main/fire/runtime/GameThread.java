package main.fire.runtime;

import java.util.ArrayList;
import java.util.List;

import main.fire.cache.CacheObject;
import main.fire.util.ICore;

public class GameThread implements ICore {
	String name;
	Thread thread;
	List<IRun> toRun;
	static int id = 1;

	public GameThread(String name) {
		this.name = name;
		toRun = new ArrayList<>();
		thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					// System.out.println("running?");
					for (int i = 0; i < toRun.size(); i++) {
						toRun.get(i).run();

					}
				}
			}

		});

		this.subscribeToCoreEvents(this);
		id++;
	}

	@Override
	public String getName() {

		return name;
	}

	@Override
	public void init() {

	}

	public void addRun(IRun rn) {
		toRun.add(rn);
	}

	public void startThread() {
		if (!thread.isAlive())
			thread.start();
	}

	@Override
	public void saveCache(CacheObject mainCache) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadCache(CacheObject obj) {
		// TODO Auto-generated method stub

	}

	public List<IRun> getToRun() {
		return toRun;
	}

}
