package main.fire.runtime;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

import main.fire.anotations.End;
import main.fire.anotations.Marked;
import main.fire.core.Core;
import main.fire.core.debug.Debug;
import main.fire.game.Program;
import main.fire.game.assets.AssetLoader;

public class Startup {
	AssetLoader loader;

	MenuHandler handler;

	public static void startRuntime() {
		Reflections find = new Reflections("main.fire", new MethodAnnotationsScanner());
		Set<Method> toCall = find.getMethodsAnnotatedWith(Marked.class);

		for (Iterator iterator = toCall.iterator(); iterator.hasNext();) {
			Method method = (Method) iterator.next();
			try {

				method.invoke(null, null);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {

				Reflections find = new Reflections("main.fire", new MethodAnnotationsScanner());
				Set<Method> toCall = find.getMethodsAnnotatedWith(End.class);

				for (Iterator iterator = toCall.iterator(); iterator.hasNext();) {
					Method method = (Method) iterator.next();
					try {

						method.invoke(null, null);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		});
		Core.initCore();
	}

	public static boolean testStart() {
		try {
			startRuntime();
		} catch (Exception e) {
			Debug.debugError(Startup.class, e);
			return false;
		}
		return true;
	}

	public static Program startGameInstance(Program game) {
		game.getProgramThread().startThread();
		game.start();

		return game;
	}

}
