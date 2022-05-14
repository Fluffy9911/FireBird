package main.module.fire.testing;

import main.fire.cache.CacheManager;
import main.fire.cache.CacheObject;
import main.module.fire.FireBirdModule;

public class ExampleModule extends FireBirdModule {

	@Override
	public void init() {
		System.out.println("inited");

	}

	@Override
	public void cacheEvent(CacheObject obj, CacheManager m) {
		System.out.println("cached");
	}

	@Override
	public String uniqueID() {
		// TODO Auto-generated method stub
		return "test-module";
	}

	@Override
	public String version() {
		// TODO Auto-generated method stub
		return "1.0-beta";
	}

}
