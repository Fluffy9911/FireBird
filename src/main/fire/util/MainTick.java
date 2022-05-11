package main.fire.util;

import main.fire.runtime.SingleThread;

public interface MainTick {
	public void tick();

	public default void createTicker(MainTick tk) {
		new SingleThread(tk, "ttk");
	}
}
