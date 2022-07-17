package main.fire.game;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.Map;

import main.fire.game.assets.AssetLoader;
import main.fire.game.game.menu.MenuManager;
import main.fire.game.saving.GameSaveingManager;
import main.fire.game.state.StateManager;
import main.fire.rendering.Renderer;
import main.fire.rendering.SimpleDisplay;
import main.fire.runtime.IRun;
import main.fire.util.PathMaker;

public abstract class BasicGame extends Program {
	GameSaveingManager manager;
	AssetLoader assets;
	String gameResources = "";
	MenuManager menuManager;
	StateManager stateManager;
	Dimension res = Toolkit.getDefaultToolkit().getScreenSize();

	public BasicGame(String name, int x, int y) {
		super(name);

		assets = new AssetLoader(this);
		gameResources = this.getResourceBasePath();
		stateManager = new StateManager(this);
		assets.addBasePath(gameResources);
		this.loadCache(this.getCacheForProgram().getTwo());

		this.addDisplay(x, y);
		// this.getDisplay().setAsRes();
		this.getDisplay().loadCache(this.getCacheForProgram().getTwo());
		manager = new GameSaveingManager(this);
		menuManager = new MenuManager(this.getDisplay(), this);
		this.preDisplayShown();
		this.getDisplay().showDisplay();

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

			@Override
			public void run() {
				getDisplay().saveCache(getCacheForProgram().getTwo());
				getCacheForProgram().getOne().writeCacheToFile(getCacheForProgram().getTwo());

				onEnd();
			}

		})

		);
		this.getProgramThread().addRun(new IRun() {

			@Override
			public void run() {
				getDisplay().tick();
				tick();
			}

		});
	}

	private void createGameResourcesPath() {
		PathMaker.makePath(gameResources);
	}

	public SimpleDisplay getDisplay() {
		return this.display;
	}

	public Renderer getGameRenderer() {
		return this.getDisplay().getRenderer();
	}

	public AssetLoader getAssetLoader() {
		return assets;
	}

	public void addTexture(String key, String path) {
		this.getAssetLoader().addTexture(key, path);
	}

	public Map<String, BufferedImage> getAllTextures() {
		return this.getAssetLoader().getLoaded();
	}

	public abstract void onEnd();

	public abstract void tick();

	public void preDisplayShown() {
		this.loadAssets();
		this.getAssetLoader().loadAsset();
	}

	public abstract String getResourceBasePath();

	public GameSaveingManager getManager() {
		return manager;
	}

	public String getGameResources() {
		return gameResources;
	}

	public MenuManager getMenuManager() {
		return menuManager;
	}

	public StateManager getStateManager() {
		return stateManager;
	}

}
