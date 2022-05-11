package main.fire.game.game;

import java.awt.image.BufferedImage;

import main.fire.core.debug.Debug;

public class SpriteAnimation {
	ImageProvider images;
	BufferedImage frame;

	public SpriteAnimation(ImageProvider images) {
		this.images = images;
		frame = images.getImages().get(0);
	}

	public BufferedImage animate(int loop, int betweenMS) {

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {

				int i = 0;
				if (i > loop) {
					for (BufferedImage img : images.getImages()) {
						i++;
						frame = img;
						try {
							Thread.sleep(betweenMS);
						} catch (InterruptedException e) {

							Debug.debugError(getClass(), e);
							Debug.error("Animation error");
						}

					}
				}
			}

		});
		t.run();
		return frame;
	}

	public BufferedImage animate(int betweenMS) {

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (BufferedImage img : images.getImages()) {

					frame = img;
					try {
						Thread.sleep(betweenMS);
					} catch (InterruptedException e) {

						Debug.debugError(getClass(), e);
						Debug.error("Animation error");
					}

				}
			}
		});
		t.run();
		return frame;
	}
}
