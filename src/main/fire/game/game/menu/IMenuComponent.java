package main.fire.game.game.menu;

import java.awt.Graphics;

import main.fire.game.game.gameplay.SimpleBB;

public interface IMenuComponent {

	public int getXInMenu();

	public int getYInMenu();

	public Menu getMenuParent();

	public SimpleBB getBounds();

	public void tick();

	public void render(Graphics g);

}
