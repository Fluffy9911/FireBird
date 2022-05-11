package main.fire.core;

import main.fire.cache.CacheObject;

public abstract class CoreModule {

	public abstract void cacheEvent();

	public abstract void loadCache(CacheObject obj);

	public abstract void save(CacheObject obj);

	public abstract String getModuleID();

	public abstract void coreInit();

}
