package main.tap;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import main.fire.cache.CacheObject;
import main.fire.core.Core;
import main.fire.game.BasicGame;
import main.fire.rendering.RenderingObject;
import main.fire.runtime.Startup;

public class TapMain extends BasicGame {
	static Base base;
	static Spawner spawner;

	public TapMain(String name, int x, int y) {
		super(name, x, y);
		spawner = new Spawner(10);
	}

	static float difficulty = 0.5f;
	public static int dint = (int) (difficulty * 5);

	@Override
	public void onEnd() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tick() {
		base.update(0, ((BasicGame) Core.getGameProgram()).getDisplay().getHeight() - 65,
				((BasicGame) Core.getGameProgram()).getDisplay().getWidth(), 25);
		spawner.update();
	}

	@Override
	public void saveCache(CacheObject obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadCache(CacheObject m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void start() {
		base = new Base(
				new Color(new Random().nextInt(0, 255), new Random().nextInt(0, 255), new Random().nextInt(0, 255)), 0,
				((BasicGame) Core.getGameProgram()).getDisplay().getHeight() - 65,
				((BasicGame) Core.getGameProgram()).getDisplay().getWidth(), 25);
		spawner.start();
		new RenderingObject(this.getGameRenderer()) {

			@Override
			public void render(Graphics g) {
				base.render(g);
				spawner.render(g);
			}

		};
	}

	@Override
	public void loadAssets() {
		// TODO Auto-generated method stub

	}

	@Override
	public void programEnd() {
		// TODO Auto-generated method stub

	}

	public static void endGame() {

	}

	public static void main(String[] args) {
		Startup.startGameInstance(new TapMain("tap", 500, 500));
	}

	@Override
	public String getProgramLocation() {
		// TODO Auto-generated method stub
		return "tap";
	}
}
