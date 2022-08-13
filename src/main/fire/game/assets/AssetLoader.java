package main.fire.game.assets;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import main.fire.core.debug.Debug;

public class AssetLoader {

	private Map<String, URL> preLoaded;
	private String basePath = "";
	private Map<String, BufferedImage> loaded;
	private boolean isloaded = false;

	public AssetLoader() {

		this.preLoaded = new HashMap<>();
		this.loaded = new HashMap<>();

	}

	public void addBasePath(String path) {
		this.basePath = path;
	}

	public void loadAsset() {
		for (Map.Entry<String, URL> entry : preLoaded.entrySet()) {
			BufferedImage img = new BufferedImage(16, 16, BufferedImage.OPAQUE);
			try {
				img = ImageIO.read(entry.getValue());
				Debug.printInfo("Loaded image: " + entry.getKey(), true);
			} catch (IOException e) {
				Debug.debugError(getClass(), e);
				Debug.error("Error Loading image: " + entry.getKey());
			}

			loaded.put(entry.getKey(), img);

		}
		isloaded = true;
	}

	public void addTexture(String name, String sub) {
		if (!this.validatePath(name, sub)) {
			Debug.error("Invalid Texture Location: " + name);
			Debug.error("/" + basePath + "/" + sub + "/" + name + ".png");
			return;
		}
		if (this.validatePath(name, sub))
			preLoaded.put(name, getFileLocation(name, sub));

	}

	public BufferedImage getTexture(String key) {
		if (!isloaded) {
			Debug.error("Unable to get texture due to textures not being loaded!");

			return null;
		}
		if (loaded.get(key) == null) {
			Debug.error("inputted field does not match any current textures");
			return null;
		}
		return loaded.get(key);
	}

	public boolean validatePath(String name, String sub) {

		if (this.getFileLocation(name, sub) != null) {
			return true;
		}
		return false;
	}

	public URL getFileLocation(String name, String sub) {

		return this.getClass().getResource("/" + basePath + "/" + sub + "/" + name + ".png");
	}

	public Map<String, BufferedImage> getLoaded() {
		return loaded;
	}

	public static AssetLoader create() {
		return new AssetLoader();
	}
}
