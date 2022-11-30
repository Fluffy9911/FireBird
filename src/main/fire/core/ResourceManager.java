package main.fire.core;

import java.net.URL;
import java.util.HashMap;

import main.fire.core.debug.Logger;
import main.fire.resources.FileLocation;
import main.utils.Extensions;

public class ResourceManager {
public static final HashMap<String,Resource> FIREBIRD_RESOURCES = new HashMap<String,Resource>();
private static final HashMap<String,URL> TO_SEARCH = new HashMap<String,URL>();
public static String LOGGING_LOCATION = "firebird/logs";
public static final Logger RESOURCE_LOGGER = new Logger("resource_manager_logger");



public static final void addResource(String identifier,URL location) {
	TO_SEARCH.put(identifier, location);   
	
	
	
}

public static final void printLogger(Logger l) {
	RESOURCE_LOGGER.log("Starting Out Print of logger: "+RESOURCE_LOGGER.encapsulate(l.getLogger_name()));
	if(l.printOut()) {
		FileLocation loc = new FileLocation(ResourceManager.LOGGING_LOCATION,l.getLogger_name(),Extensions.TXT);
		RESOURCE_LOGGER.log("Printing Status: Finished!");
		RESOURCE_LOGGER.log("Find Data at: "+loc.getAsFile().getAbsolutePath());
	}else {
		RESOURCE_LOGGER.error("Print out failed for logger: "+RESOURCE_LOGGER.encapsulate(l.getLogger_name()));
	}
	
}

}
