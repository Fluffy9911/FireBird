package main.fire.rendering;

public abstract class BooleanRenderer extends RenderingObject {

	public BooleanRenderer(Renderer r) {
		super(r);

	}

	@Override
	public void add() {

	}

	public void tickRender(boolean render) {
		if (render)
			this.render(this.r.getGraphics());
	}

}
