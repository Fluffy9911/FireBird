package main.fire.cache;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import main.fire.core.Core;
import main.fire.core.debug.Debug;
import main.fire.core.debug.MSCalc;
import main.fire.exception.CacheException;

public class CacheManager {
	File f;
	URL loc;
	String location = "";
	String text = "";

	public void createCoreFileForCaching(String name) throws CacheException {

		createCacheFile(name, Core.CACHE_LOCATION);

	}

	public void createCacheFile(String name, String path) throws CacheException {
		if (name == null || path == null)
			throw new CacheException();

		File g = new File(path);
		f = new File(path + "/" + name + ".fbc");

		if (!f.exists()) {
			g.mkdirs();
			try {
				f.createNewFile();
				location = f.getPath();
			} catch (IOException e) {

				Debug.debugError(getClass(), e);
			}
		} else {
			Debug.printInfo("File Already Exists: " + f.getName());
		}
		loc = this.getClass().getResource(path + "/" + name + ".fbc");
	}

	public void setCacheFile(File f) {
		this.f = f;
	}

	public String readFromFile() throws IOException, CacheException {
		if (!f.exists()) {
			System.out.println("doesn't exist");
			throw new CacheException();
		}
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
			inputStream = new FileInputStream(f);
			sc = new Scanner(inputStream, "UTF-8");
			while (sc.hasNextLine()) {
				text += sc.nextLine();

			}

			if (sc.ioException() != null) {
				throw sc.ioException();
			}
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (sc != null) {
				sc.close();
			}
		}
		return text;
	}

	public void populateCacheObject(CacheObject cache) {
		MSCalc c = new MSCalc();
		JSONObject obj = this.readJson();
		c.end();
		Debug.printInfo("Took:" + c.getEnd() + " ms to read from file: " + f.getName());
		Debug.printInfo("starting map transfer...");

		try {
			cache.setObj(obj);
		} catch (CacheException e) {
			Debug.debugError(getClass(), e);
			Debug.error("Error transfering map...");
			e.printStackTrace();
		}

	}

	public synchronized JSONObject readJson() {
		JSONObject obj = new JSONObject();
		JSONParser parser = new JSONParser();

		InputStreamReader reader;

		try {
			if (this.readFromFile() != "null" || this.readFromFile() != "") {

				reader = new InputStreamReader(new FileInputStream(f));
				parser.reset(reader);
				obj = (JSONObject) parser.parse(reader);

				reader.close();

			}
		} catch (IOException | ParseException | CacheException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return obj;
	}

	public void writeCacheToFile(CacheObject obj) {
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(f, false));
			if (obj.getObj() != null) {
				writer.write(obj.getObj().toJSONString());
			}
			writer.close();
		} catch (IOException | CacheException e) {
			Debug.debugError(getClass(), e);
		}

	}

}