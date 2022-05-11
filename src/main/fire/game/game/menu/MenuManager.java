package main.fire.game.game.menu;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import main.fire.game.Program;
import main.fire.rendering.IRender;
import main.fire.rendering.MultiRenderer;
import main.fire.rendering.SimpleDisplay;
import main.fire.runtime.IRun;

public class MenuManager {
	List<Menu> menus;
	SimpleDisplay display;
	Program program;

	public MenuManager(SimpleDisplay display, Program program) {
		this.display = display;
		this.program = program;
		this.menus = new ArrayList<>();
		init();
	}

	public void addMenu(Menu menu) {
		menus.add(menu);
	}

	public void init() {
		new MultiRenderer(display.getRender()).addRender(new IRender() {

			@Override
			public void render(Graphics g) {
				for (int i = 0; i < menus.size(); i++) {
					menus.get(i).renderMenuComponents(g);
				}

			}

		});

		program.getProgramThread().addRun(new IRun() {

			@Override
			public void run() {
				for (int i = 0; i < menus.size(); i++) {
					menus.get(i).tickMenuComponents();
				}
			}

		});
	}

}
