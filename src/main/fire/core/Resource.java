package main.fire.core;

import java.io.File;

import main.fire.resources.FileLocation;

public class Resource {
FileLocation location;
String name;
ResourceType type;
public Resource(FileLocation location, ResourceType type) {
	super();
	this.location = location;
	this.type = type;
	this.name = location.getName();
}
public FileLocation getLocation() {
	return location;
}
public String getName() {
	return name;
}
public ResourceType getType() {
	return type;
}
public boolean fileExists() {
	return location.fileExists();
}
public File getAsFile() {
	return location.getAsFile();
}
public String getNamedFile() {
	return location.getNamedFile();
}




}
