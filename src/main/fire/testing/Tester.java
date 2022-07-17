package main.fire.testing;

import main.fire.runtime.Startup;

public class Tester {

	public static void main(String[] args) {
		Startup.startGameInstance(new TestProgram("test", 100, 100));
	}

}
