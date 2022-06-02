package main.fire.core;

import main.fire.cache.Cache;
import main.fire.cache.CacheManager;
import main.fire.game.Program;

public class CoreIO {
	Program pg;

	public void startProgram(Program p) {
		this.pg = p;
		Core.addProgram(p);
		p.getProgramThread().startThread();
		p.start();

	}

	public CacheManager createChache(String name) {
		return Cache.createCacheDual(name).getOne();

	}

	public Program getProgram() {
		return pg;
	}

}
