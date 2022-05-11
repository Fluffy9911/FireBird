package main.fire.util;

import main.fire.core.Core;

public interface IUpdateable {
	public void update();

	public default void subscribeToUpdater(IUpdateable iu) {
		Core.updater.add(iu);
	}

}
