package main.fire.rendering;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Renderer {

	BufferedImage frame, lastFrame;
	Graphics graphics;
	int x, y;
	List<RenderingObject> toRender;
	List<PreRenderer> pres;

	public Renderer(SimpleDisplay ds) {
		frame = new BufferedImage(ds.getWidth(), ds.getHeight(), BufferedImage.TRANSLUCENT);
		frame.createGraphics();
		graphics = frame.getGraphics();
		x = ds.getWidth();
		y = ds.getHeight();
		toRender = new ArrayList<>();
		pres = new ArrayList<>();
		lastFrame = frame;
	}

	public void tickRenderer() {
		long startTime = System.currentTimeMillis();

		graphics = frame.getGraphics();

		for (int i = 0; i < toRender.size(); i++) {

			toRender.get(i).render(graphics);

		}

		for (int i = 0; i < pres.size(); i++) {

			frame = pres.get(i).render(frame);

		}
		graphics.dispose();
		lastFrame = frame;
		frame = new BufferedImage(x, y, BufferedImage.TRANSLUCENT);
		long endTime = startTime - System.currentTimeMillis();
		if (endTime > 0)
			System.out.println("It took:" + endTime + "ms to draw last frame");
	}

	public BufferedImage getFrame() {
		return lastFrame;
	}

	public Graphics getGraphics() {
		return graphics;
	}

	public List<RenderingObject> getToRender() {
		return toRender;
	}

	public List<PreRenderer> getPres() {
		return pres;
	}

}
