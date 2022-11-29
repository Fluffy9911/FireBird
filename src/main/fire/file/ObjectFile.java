package main.fire.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import main.fire.resources.FileLocation;
import main.utils.FileExtension;

public class ObjectFile<T extends Object> {
	T t;
	File out;
	FileLocation location;
	public static final FileExtension SERIAL = FileExtension.create(".fbsrb");

	public ObjectFile(T t, FileLocation location) {

		this.t = t;
		this.location = location;
		out = location.getAsFile();
	}

	public T getObject() throws ClassNotFoundException, IOException {
		FileInputStream f = new FileInputStream(out);
		ObjectInputStream in = new ObjectInputStream(f);
		@SuppressWarnings("unchecked")
		T o = (T) in.readObject();
		in.close();
		f.close();
		t = o;
		return o;
	}

	public static <T extends Object> T getObject(File out) throws ClassNotFoundException, IOException {
		FileInputStream f = new FileInputStream(out);
		ObjectInputStream in = new ObjectInputStream(f);
		@SuppressWarnings("unchecked")
		T o = (T) in.readObject();
		in.close();
		f.close();

		return o;
	}

	public void writeOut() {
		System.out.println("writing");
		try {
			FileOutputStream fileOut = new FileOutputStream(out);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			if (t != null)
				out.writeObject(t);
			out.close();
			fileOut.close();

		} catch (IOException i) {
			i.printStackTrace();
		}
	}

}
