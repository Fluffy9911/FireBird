package main.fire.rendering;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class MultiRenderer extends RenderingObject {
	List<IRender> renders;

	public MultiRenderer(Renderer r) {
		super(r);
		renders = new ArrayList<>();
	}

	public MultiRenderer addRender(IRender r) {
		renders.add(r);
		return this;
	}

	@Override
	public void render(Graphics g) {
		for (int i = 0; i < renders.size(); i++) {
			renders.get(i).render(g);
		}
	}

}
