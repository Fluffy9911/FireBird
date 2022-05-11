package main.fire.game.game.menu;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public abstract class Menu {
	boolean render = false;
	int screenX, screenY;
	MenuManager manager;
	List<IMenuComponent> comps;

	public Menu(int screenX, int screenY, MenuManager manager) {
		this.screenX = screenX;
		this.screenY = screenY;
		this.manager = manager;
		comps = new ArrayList<>();
	}

	public abstract void tickMenuComponents();

	public abstract void renderMenuComponents(Graphics g);

	public boolean isRender() {
		return render;
	}

	public void setShouldRender(boolean render) {
		this.render = render;
	}

	public int getScreenX() {
		return screenX;
	}

	public int getScreenY() {
		return screenY;
	}

	public MenuManager getManager() {
		return manager;
	}

	public List<IMenuComponent> getComps() {
		return comps;
	}

}
