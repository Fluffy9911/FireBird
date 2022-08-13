package main.games.dot;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import main.fire.core.Core;
import main.fire.game.BasicGame;
import main.fire.game.game.gameplay.BasicBB;

public class Dot {
	Color c;
	int size, x, y;
	BasicBB bounds;
	int midx, midy;
	boolean alive = true;
	VectorX vc;

	public Dot(Color c, int size, int x, int y) {
		this.c = c;
		this.size = size;
		this.x = x;
		this.y = y;
		vc = new VectorX(1);
		vc.addForce(new VectorX(new Random().nextInt(DotMain.dint)));
		bounds = new BasicBB(((BasicGame) Core.getGameProgram()).getDisplay(), x + 5, y + 5, size - 10, size - 10);

	}

	public void update(int x, int y) {
		this.x = x;
		this.y = y;
		this.midx = x / 2;
		this.midy = y / 2;
		bounds.updatePos(x + 5, y + 5, size - 10, size - 10);
	}

	@Override
	public String toString() {
		return "Dot [c=" + c + ", size=" + size + ", x=" + x + ", y=" + y + ", bounds=" + bounds + ", midx=" + midx
				+ ", midy=" + midy + ", alive=" + alive + ", vc=" + vc + "]";
	}

	public void kill() {
		bounds.setShouldrender(false);

		alive = false;

	}

	public void render(Graphics g) {
		// if (alive) {
		g.setColor(c);
		g.fillOval(x, y, size, size);
		// g.setColor(Color.WHITE);
		// g.drawString("" + size + " " + vc.toString(), x, y);

	}

	public BasicBB getBounds() {
		return bounds;
	}

	public void onCollision(Dot d) {

	}

	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public int getSize() {
		return size;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getMidx() {
		return midx;
	}

	public int getMidy() {
		return midy;
	}

	public void randomForce(float difficulty) {
		vc.addForce(new VectorX(new Random().nextInt(5, (int) DotMain.difficulty * 5)));
	}
}