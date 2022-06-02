package main.fire.cache;

import main.fire.core.debug.Debug;
import main.fire.exception.CacheException;
import main.fire.util.Dual;

/**
 * a utility file to help make some useful things.
 * 
 * @author big_r
 *
 */
public class Cache {

	/**
	 * 
	 * @param name - the name of the cache file
	 * @return a new {@link Dual} with a {@link CacheManager} and
	 *         {@link CacheObject}
	 */
	public static Dual<CacheManager, CacheObject> createCacheDual(String name) {
		CacheManager manager = new CacheManager();
		try {
			manager.createCoreFileForCaching(name);
		} catch (CacheException e) {
			Debug.debugError(Cache.class, e);
		}
		CacheObject obj = new CacheObject();
		return new Dual<CacheManager, CacheObject>(manager, obj);
	}

	/**
	 * 
	 * @param mngr returns a new {@link CacheObject} and populates it with the data
	 *             in the {@link CacheManager}
	 * @return the new {@link CacheObject}
	 */
	public static CacheObject loadCache(CacheManager mngr) {
		CacheObject c = new CacheObject();
		mngr.populateCacheObject(c);
		return c;
	}

}
