package com.dc.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegex {

	public static void main(String[] args) {
    	Pattern pattern = Pattern.compile("/cloudui/ws/[\\w-]+");
    	String str = "/cloudui/ws/123-234-345";
    	Matcher matcher = pattern.matcher(str);
    	if (matcher.find()) {
			System.out.println(matcher.group());
		}
    }
	
}
