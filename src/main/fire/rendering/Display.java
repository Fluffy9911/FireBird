package main.fire.rendering;

import javax.swing.JFrame;

import main.fire.game.Program;

public abstract class Display extends SimpleDisplay {

	public Display(int width, int height, String name, Program p) {
		super(width, height, name, p);

	}

	@Override
	public void create() {

		super.create();
		addComponents(this.frame);
		this.frame.revalidate();
		this.frame.show();
	}

	public abstract void addComponents(JFrame frame);

}
