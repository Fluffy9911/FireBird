package main.games.dot;

import java.awt.Color;

import main.fire.core.Core;
import main.fire.game.BasicGame;

public class PlayerDot extends Dot {
	int score = 1;

	public PlayerDot(Color c, int size, int x, int y) {
		super(c, size, x, y);
		this.c = Color.ORANGE;

	}

	@Override
	public void update(int x, int y) {
		super.update(((BasicGame) Core.getGameProgram()).getDisplay().getMouseBounds().getX(),
				((BasicGame) Core.getGameProgram()).getDisplay().getMouseBounds().getY());
	}

	@Override
	public void onCollision(Dot d) {
		if (d.getSize() > this.getSize()) {
			DotMain.playerDeath();
			this.kill();
		}
		if (this.getSize() > d.getSize()) {
			d.kill();
			score++;
			this.size++;
		}
	}

}
