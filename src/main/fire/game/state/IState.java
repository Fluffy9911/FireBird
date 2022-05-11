package main.fire.game.state;

import java.awt.Graphics;

public interface IState {

	public void init();

	public void tick();

	public void render(Graphics g);

	public void stop();

	public boolean shouldRun();
}
