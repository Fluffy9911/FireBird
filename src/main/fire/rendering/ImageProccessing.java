package main.fire.rendering;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

public class ImageProccessing {

	public static BufferedImage resizeImage(BufferedImage image, Dimension desired) {
		var orig = image;
		BufferedImage nw = new BufferedImage(desired.width, desired.height, BufferedImage.TRANSLUCENT);
		nw.createGraphics();
		nw.getGraphics().drawImage(orig, 0, 0, desired.width, desired.height, null);
		return nw;
	}

}
