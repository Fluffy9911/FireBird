package main.games.dot;

import main.fire.runtime.Startup;

public class DotTest {

	public static void main(String[] args) {
		Startup.startGameInstance(new DotMain("dot-game", 600, 600));
	}

}
