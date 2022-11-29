package main.fire.file;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import main.fire.core.debug.CrashReporter;

public class ConfigFile {
	SaveFile file;

	Map<String, Object> values;

	public ConfigFile(SaveFile file) {
		this.file = file;
		values = new HashMap<>();
		try {
			if (file.parseContents() != null)
				values = file.parseContents();
		} catch (ParseException | IOException e) {
			e.printStackTrace();
			CrashReporter.dispatchCrash(e);
		}

	}

	void writeData(SaveFile f) {
		JSONObject o = new JSONObject();
		for (Map.Entry<String, Object> entry : values.entrySet()) {
			String key = entry.getKey();
			Object val = entry.getValue();
			o.put(key, val);
		}
		f.writeToFile(o);
		f.saveFile();
	}

	public SaveFile getSavedFile() {
		this.writeData(file);
		return file;
	}

	public void buildFile() {
		this.getSavedFile().saveFile();
	}

	public <T extends Object> ConfigFile addField(String name, T defaultValue) {
		if (values.get(name) != null) {
			return this;

		} else {
			values.put(name, defaultValue);
			return this;
		}

	}

	public <T extends Object> T readField(String key) {
		if (values.get(key) != null) {
			return (T) values.get(key);

		}
		return null;
	}

	public <T extends Object> T readOrWriteField(String key, T defaultValue) {
		if (values.get(key) != null) {
			return (T) values.get(key);

		} else {
			values.put(key, defaultValue);

		}
		this.writeData(file);
		return defaultValue;
	}
}
