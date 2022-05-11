package main.fire.core.debug;

public class MSCalc {
	long start, end;

	public MSCalc() {
		start = System.currentTimeMillis();
	}

	public void end() {
		end = System.currentTimeMillis() - start;

	}

	public long getStart() {
		return start;
	}

	public long getEnd() {
		return end;
	}

}
