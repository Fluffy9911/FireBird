package main.fire.util;

import main.fire.rendering.display.UpscaleCalculator;

public class Util {

	public static int calc(int calc) {
		return (int) (calc * UpscaleCalculator.UPSCALE);
	}

}
