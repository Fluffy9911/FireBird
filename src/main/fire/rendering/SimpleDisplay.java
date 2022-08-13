package main.fire.rendering;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import main.fire.cache.CacheObject;
import main.fire.cache.ICache;
import main.fire.core.debug.Debug;
import main.fire.exception.CacheException;
import main.fire.game.Program;
import main.fire.game.IO.Key;
import main.fire.game.IO.MouseManager;
import main.fire.game.IO.MouseScroll;
import main.fire.game.game.gameplay.SimpleBB;
import main.fire.util.IUpdateable;
import main.fire.util.MainTick;

public class SimpleDisplay implements ICache, IUpdateable, MainTick {

	JFrame frame;
	Canvas cv;
	BufferStrategy bs;
	Graphics gs;
	int width;
	int height;
	String name;
	Renderer render;
	boolean shown = false;
	MouseManager mouse;
	Point mousepos;
	SimpleBB mouseBounds;
	Program p;
	Key key;
	MouseScroll scroll;
	int ow, oh;
	float scale;

	public SimpleDisplay(int width, int height, String name, Program p) {
		this.width = width;
		this.height = height;
		ow = width;
		oh = height;
		this.name = name;
		this.p = p;
		mouse = new MouseManager();
		key = new Key(p.getKeyManager());
		scroll = new MouseScroll();
		this.create();

		this.subscribeToUpdater(this);
		this.createTicker(this);
		scale = width / height;
	}

	public void create() {
		cv = new Canvas();
		frame = new JFrame(name);
		render = new Renderer(this);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(true);
		mousepos = new Point(0, 0);
		cv.setSize(width, height);
		cv.setVisible(true);
		cv.addMouseListener(mouse);
		cv.addKeyListener(key);
		cv.addMouseWheelListener(scroll);
		mouseBounds = new SimpleBB(this, 0, 0, 0, 0);
		// mouseBounds.setShouldrender(true);

		frame.add(cv);
		cv.addHierarchyBoundsListener(new HierarchyBoundsListener() {

			@Override
			public void ancestorMoved(HierarchyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void ancestorResized(HierarchyEvent e) {
				Debug.printInfo("Resized Display, updating renderer", true);
				render.updateSize(e.getChanged().getSize());

			}

		});

	}

	@Override
	public void saveCache(CacheObject ob) {

		ob.add(name + "width", width);
		ob.add(name + "height", height);
	}

	@Override
	public void loadCache(CacheObject obj) {

		try {
			if (obj.getObj().get(name + "width") != null) {
				Debug.printInfo("found cached info loading...", true);
				width = obj.getInt(name + "width");
				frame.setSize(width, height);
			}
		} catch (CacheException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (obj.getObj().get(name + "height") != null) {
				Debug.printInfo("found cached info loading...", true);
				height = obj.getInt(name + "height");
				frame.setSize(width, height);
			}
		} catch (CacheException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		render.updateSize(new Dimension(width, height));
	}

	@Override
	public void update() {
		this.width = frame.getWidth();
		this.height = frame.getHeight();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	@Override
	public void tick() {

		if (shown) {
			if (frame.getMousePosition() != null)
				mousepos = frame.getMousePosition();

			if (frame.getMousePosition() != null)
				mouseBounds.updatePos(mousepos.x - 10, mousepos.y - 30, 10, 10);

			do {

				gs = cv.getBufferStrategy().getDrawGraphics();

				gs.drawImage(render.show(), 0, 0, width, height, null);
				bs.show();
				render.end();
				gs.dispose();
			} while (bs.contentsLost());
		}
	}

	public void showDisplay() {

		frame.setVisible(true);

		cv.createBufferStrategy(3);
		bs = cv.getBufferStrategy();
		shown = true;
	}

	public Renderer getRenderer() {
		return render;
	}

	public MouseManager getMouseManager() {
		return mouse;
	}

	public SimpleBB getMouseBounds() {
		return mouseBounds;
	}

	public JFrame getFrame() {
		return frame;
	}

	public Canvas getCanvas() {
		return cv;
	}

	public String getName() {
		return name;
	}

	public Renderer getRender() {
		return render;
	}

	public boolean isShown() {
		return shown;
	}

	public MouseManager getMouse() {
		return mouse;
	}

	public Point getMousepos() {
		return mousepos;
	}

	public Program getProgram() {
		return p;
	}

	public MouseScroll getScroll() {
		return scroll;
	}

	public void setAsRes() {
		this.width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		this.height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		frame.setSize(width, height);
	}
}
