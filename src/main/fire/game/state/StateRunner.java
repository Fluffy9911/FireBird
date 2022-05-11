package main.fire.game.state;

import java.awt.Graphics;

import main.fire.rendering.IRender;
import main.fire.rendering.MultiRenderer;
import main.fire.rendering.Renderer;
import main.fire.runtime.IRun;

public class StateRunner implements IRun {
	IState state;
	Renderer render;

	public StateRunner(IState state, Renderer render) {
		this.state = state;
		this.render = render;
		MultiRenderer r = new MultiRenderer(render);
		r.addRender(new IRender() {

			@Override
			public void render(Graphics g) {
				if (state.shouldRun())
					state.render(g);

			}

		});
	}

	@Override
	public void run() {
		if (state.shouldRun()) {
			state.tick();
		}

	}

}
