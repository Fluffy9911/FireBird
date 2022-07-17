package main.fire.game.game.gameplay;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import main.fire.rendering.RenderUtils;
import main.fire.rendering.Renderer;
import main.fire.rendering.RenderingObject;
import main.fire.rendering.SimpleDisplay;
import main.fire.runtime.IRun;
import main.fire.util.MainTick;

public class SimpleBB implements MainTick {
	Renderer render;
	int x, y, width, height;
	Rectangle bounds;
	boolean shouldrender = false;

	public SimpleBB(SimpleDisplay ds, int x, int y, int width, int height) {
		this.render = ds.getRender();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.bounds = new Rectangle(x, y, width, height);
		ds.getProgram().getProgramThread().addRun(new IRun() {

			@Override
			public void run() {
				tick();
			}

		});
	}

	@Override
	public void tick() {
		if (shouldrender)
			new RenderingObject(render) {

				@Override
				public void render(Graphics g) {

					g.setColor(Color.RED);
					RenderUtils.drawRectangle(bounds, g);

				}

			};

		this.updatePos(x, y, width, height);
	}

	public void updatePos(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.bounds.setBounds(x, y, width, height);

	}

	@Override
	public String toString() {
		return "SimpleBB [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + "]";
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public boolean Shouldrender() {
		return shouldrender;
	}

	public void setShouldrender(boolean shouldrender) {
		this.shouldrender = shouldrender;
	}

}
