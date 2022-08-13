package main.fire.resources;

import java.io.File;
import java.io.IOException;

import main.utils.FileExtension;

public class FileLocation {
	private String location, name;
	private FileExtension extension;

	public FileLocation(String location, String name, FileExtension extension) {
		this.location = location;
		this.name = name;
		this.extension = extension;
		this.fixlocation();
		try {
			System.out.println(this.getAsFile().createNewFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void fixlocation() {
		if (!location.endsWith("/"))
			location += "/";

	}

	String buildPath() {
		return location + name + extension.getExtension();
	}

	public boolean fileExists() {
		return new File(this.buildPath()).exists();
	}

	public File getAsFile() {
		System.out.println(this.buildPath());
		return new File(this.buildPath());
	}

	public String getNamedFile() {
		return this.buildPath();
	}

}
