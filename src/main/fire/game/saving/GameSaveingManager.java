package main.fire.game.saving;

import main.fire.cache.Cache;
import main.fire.cache.CacheManager;
import main.fire.cache.CacheObject;
import main.fire.game.BasicGame;
import main.fire.util.Dual;

public class GameSaveingManager {
	BasicGame game;
	CacheManager saver;
	CacheObject cacheMap;

	public GameSaveingManager(BasicGame game) {
		this.game = game;
	}

	void init() {
		Dual<CacheManager, CacheObject> cache = Cache.createCacheDual(game.getName());
		saver = cache.getOne();
		cacheMap = cache.getTwo();
	}

	public CacheObject getCacheData() {
		saver.populateCacheObject(cacheMap);
		return cacheMap;
	}

	public void writeFile() {
		saver.writeCacheToFile(cacheMap);
	}

}
