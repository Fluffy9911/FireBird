package main.fire.runtime;

import java.io.IOException;
import java.util.Scanner;

import main.fire.cache.CacheObject;
import main.fire.core.debug.Debug;
import main.fire.file.ConfigFile;
import main.fire.file.SaveFile;
import main.fire.game.Program;
import main.fire.rendering.SimpleDisplay;
import main.fire.resources.FileLocation;
import main.utils.Extensions;

public class TestStarter {

	public static void main(String[] args) throws IOException {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Start in debug mode? Y/N");
			boolean start = false;
			while (!start) {
				String next = "";
				if (sc.hasNext())
					next = sc.nextLine();
				if (next.equals("Y")) {
					System.out.println("Starting...");
					Debug.debug();
					start = true;

				}
				if (next.equals("N")) {
					System.out.println("Ok, Starting");
					start = true;
				}

			}
			sc.close();

			System.out.println("Initializing Core Test Start...");
			new SimpleDisplay(500, 500, "nuool", new Program("test") {

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
					this.addArrayedInfo("Hey", "hey", "hey");

				}

				public void addArrayedInfo(String... str) {
					System.out.println(str);
				}

				@Override
				public void loadAssets() {
					// TODO Auto-generated method stub

				}

				@Override
				public void programEnd() {
					// TODO Auto-generated method stub

				}

				@Override
				public String getProgramLocation() {
					// TODO Auto-generated method stub
					return null;
				}

			});
			if (!Startup.testStart()) {
				System.out.println("TestStart failed...");

			} else {
				System.out.println("Successfully Tested Core");
				ConfigFile f = new ConfigFile(
						new SaveFile(new FileLocation("testing/tests", "config_test", Extensions.JSON)));
				System.out.println("Readable string as: " + f.<String>readOrWriteField("red", "t"));
				System.exit(404);
			}
		} catch (Exception e) {
			System.out.println("umm");
			e.printStackTrace();
			// CrashReporter.dispatchCrash(e);
		}

	}

}
