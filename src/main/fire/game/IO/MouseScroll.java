package main.fire.game.IO;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseScroll implements MouseWheelListener {
	boolean up = false, down = true;
	int notches = 0;

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int notches = e.getWheelRotation();
		this.notches = notches;
		if (notches < 0) {
			// up
			up = true;
			down = false;
		} else {
			// down
			up = false;
			down = true;
		}

	}

	public boolean isUp() {
		return up;
	}

	public boolean isDown() {
		return down;
	}

	/**
	 * Negative is UP Positive is DOWN
	 * 
	 * @return
	 */
	public int getNotches() {
		return notches;
	}

}
