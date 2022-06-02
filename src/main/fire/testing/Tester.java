package main.fire.testing;

import main.fire.core.CoreIO;

public class Tester {

	public static void main(String[] args) {
		CoreIO io = new CoreIO();
		io.startProgram(new TestProgram("fb", 400, 400));
	}

}
