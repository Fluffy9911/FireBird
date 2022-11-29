package main.fire.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import main.fire.core.debug.Debug;
import main.fire.resources.FileLocation;

public class SaveFile {
	public JSONObject info;
	private File save;
	FileLocation location;

	public SaveFile(FileLocation location) {

		this.location = location;
		this.loadFile();
		// SaveManager.addToSave(this);
	}

	public boolean loadFile() {
		if (location.fileExists()) {
			save = location.getAsFile();
			return true;
		}
		try {
			location.getAsFile().createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void reloadFile() {

		this.loadFile();
		// Debug.printInfo("File :" + location.getNamedFile(), true);

	}

	public void writeToFile(JSONObject object) {
		this.info = object;

	}

	public void writeArray(JSONArray arry) {
		BufferedWriter writer;
		save = location.getAsFile();
		try {
			writer = new BufferedWriter(new FileWriter(save, false));
			arry.writeJSONString(writer);
			writer.close();
		} catch (IOException e) {
			Debug.debugError(getClass(), e);
		}
	}

	public String getFileContents() throws IOException {
		this.reloadFile();
		String text = "";
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
			inputStream = new FileInputStream(save);
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

	public Map<String, Object> parseContents() throws ParseException, IOException {
		JSONObject obj = new JSONObject();
		JSONParser parser = new JSONParser();
		if (!this.getFileContents().equals("")) {
			obj = (JSONObject) parser.parse(getFileContents());
			this.info = obj;
		}
		return obj;
	}

	public JSONArray parseArray() {
		JSONArray obj = new JSONArray();
		JSONParser parser = new JSONParser();

		try {
			obj = (JSONArray) parser.parse(getFileContents());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return obj;
	}

	public <T> T parseTypes(String key) throws ParseException, IOException {
		return (T) this.parseContents().get(key);

	}

	public void saveFile() {
		this.reloadFile();
		BufferedWriter writer;
		save = location.getAsFile();
		try {
			writer = new BufferedWriter(new FileWriter(save, false));
			info.writeJSONString(writer);
			writer.close();
		} catch (IOException e) {
			Debug.debugError(getClass(), e);
		}
	}

	/**
	 * @return the save
	 */
	public File getSavedFile() {
		return save;
	}

}
