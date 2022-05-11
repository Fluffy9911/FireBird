package main.fire.rendering;

import java.awt.Graphics;
import java.awt.Rectangle;

public class RenderUtils {

	public static void drawRectangle(Rectangle r, Graphics g) {
		g.drawRect(r.x, r.y, r.width, r.height);

	}

}
