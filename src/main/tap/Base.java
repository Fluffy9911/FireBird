package main.tap;

import java.awt.Color;
import java.awt.Graphics;

import main.fire.core.Core;
import main.fire.game.BasicGame;
import main.fire.game.game.gameplay.BasicBB;

public class Base {
	BasicBB bounds;
	Color c;
	int x, y, width, height;

	public Base(Color c, int x, int y, int width, int height) {
		this.c = c;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds = new BasicBB(((BasicGame) Core.getGameProgram()).getDisplay(), x, y, width, height);
		bounds.setShouldrender(true);
	}

	public void update(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.bounds.updatePos(x, y, width, height);
	}

	public void render(Graphics g) {
		g.setColor(c);
		g.fillRect(x, y, width, height);
	}

	public void onDotCollision(Dot d) {
		TapMain.endGame();
		d.kill();
	}
}
