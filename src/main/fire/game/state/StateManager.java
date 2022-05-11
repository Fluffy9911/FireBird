package main.fire.game.state;

import java.util.HashMap;
import java.util.Map;

import main.fire.game.BasicGame;

public class StateManager {
	BasicGame game;
	Map<String, IState> states;
	StateRunner runner;

	public StateManager(BasicGame game) {
		this.game = game;
		this.states = new HashMap<>();
	}

	public void startState(String key) {
		stopAllStates();
		runner = new StateRunner(states.get(key), game.getGameRenderer());
		IState s = states.get(key);
		s.init();
		game.getProgramThread().addRun(runner);
	}

	public void stopAllStates() {
		for (Map.Entry<String, IState> entry : states.entrySet()) {
			game.getProgramThread().getToRun().remove(runner);
			entry.getValue().stop();

		}
	}

	public BasicGame getGame() {
		return game;
	}

	public Map<String, IState> getStates() {
		return states;
	}

	public StateRunner getRunner() {
		return runner;
	}

}
