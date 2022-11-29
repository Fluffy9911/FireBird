package main.fire.game;

import java.awt.Graphics2D;
import java.util.concurrent.Executor;

import main.fire.core.Core;
import main.fire.game.assets.AssetLoader;

public abstract class Program {
	String identifier;
	protected Executor program_runner = Core.EXECUTOR;
	AssetLoader asset_loader;

	public abstract void init();

	public abstract boolean shouldStart();

	public abstract void render(Graphics2D g);

	public abstract void tick();

}
