package main.fire.testing;

import main.fire.cache.CacheObject;
import main.fire.exception.CacheException;
import main.fire.game.BasicGame;
import main.fire.game.ProgramHolder;
import main.fire.game.IO.KeyInput;
import main.fire.game.game.gameplay.BasicBB;
import main.module.fire.ModuleLoader;
import main.module.fire.pixelBuilder.ImageToPixel;
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
		// TODO Auto-generated method stub

	}

	@Override
	public void loadCache(CacheObject m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void start() {

		bb.setShouldrender(true);
		try {
			ImageToPixel.createFile(this.getAssetLoader().getTexture("test2"), "test");
		} catch (CacheException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.holder.saveInfoToMainFile();
		} catch (CacheException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		if (bb.rightClicked()) {
			System.out.println("clicked");
		}
		if (bb.leftClicked()) {
			System.out.println("clicked_left");
		}
	}

	@Override
	public String getResourceBasePath() {
		// TODO Auto-generated method stub
		return "main/resources/firebird";
	}

}
