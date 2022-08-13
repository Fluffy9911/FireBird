package main.fire.rendering;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.fire.game.game.gameplay.SimpleBB;

public class ImageRenderer {
	Renderer renderer;
	BufferedImage image;
	int x, y, width, height;
	SimpleBB bounds;

	public ImageRenderer(Renderer renderer, BufferedImage image, int x, int y, int width, int height) {
		this.renderer = renderer;
		this.image = image;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.bounds = new SimpleBB(renderer.getDisplay(), x, y, width, height);
		draw();
	}

	public void updatePosition(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.bounds.updatePos(x, y, width, height);
	}

	void renderBounds() {
		this.bounds.setShouldrender(true);
	}

	void hideBounds() {
		this.bounds.setShouldrender(false);
	}

	private void draw() {
		new RenderingObject(renderer) {

			@Override
			public void render(Graphics g) {
				g.drawImage(image, x, y, width, height, null);

			}

		};
	}
}
