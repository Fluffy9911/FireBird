package main.fire.game.game;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ImageProvider {
	List<BufferedImage> images;
	String name;

	public ImageProvider(String name) {
		this.name = name;
		images = new ArrayList<>();

	}

	/**
	 * add in order of how you want to animate
	 * 
	 * @param image
	 */
	public ImageProvider addImage(BufferedImage image) {
		images.add(image);
		return this;
	}

	public List<BufferedImage> getImages() {
		return images;
	}

	public String getName() {
		return name;
	}

}
