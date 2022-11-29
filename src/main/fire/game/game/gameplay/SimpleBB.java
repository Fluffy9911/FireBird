package main.fire.game.game.gameplay;

import java.awt.Rectangle;
import java.io.Serializable;

import main.fire.util.MainTick;

public class SimpleBB implements MainTick, Serializable {

	int x, y, width, height;
	Rectangle bounds;
	boolean shouldrender = false;

	public SimpleBB(int x, int y, int width, int height) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.bounds = new Rectangle(x, y, width, height);

	}

	@Override
	public void tick() {

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
