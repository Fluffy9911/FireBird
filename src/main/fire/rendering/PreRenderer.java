package main.fire.rendering;

import java.awt.image.BufferedImage;

public abstract class PreRenderer {

	public PreRenderer(Renderer r) {
		r.getPres().add(this);
	}

	public abstract BufferedImage render(BufferedImage frame);
}