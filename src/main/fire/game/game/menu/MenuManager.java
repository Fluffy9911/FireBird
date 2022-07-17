package main.fire.game.game.menu;

import java.util.ArrayList;
import java.util.List;

import main.fire.game.Program;
import main.fire.rendering.SimpleDisplay;

public class MenuManager {
	List<Menu> menus;
	SimpleDisplay display;
	Program program;

	public MenuManager(SimpleDisplay display, Program program) {
		this.display = display;
		this.program = program;
		this.menus = new ArrayList<>();

	}

	public void addMenu(Menu menu) {
		menus.add(menu);
	}

}
