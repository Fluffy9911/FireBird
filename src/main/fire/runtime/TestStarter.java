package main.fire.runtime;

import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONObject;

import main.fire.core.debug.Debug;
import main.fire.file.SaveFile;
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
			SaveFile file = new SaveFile(new FileLocation("firebird/data/test", "test_file", Extensions.JSON));
			var a = new JSONObject();
			a.put("test1", "hey");
			file.writeToFile(a);
			file.saveFile();
			file.loadFile();
			System.out.println(file.getFileContents());
			if (!Startup.testStart()) {
				System.out.println("TestStart failed...");
			} else {
				System.out.println("Successfully Tested Core");
				System.exit(404);
			}
		} catch (Exception e) {

		}

	}

}
