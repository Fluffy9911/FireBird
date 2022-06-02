package main.fire.game;

import java.io.File;

import main.fire.cache.CacheManager;
import main.fire.cache.CacheObject;
import main.fire.exception.CacheException;
import main.fire.util.PathMaker;

public class ProgramHolder {
	File mainLocation, assetFile, configFile;
	Program pg;
	BasicGame game;
	CacheObject obj;
	CacheManager manager;
	String name;

	public ProgramHolder(String name, Program program) {
		if (program instanceof BasicGame) {
			this.game = (BasicGame) program;
		} else {
			this.pg = program;
		}
		PathMaker.makePath("firebird/cache/" + name);
		this.mainLocation = new File("firebird/configs/" + name + "_main");
		this.assetFile = new File("firebird/configs/" + name + "_assets.fbc");
		this.configFile = new File("firebird/configs/" + name + "_configs.fbc");
		this.obj = new CacheObject();
		this.manager = new CacheManager();
		this.name = name;
	}

	public void saveInfoToMainFile() throws CacheException {
		var c = new CacheObject();
		if (game != null) {
			c.add("assets_location", this.assetFile.getPath());
		}
		c.add("configs_location", this.configFile.getPath());
		manager.createCacheFile(name + "_main", this.mainLocation.getPath());
		manager.writeCacheToFile(c);
	}

}
