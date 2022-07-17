package main.fire.testing;

import java.awt.Graphics;

import main.fire.cache.CacheObject;
import main.fire.game.BasicGame;
import main.fire.game.ProgramHolder;
import main.fire.game.IO.KeyInput;
import main.fire.game.game.gameplay.BasicBB;
import main.fire.rendering.RenderingObject;
import main.module.fire.ModuleLoader;
import main.module.fire.pixelBuilder.PixelBuilder;
import main.module.fire.testing.ExampleModule;

public class TestProgram extends BasicGame {
	BasicBB bb;
	KeyInput test;
	ProgramHolder holder;

	public TestProgram(String name, int x, int y) {
		super(name, x, y);
		bb = new BasicBB(this.getDisplay(), 10, 10, 50, 50);
		holder = new ProgramHolder(name, this);
	}

	@Override
	public void addModulesEvent() {
		ModuleLoader.addModule(new ExampleModule());
		ModuleLoader.addModule(new PixelBuilder());
	}

	@Override
	public void saveCache(CacheObject obj) {

	}

	@Override
	public void loadCache(CacheObject m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void start() {

		bb.setShouldrender(true);
		new RenderingObject(this.getDisplay().getRender()) {

			@Override
			public void render(Graphics g) {

				g.drawRect(5, 5, 50, 50);

			}

		};
	}

	@Override
	public void loadAssets() {
		this.addTexture("test2", "test");

	}

	@Override
	public void programEnd() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEnd() {

	}

	@Override
	public void tick() {

	}

	@Override
	public String getResourceBasePath() {
		// TODO Auto-generated method stub
		return "main/resources/firebird";
	}

}
