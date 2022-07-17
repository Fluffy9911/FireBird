package main.fire.util;

import main.fire.core.debug.Debug;

public class StatusMarker {

	Status s;
	String name;

	public StatusMarker(Status s, String name) {
		this.s = s;
		this.name = name;
	}

	public static StatusMarker create(String name) {
		var a = new StatusMarker(Status.NONE, name);
		a.s = Status.STARTING;
		Debug.status(a.s, "Starting: " + name, false);
		return a;
	}

	public void end() {
		s = Status.DONE;
		Debug.status(s, "Started: " + name + " at: " + Time.getDate(), false);
	}
}
