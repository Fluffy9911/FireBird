package main.fire.game.IO;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import main.fire.game.Program;

public class KeyManager {
	List<KeyInput> keys;

	Program p;

	public KeyManager(Program p) {

		this.p = p;
		this.keys = new ArrayList<>();
	}

	public void checkKeys(KeyEvent evt) {
		for (int i = 0; i < keys.size(); i++) {
			keys.get(i).check(evt);
		}
	}

	public void undown() {
		for (int i = 0; i < keys.size(); i++) {
			keys.get(i).undown();
		}
	}

	public KeyInput createKeyInput(int code) {
		return new KeyInput(this, code);
	}

	public List<KeyInput> getKeys() {
		return keys;
	}

}
