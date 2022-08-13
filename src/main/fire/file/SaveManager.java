package main.fire.file;

import java.util.Iterator;
import java.util.List;

import main.fire.anotations.End;
import main.fire.anotations.Marked;
import main.fire.util.Utils;

public class SaveManager {
	static List<SaveFile> toSave;

	@Marked
	public static void initLists() {
		toSave = Utils.<SaveFile>creatArrayList();

	}

	public static void addToSave(SaveFile file) {
		toSave.add(file);
	}

	@End
	public static void saveAll() {
		for (Iterator iterator = toSave.iterator(); iterator.hasNext();) {
			SaveFile f = (SaveFile) iterator.next();
			f.saveFile();
		}
	}

}
