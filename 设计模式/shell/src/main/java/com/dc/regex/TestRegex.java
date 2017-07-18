package com.dc.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegex {

	public static void main(String[] args) {
    	Pattern pattern = Pattern.compile("\\w*/\\w*/(.*)(.java)");
    	String str = "Adapter/src/com/dc/appengine/adapter/utils/UnitedStackClient.java";
    	Matcher matcher = pattern.matcher(str);
    	if (matcher.find()) {
			System.out.println(matcher.group());
			System.out.println(matcher.group(1));
		}
    }
	
}
