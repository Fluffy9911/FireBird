package main.fire.game.state;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import main.fire.anotations.End;
import main.fire.anotations.Marked;

public class StateManager {
	public static Map<String, State> states;
	public static State running;

	@Marked
	public static void init() {
		states = new HashMap<>();
	}

	public static void registerState(String name, State state) {
		states.put(name, state);
	}

	public static void startState(String name) {
		for (Map.Entry<String, State> entry : states.entrySet()) {
			String key = entry.getKey();
			State val = entry.getValue();
			val.stop();
		}
		states.get(name).start();
		running = states.get(name);
	}

	public static void tick() {
		if (running != null)
			if (running.isOn())
				running.tick();

	}

	public static void render(Graphics g) {
		if (running != null)
			if (running.isOn())
				running.render(g);

	}

	@End
	public static void stopStates() {
		for (Map.Entry<String, State> entry : states.entrySet()) {
			String key = entry.getKey();
			State val = entry.getValue();
			val.stop();
		}
		running = null;
	}

	public static State getRunning() {
		return running;
	}

}
