package main.fire.resources;

import java.io.File;

import main.fire.util.PathMaker;
import main.utils.FileExtension;

public class FileLocation {
	private String location, name;
	private FileExtension extension;

	public FileLocation(String location, String name, FileExtension extension) {
		PathMaker.makePath(location);
		this.location = location;
		this.name = name;
		this.extension = extension;
		this.fixlocation();

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

		return new File(this.buildPath());
	}

	public String getNamedFile() {
		return this.buildPath();
	}

}
