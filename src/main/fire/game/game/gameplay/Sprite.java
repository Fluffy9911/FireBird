package main.fire.game.game.gameplay;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class Sprite {

	BufferedImage texture;
	int resolutionx = (int) Math.floor(Math.sqrt(Toolkit.getDefaultToolkit().getScreenSize().getWidth()));
	int resolutiony = (int) Math.floor(Math.sqrt(Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
	int x, y, width = 16 * resolutionx, height = 16 * resolutiony;

}
