package main.fire.runtime.util;

import java.math.RoundingMode;
import java.text.NumberFormat;

public class MathUtils {

	public static double[] createArray(int length) {

		double[] d = new double[length];
		for (int i = 0; i < d.length; i++) {
			for (double j = 1; j < 10; j++) {

				double p = j / 10;

				d[i] = i + p;
			}
		}
		return d;
	}

	// create a method that makes a sinewave
	public static double[] sinWave(double[] x, double freq, double amplitude) {

		double[] y = new double[x.length];
		for (int i = 0; i < x.length; i++) {
			y[i] = (Math.sin(x[i] * freq) * amplitude);
			y[i] = MathUtils.format(y[i]);
		}
		return y;
	}

	public static double format(double in) {
		NumberFormat fm = NumberFormat.getInstance();

		fm.setRoundingMode(RoundingMode.DOWN);
		fm.setMaximumFractionDigits(5);
		return Double.parseDouble(fm.format(in));

	}

	// create a method that makes a sawtooth wave
	public static double[] sawWave(double[] x, double freq) {
		double[] y = new double[x.length];
		for (int i = 0; i < x.length; i++) {
			y[i] = (2 * Math.PI * freq * x[i]) % (2 * Math.PI);
		}
		return y;
	}

	// create a method that makes a triangle wave
	public static double[] triWave(double[] x, double freq) {
		double[] y = new double[x.length];
		for (int i = 0; i < x.length; i++) {
			if ((2 * Math.PI * freq * x[i]) % (2 * Math.PI) < Math.PI) {
				y[i] = (2 * Math.PI * freq * x[i]) % (2 * Math.PI);
			} else {
				y[i] = (2 * Math.PI * freq * x[i]) % (2 * Math.PI) - 2 * Math.PI;
			}
		}
		return y;
	}

	// create a method that makes a square wave
	public static double[] sqWave(double[] x, double freq) {
		double[] y = new double[x.length];
		for (int i = 0; i < x.length; i++) {
			if ((2 * Math.PI * freq * x[i]) % (2 * Math.PI) < Math.PI) {
				y[i] = 1;
			} else {
				y[i] = -1;
			}
		}
		return y;
	}

	public static double[] smooth(float aggression, int length) {
		double[] arry = MathUtils.createArray(length);
		for (int i = 0; i < arry.length; i++) {
			arry[i] *= aggression;
		}

		return arry;
	}

}
