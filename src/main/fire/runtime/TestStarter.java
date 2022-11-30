package main.fire.runtime;

import java.io.IOException;
import java.util.Scanner;

import main.fire.cache.CacheObject;
import main.fire.core.Core;
import main.fire.core.ResourceManager;
import main.fire.core.debug.Debug;
import main.fire.core.debug.command.CommandManager;
import main.fire.file.ConfigFile;
import main.fire.file.SaveFile;
import main.fire.game.Program;
import main.fire.rendering.SimpleDisplay;
import main.fire.resources.FileLocation;
import main.utils.Extensions;

public class TestStarter {

	public static void main(String[] args) throws IOException {
		try {
			Scanner sc = Core.scanner;
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
			

			System.out.println("Initializing Core Test Start...");
			
			if (!Startup.testStart()) {
				System.out.println("TestStart failed...");

			} else {
				CommandManager.registerCommands();
				Core.EXECUTOR.execute(()-> {
					CommandManager.parse(sc);
				});
				ResourceManager.RESOURCE_LOGGER.log("heeeyyy");
				System.out.println("Successfully Tested Core");
				ConfigFile f = new ConfigFile(
						new SaveFile(new FileLocation("testing/tests", "config_test", Extensions.JSON)));
				System.out.println("Readable string as: " + f.<String>readOrWriteField("red", "t"));
				
			}
		} catch (Exception e) {
			System.out.println("umm");
			e.printStackTrace();
			// CrashReporter.dispatchCrash(e);
		}

	}

}
