package main.fire.core.debug;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import main.fire.core.ResourceManager;
import main.fire.resources.FileLocation;
import main.fire.util.Time;
import main.utils.Extensions;

public class Logger {
private ArrayList<String> data = new ArrayList<String>();
private String logger_name;

public Logger(String logger_name) {
	super();
	this.logger_name = logger_name;
}

public String encapsulate(String str) {
	return "["+str+"]";
}

public void log(String str) {
	System.out.println(this.encapsulate("logger: "+logger_name+" : "+str));
	data.add(this.encapsulate("logger: "+logger_name+" : "+str));
}
public void error(String str) {
	System.err.println(this.encapsulate("Error logger: "+logger_name+" : "+str));
	data.add(this.encapsulate("logger: "+logger_name+" : "+str));
}

public boolean printOut() {
	try {
		FileLocation loc = new FileLocation(ResourceManager.LOGGING_LOCATION,logger_name,Extensions.TXT);
		FileOutputStream stream = new FileOutputStream(loc.getAsFile());
		String dat = "";
		for (Iterator iterator = data.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			
		
			
			
			dat += string + Time.fileDate()+" ";
		}
		stream.write(dat.getBytes());
		stream.close();
		return true;
		
	}catch(Exception e) {
		
		return false;
	}
	
}

public String getLogger_name() {
	return logger_name;
}

}
