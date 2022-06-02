package main.module.fire.pixelBuilder;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import main.fire.cache.CacheManager;
import main.fire.cache.CacheObject;
import main.fire.exception.CacheException;
import main.fire.util.Dual;

public class ImageToPixel {

	BufferedImage convert;
	File folder;

	public static void createFile(BufferedImage image, String name) throws CacheException {
		CacheObject obj = new CacheObject();
		for (int i = 0; i < image.getHeight(); i++) {
			for (int j = 0; j < image.getWidth(); j++) {
				var v = new Color(image.getRGB(i, j));
				var d = new Dual<Integer, Integer>(i, j);
				obj.add(d, obj);
			}
		}
		var c = new CacheManager();
		c.createCacheFile(name, "firebird/img");
		c.writeCacheToFile(obj);
	}

}
