package main.fire.cache;

public interface ICache {

	public void saveCache(CacheObject obj);

	public void loadCache(CacheObject obj);
}
