package main.games.dot;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.fire.core.Core;
import main.fire.game.BasicGame;

public class Manager {
	List<Dot> alive, discard;
	int maxAmount;
	float difficulty;

	public Manager(int maxAmount, float difficulty) {
		this.maxAmount = maxAmount;
		this.difficulty = difficulty;
		alive = new ArrayList<>();
		discard = new ArrayList<>();
	}

	public void reset(int add, float diff) {
		maxAmount += add;
		DotMain.difficulty += diff;
		DotMain.player = new PlayerDot(null, 15, 50, 50);
		for (int i = 0; i < alive.size(); i++) {
			alive.get(i).kill();

		}
		discard.clear();
		alive.clear();
		start();

	}

	public void start() {
		for (int i = 0; i < maxAmount; i++) {
			if (new Random().nextInt(100) < 25) {
				alive.add(new Dot(
						new Color(new Random().nextInt(0, 255), new Random().nextInt(0, 255),
								new Random().nextInt(0, 255)),
						new Random().nextInt(DotMain.player.getSize() + 5, DotMain.player.getSize() + 50), 0,
						new Random().nextInt(0, ((BasicGame) Core.getGameProgram()).getDisplay().getHeight())));
			} else {
				alive.add(new Dot(
						new Color(new Random().nextInt(0, 255), new Random().nextInt(0, 255),
								new Random().nextInt(0, 255)),
						new Random().nextInt(DotMain.player.getSize() - 1, DotMain.player.getSize()), 0,
						new Random().nextInt(0, ((BasicGame) Core.getGameProgram()).getDisplay().getHeight())));
			}

		}
	}

	public void update() {
		if (!DotMain.player.alive) {
			DotMain.player = new PlayerDot(null, 15, 50, 50);
			DotMain.dint = (int) (DotMain.difficulty * 5);
			for (int i = 0; i < alive.size(); i++) {
				alive.get(i).kill();

			}
			discard.clear();
			alive.clear();
			maxAmount = (int) (20 + difficulty * 15);
			DotMain.difficulty = 0.5f;
			DotMain.level = 1;
			start();
		}
		for (int i = 0; i < alive.size(); i++) {
			if (!alive.get(i).alive) {
				discard.add(alive.get(i));
				alive.remove(alive.get(i));
				update();
			}
			if (!(i > alive.size()))
				if (alive.get(i).getBounds().intersects(DotMain.player.getBounds())) {
					DotMain.player.onCollision(alive.get(i));
				}
			respawn();
			// System.out.println(DotMain.player.getBounds().intersects(alive.get(i).getBounds()));
			alive.get(i).update(alive.get(i).getX() + alive.get(i).vc.getXamnt(), alive.get(i).getY());
			spawn();
		}
	}

	public void respawn() {
		for (int i = 0; i < alive.size(); i++) {
			if (alive.get(i).x > ((BasicGame) Core.getGameProgram()).getDisplay().getWidth()) {
				alive.get(i).x = 0;
				alive.get(i).y = new Random().nextInt(((BasicGame) Core.getGameProgram()).getDisplay().getHeight());
			}

		}
	}

	public void spawn() {
		int j = maxAmount - alive.size();
		for (int i = 0; i < j; i++) {
			alive.add(new Dot(
					new Color(new Random().nextInt(0, 255), new Random().nextInt(0, 255), new Random().nextInt(0, 255)),
					new Random().nextInt(DotMain.player.getSize() - 5,
							(int) (DotMain.player.getSize() + difficulty * 10)),
					0, new Random().nextInt(0, ((BasicGame) Core.getGameProgram()).getDisplay().getHeight())));
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < alive.size(); i++) {
			alive.get(i).render(g);

		}
	}

}
