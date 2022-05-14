package main.module.fire;

import main.fire.cache.CacheManager;
import main.fire.cache.CacheObject;

public abstract class FireBirdModule {

	public abstract void init();

	public abstract void cacheEvent(CacheObject obj, CacheManager m);

	public abstract String uniqueID();

	public abstract String version();

}
