package main.fire.game.game.menu;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.fire.core.Core;
import main.fire.game.BasicGame;
import main.fire.game.game.gameplay.BasicBB;

public abstract class Menu {
	BasicBB bounds;
	int x, y, width, height;
	List<IMenuComponent> comps;

	public Menu(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds = new BasicBB(((BasicGame) Core.getGameProgram()).getDisplay(), x, y, width, height);
		comps = new ArrayList<>();

	}

	public BasicBB getBounds() {
		return bounds;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void tick() {
		for (Iterator iterator = comps.iterator(); iterator.hasNext();) {
			IMenuComponent m = (IMenuComponent) iterator.next();
			m.tick();
		}
	}

	public void render(Graphics g) {
		for (Iterator iterator = comps.iterator(); iterator.hasNext();) {
			IMenuComponent m = (IMenuComponent) iterator.next();
			m.render(g);
		}
	}

	public boolean clickedOn() {
		return bounds.rightClicked();
	}

	public List<IMenuComponent> getComps() {
		return comps;
	}

}