package main.fire.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;

public class Time {
	public static String getDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String t = dtf.format(now);
		String s = StringUtils.deleteWhitespace(t);
		return s;
	}

	public static String fileDate() {
		String srt = getDate().replaceAll("/", "_");
		String t = srt.replaceAll(":", "_");
		return t;
	}
}
