package main.fire.game.animation;

import main.fire.runtime.util.MathUtils;

public class IntegerSineAnimation extends IntegerAnimation {
	double height, phase;

	public IntegerSineAnimation(Integer start, double height, double phase) {
		super(start);
		this.height = height;
		this.phase = phase;
	}

	@Override
	public Integer getNextPos(Integer number) {
		double[] sin = MathUtils.sinWave(MathUtils.createArray(7), 1, 2);
		number++;
		if (number == 7) {
			number = 0;
		}
		return (int) sin[number];
	}

}
