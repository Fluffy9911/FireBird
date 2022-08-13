package main.tap;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.fire.core.Core;
import main.fire.game.BasicGame;

public class Spawner {
	int amount, start;
	List<Dot> alive;

	public Spawner(int amount) {
		this.amount = amount;
		this.start = amount;
		this.alive = new ArrayList<>();
	}

	public void start() {
		for (int i = 0; i < amount; i++) {
			alive.add(new Dot(
					new Color(new Random().nextInt(0, 255), new Random().nextInt(0, 255), new Random().nextInt(0, 255)),
					new Random().nextInt(20, 35),
					new Random().nextInt(((BasicGame) Core.getGameProgram()).getDisplay().getWidth()), 0));
		}
	}

	public void update() {
		if (!alive.isEmpty())
			for (int i = 0; i < alive.size(); i++) {

				if (!alive.get(i).alive) {
					alive.remove(alive.get(i));
				}
				if (alive.get(i).getBounds().intersects(TapMain.base.bounds)) {
					TapMain.base.onDotCollision(alive.get(i));
				}
				alive.get(i).update(alive.get(i).x, alive.get(i).y + alive.get(i).vc.getYamnt());
			}
	}

	public void render(Graphics g) {
		for (int i = 0; i < alive.size(); i++) {

			alive.get(i).render(g);
		}
	}

}
