package main.fire.rendering;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import main.fire.core.Core;
import main.fire.game.BasicGame;
import main.fire.util.PerformanceChecker;

public class Renderer {

	BufferedImage frame, lastFrame;
	Graphics graphics;
	int x, y;
	List<RenderingObject> toRender;
	List<PreRenderer> pres;
	SimpleDisplay ds;
	int frames = 0;

	public Renderer(SimpleDisplay ds) {
		frame = new BufferedImage(ds.getWidth(), ds.getHeight(), BufferedImage.TRANSLUCENT);
		frame.createGraphics();
		graphics = frame.getGraphics();
		x = ds.getWidth();
		y = ds.getHeight();
		this.ds = ds;
		toRender = new ArrayList<>();
		pres = new ArrayList<>();
		lastFrame = frame;
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				PerformanceChecker.check(((BasicGame) Core.getGameProgram()).getGameRenderer());
				frames = 0;
			}

		}, 2000, 1000);

	}

	public void tickRenderer() {
		long startTime = System.currentTimeMillis();

		graphics = frame.getGraphics();

		for (int i = 0; i < toRender.size(); i++) {

			toRender.get(i).render(graphics);

		}

		graphics.dispose();
		lastFrame = frame;
		frame = new BufferedImage(ds.getWidth(), ds.getHeight(), BufferedImage.TRANSLUCENT);
		long endTime = startTime - System.currentTimeMillis();
		if (endTime > 0)
			System.out.println("It took:" + endTime + "ms to draw last frame");
		frames++;

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

	public int getFrames() {
		return frames;
	}

}
