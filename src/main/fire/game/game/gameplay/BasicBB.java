package main.fire.game.game.gameplay;

import java.io.Serializable;

import main.fire.rendering.SimpleDisplay;

public class BasicBB extends SimpleBB implements Serializable {
	SimpleDisplay display;

	public BasicBB(SimpleDisplay ds, int x, int y, int width, int height) {
		super(ds, x, y, width, height);
		this.display = ds;
	}

	public boolean mouseOver() {
		return display.getMouseBounds().getBounds().intersects(this.getBounds());
	}

	public boolean intersects(SimpleBB bb) {
		return bb.getBounds().intersects(this.getBounds());
	}

	public boolean leftClicked() {
		return (this.mouseOver() && display.getMouseManager().isLeft());
	}

	public boolean rightClicked() {

		return (this.mouseOver() && display.getMouseManager().isRight());
	}

	public int scrolledDown() {
		if (mouseOver() && display.getScroll().isUp())
			return display.getScroll().getNotches();
		return 0;
	}

	public int scrolledUp() {
		if (mouseOver() && display.getScroll().isDown())
			return display.getScroll().getNotches();
		return 0;
	}
}
