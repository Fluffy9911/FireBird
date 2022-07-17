package main.fire.runtime;

import java.util.ArrayList;
import java.util.List;

import main.fire.game.game.menu.Menu;

public class MenuHandler {
	public static List<Menu> menus;

	public static void init() {
		menus = new ArrayList<>();
	}

	public static void registerMenu(Menu m) {
		// Debug.printInfo("Registered menu: " + m.getName());
	}

}
