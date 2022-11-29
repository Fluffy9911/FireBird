package main.fire.rendering;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import main.fire.core.Core;
import main.fire.game.BasicGame;
import main.fire.util.PerformanceChecker;

public class Renderer {

	BufferedImage frame;
	Graphics graphics;
	Dimension imageDim;
	List<RenderingObject> toRender;
	SimpleDisplay ds;
	int frames = 0;

	public Renderer(SimpleDisplay ds) {
		frame = new BufferedImage(ds.getWidth(), ds.getHeight(), BufferedImage.BITMASK);
		this.ds = ds;
		graphics = frame.createGraphics();
		imageDim = new Dimension(ds.getWidth(), ds.getHeight());

		toRender = new ArrayList<>();

		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				if (Core.getGameProgram() instanceof BasicGame)
					PerformanceChecker.check(((BasicGame) Core.getGameProgram()).getGameRenderer());
				frames = 0;
			}

		}, 2000, 1000);

	}

	public synchronized BufferedImage show() {

		long startTime = System.currentTimeMillis();
		for (Iterator iterator = toRender.iterator(); iterator.hasNext();) {
			RenderingObject ob = (RenderingObject) iterator.next();
			ob.render(graphics);
		}
		long end = System.currentTimeMillis() - startTime;
		frames++;
		return this.frame;

	}

	void updateSize(Dimension size) {
		if (!this.imageDim.equals(size)) {
			frame = new BufferedImage(size.width, size.height, BufferedImage.BITMASK);
			this.graphics = frame.createGraphics();
			imageDim = size;
		}
	}

	public void end() {
		graphics.clearRect(0, 0, (int) imageDim.getWidth(), (int) imageDim.getHeight());
	}

	public synchronized List<RenderingObject> getToRender() {
		return toRender;
	}

	public int getFrames() {
		return frames;
	}

	public BufferedImage pixelate(Dimension size) {
		BufferedImage frame = show();
		BufferedImage show = new BufferedImage(size.width, size.height, BufferedImage.TRANSLUCENT);
		show.createGraphics();
		show.getGraphics().drawImage(frame, 0, 0, size.width, size.height, null);
		return show;
	}

	public SimpleDisplay getDisplay() {
		return ds;
	}

	public FontMetrics getFontMetrics() {
		return graphics.getFontMetrics();
	}
}
