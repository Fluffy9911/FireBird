package main.fire.cache;

import main.fire.util.Dual;

public class Cache {

	public static Dual<CacheManager, CacheObject> createCacheDual(String name) {
		CacheManager manager = new CacheManager();
		manager.createCoreFileForCaching(name);
		CacheObject obj = new CacheObject();
		return new Dual<CacheManager, CacheObject>(manager, obj);
	}

	public static CacheObject loadCache(CacheManager mngr) {
		CacheObject c = new CacheObject();
		mngr.populateMap(c);
		return c;
	}

}
