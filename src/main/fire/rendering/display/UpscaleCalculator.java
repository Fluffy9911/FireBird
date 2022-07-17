package main.fire.rendering.display;

import main.fire.core.debug.Debug;

public class UpscaleCalculator {
	final static int width = 1920;
	final static int height = 1080;
	private static int screenW, screenH;
	public static float UPSCALE;

	public static void calculateUpscale(int w, int h) {
		screenW = w;
		screenH = h;
		final float upscale = w / h;
		UPSCALE = upscale;
		Debug.printInfo("Required Upscaling for display is:" + upscale, true);
	}

}
