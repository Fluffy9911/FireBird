package main.fire.game.animation;

public class IntegerAnimation extends PositionAnimation<Integer> {

	public IntegerAnimation(Integer start) {
		super(start);

	}

	@Override
	public Integer getNextPos(Integer number) {

		return number + 1;
	}

}
