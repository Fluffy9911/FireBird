package main.fire.rendering;

import java.awt.Graphics;

public abstract class RenderingObject {
	Renderer r;

	public RenderingObject(Renderer r) {
		this.r = r;
		add();
	}

	public synchronized void add() {
		r.getToRender().add(this);
	}

	public abstract void render(Graphics g);

}
