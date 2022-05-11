package main.fire.game.IO;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Key implements KeyListener {
	KeyManager mng;

	public Key(KeyManager mng) {
		this.mng = mng;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		mng.checkKeys(e);

	}

	@Override
	public void keyReleased(KeyEvent e) {
		mng.undown();

	}

}
