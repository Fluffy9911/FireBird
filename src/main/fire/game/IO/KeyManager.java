package main.fire.game.IO;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class KeyManager {
	List<KeyInput> keys;

	public KeyManager() {

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
