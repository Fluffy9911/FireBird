package main.fire.game.animation;

import main.fire.core.debug.CrashReporter;

public abstract class PositionAnimation<N extends Number> {
	N pos, start;
	Thread animationThread;
	long tpa;
	Animator<N> an;

	public PositionAnimation(N start) {
		this.start = start;
		pos = start;
		an = new Animator<>(this);
		animationThread = new Thread(an);

	}

	public N getPosition() {
		return pos;
	}

	public abstract N getNextPos(N number);

	public long getTpa() {
		return tpa;
	}

	@Override
	public String toString() {
		return "PositionAnimation [pos=" + pos + ", start=" + start + ", animationThread=" + animationThread + ", tpa="
				+ tpa + "]";
	}

	public void startAnimation(long tpa) {
		this.tpa = tpa;
		this.animationThread.start();
	}

	public static class Animator<N extends Number> implements Runnable {
		PositionAnimation<N> p;

		public Animator(PositionAnimation<N> p) {
			this.p = p;
		}

		@Override
		public void run() {
			while (p.animationThread.isAlive()) {
				try {
					System.out.println(p.getPosition());
					p.pos = p.getNextPos(p.getPosition());
					Thread.sleep(p.getTpa());

				} catch (Exception e) {
					CrashReporter.dispatchCrash(p.toString(), e);
				}
			}
		}

	}

}
