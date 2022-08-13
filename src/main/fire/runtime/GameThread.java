package main.fire.runtime;

import java.util.ArrayList;
import java.util.List;

import main.fire.cache.CacheObject;
import main.fire.util.ICore;
import main.fire.util.Status;

public class GameThread implements ICore {
	String name;
	Thread thread;
	List<IRun> toRun;
	static int id = 1;
	Status s = Status.NONE;

	public GameThread(String name) {
		this.name = name;
		s = Status.STARTING;
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
		thread.setName(name);
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
		if (!thread.isAlive()) {
			thread.start();
			s = Status.DONE;
		}

	}

	@Override
	public void saveCache(CacheObject mainCache) {
		// TODO Auto-generated method stub

	}

	public String getStatus() {
		return toString();
	}

	@Override
	public String toString() {
		return "GameThread Status [name=" + name + ", thread_name=" + thread.getName() + ", To Run=" + toRun.toString()
				+ ", Status=" + s.toString() + ", ThreadStatus: " + thread.toString() + "]";
	}

	@Override
	public void loadCache(CacheObject obj) {
		// TODO Auto-generated method stub

	}

	public String runStatus() {

		return toRun.toString();
	}

	public List<IRun> getToRun() {
		return toRun;
	}

}
