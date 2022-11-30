package main.fire.core.debug.command;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import main.fire.core.Core;
import main.fire.core.CoreInfo;
import main.fire.core.ResourceManager;
import main.fire.core.debug.Logger;

public class CommandManager {
private static final HashMap<String,Command> commands = new HashMap<String,Command>();
public static boolean run = true;
public static Logger command_logger = new Logger("command_manager");
public static void putCommand(String key, Command value) {
	
	 commands.put(key, value);
}
	public static void registerCommands() {
		putCommand("DBG-PRINT_RESOURCE", (s)->{
			ResourceManager.RESOURCE_LOGGER.log("Ok, Printing out to location: "+ResourceManager.LOGGING_LOCATION);
			ResourceManager.RESOURCE_LOGGER.printOut();
		});
		putCommand("HELP", (s)->{
			command_logger.log("Possible Commands:");
			for (Map.Entry<String, Command> entry : commands.entrySet()) {
				String key = entry.getKey();
				Command val = entry.getValue();
				command_logger.log(key);
			}
		});
		putCommand("KILL", (s)->{
			command_logger.log("ok killing...");
			System.exit(55);
		});
		putCommand("LOGGING-LOCATION", (s)->{
			command_logger.log(ResourceManager.LOGGING_LOCATION);
			
		});
		putCommand("$DBG-CHANGE_LOC", (s)->{
			while(!Core.scanner.hasNext()) {
				
			}
			String r = Core.scanner.next();
			if(r.contains("\"")) {
				String t = StringUtils.substringBetween(r, "\"");
				ResourceManager.LOGGING_LOCATION = t;
				ResourceManager.RESOURCE_LOGGER.log("Changed Location to: "+t);
			}
		});
		putCommand("DBG", (s)->{
			while(!Core.scanner.hasNext()) {
				
			}
			String r = Core.scanner.next();
			if(r.equals("true")) {
				CoreInfo.PRINT_INFO = true;
				command_logger.log("Changed debug mode to: True");
			}else if(r.equals("false")){
				CoreInfo.PRINT_INFO = false;
				command_logger.log("Changed debug mode to: False");
			}
			if(!(r.equals("true")||r.equals("false")))
			command_logger.log("Error, input must be true/false");
		});
	}
	public static void parse(Scanner sc) {
		
		
		while(run) {
			
			
			String next = "";
			if(sc.hasNext()) {
					next = sc.nextLine();
				for (Map.Entry<String, Command> entry : commands.entrySet()) {
					String key = entry.getKey();
					Command val = entry.getValue();
					
					
					if(next.equals(key)) {
						val.run(key);
					}
				}
			}
			
		}
		sc.close();
	}
	
}
