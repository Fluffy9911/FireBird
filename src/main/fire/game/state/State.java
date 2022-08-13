package main.fire.game.state;

import java.awt.Graphics;

import main.fire.game.BasicGame;

public abstract class State {
	BasicGame game;
	boolean on = false;

	public void start() {
		init();
		on = true;
	}

	public void stop() {
		on = false;
	}

	public boolean isOn() {
		return on;
	}

	public abstract void init();

	public abstract void tick();

	public abstract void render(Graphics g);

}
