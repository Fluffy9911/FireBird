package main.fire.game.IO;

import java.awt.event.KeyEvent;

public class KeyInput {
	KeyManager ma;
	int code;
	boolean down = false;

	protected KeyInput(KeyManager ma, int code) {
		this.ma = ma;
		this.code = code;
		ma.getKeys().add(this);
	}

	public void check(KeyEvent event) {
		if (event.getKeyCode() == code) {

			down = true;
		} else {
			down = false;
		}
	}

	public KeyManager getMa() {
		return ma;
	}

	public void undown() {
		down = false;
	}

	public boolean isDown() {
		return down;
	}

}
