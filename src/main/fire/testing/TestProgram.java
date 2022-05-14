package main.fire.testing;

import main.fire.cache.CacheObject;
import main.fire.game.BasicGame;
import main.fire.game.IO.KeyInput;
import main.fire.game.game.gameplay.BasicBB;
import main.module.fire.ModuleLoader;
import main.module.fire.testing.ExampleModule;

public class TestProgram extends BasicGame {
	BasicBB bb;
	KeyInput test;

	public TestProgram(String name, int x, int y) {
		super(name, x, y);
		bb = new BasicBB(this.getDisplay(), 10, 10, 50, 50);
	}

	@Override
	public void addModulesEvent() {
		ModuleLoader.addModule(new ExampleModule());
	}

	@Override
	public void saveCache(CacheObject obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadCache(CacheObject m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void start() {

		bb.setShouldrender(true);
	}

	@Override
	public void loadAssets() {
		this.addTexture("test_pic", "test");

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
		if (bb.rightClicked()) {
			System.out.println("clicked");
		}
	}

	@Override
	public String getResourceBasePath() {
		// TODO Auto-generated method stub
		return "main/resources/firebird";
	}

}
