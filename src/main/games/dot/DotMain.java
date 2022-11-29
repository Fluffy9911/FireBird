package main.games.dot;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import main.fire.cache.CacheObject;
import main.fire.game.BasicGame;
import main.fire.rendering.RenderingObject;
import main.fire.runtime.Startup;

public class DotMain extends BasicGame {
	static Point playerpos;
	static PlayerDot player;
	static float difficulty = .5f;
	static Manager mng;
	static int dint;
	static int level = 1;

	public DotMain(String name, int x, int y) {
		super(name, x, y);
		mng = new Manager((int) (25 + difficulty * 15), difficulty);
		dint = (int) (difficulty * 5);
	}

	@Override
	public void onEnd() {

	}

	public static void playerDeath() {

	}

	@Override
	public void tick() {
		if (player != null) {
			if (player.score >= 100) {
				mng.reset(5, 0.5f);
				dint = (int) (difficulty * 5);
				level++;
			}

			player.update(0, 0);
			mng.update();
		}
	}

	@Override
	public void saveCache(CacheObject obj) {

	}

	@Override
	public void loadCache(CacheObject m) {

	}

	@Override
	public void start() {

		this.getEngine().printThreadsStatus();
		playerpos = new Point(0, 0);
		player = new PlayerDot(null, 15, 50, 50);
		mng.start();

		new RenderingObject(this.getGameRenderer()) {

			@Override
			public void render(Graphics g) {
				Font f = g.getFont().deriveFont(g.getFont().getSize() + 500);
				g.setFont(f);
				player.render(g);
				mng.render(g);

				g.drawString("Score: " + player.score + " Difficulty: " + difficulty + " Level: " + level, 0, 15);

			}

		};

	}

	@Override
	public void loadAssets() {

	}

	@Override
	public void programEnd() {

	}

	@Override
	public String getProgramLocation() {

		return "firebird/dotgame";
	}

	public static void main(String[] args) {
		Startup.startGameInstance(new DotMain("Dotz", 600, 200));
	}

}
