package main.fire.util;

import main.fire.core.Core;
import main.fire.runtime.IRun;

public interface MainTick {
	public void tick();

	public default void createTicker(MainTick tk) {

		Core.getGameProgram().getProgramThread().addRun(new IRun() {

			@Override
			public void run() {

				tk.tick();

			}

		});
	}
}
