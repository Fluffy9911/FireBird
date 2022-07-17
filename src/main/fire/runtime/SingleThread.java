package main.fire.runtime;

import main.fire.util.MainTick;

public class SingleThread {

	MainTick tick;

	public SingleThread(MainTick tick, String name) {

		this.tick = tick;
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					System.out.println("tt");
					tick.tick();
				}

			}

		});
		t.setName(name);
		t.start();
	}

}
