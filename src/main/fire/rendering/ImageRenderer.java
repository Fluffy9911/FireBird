package main.fire.rendering;

import java.awt.Graphics;
import java.awt.image.BufferedImage;



public class ImageRenderer {
	Renderer renderer;
	BufferedImage image;
	int x, y, width, height;
	

	public ImageRenderer(Renderer renderer, BufferedImage image, int x, int y, int width, int height) {
		this.renderer = renderer;
		this.image = image;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		draw();
	}

	public void updatePosition(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
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
