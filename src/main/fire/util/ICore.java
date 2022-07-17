package main.fire.util;

import main.fire.cache.CacheObject;
import main.fire.core.Core;

public interface ICore {
	/**
	 * 
	 * 
	 * @return the objects name
	 */
	public String getName();

	/**
	 * Called after core init. Called before the runtime engine is created nor
	 * initailized; Its safe to call Core cacheing functions happens after this
	 * object is created!
	 */
	public void init();

	/**
	 * Call if you want to subscribe this object to core events
	 * 
	 * @param core
	 */
	public default void subscribeToCoreEvents(ICore core) {

		Core.core.add(core);

	}

	public void saveCache(CacheObject mainCache);

	public void loadCache(CacheObject obj);
}
