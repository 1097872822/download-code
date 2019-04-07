package com.mwq.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {

	public static boolean execute(String rule, String content) {
		Pattern pattern = Pattern.compile(rule);
		Matcher matcher = pattern.matcher(content);
		return matcher.matches();
	}

	public static void main(String[] args) {
		System.out.println(Validate.execute("[1-9]{1}([0-9]{1})", "1"));
	}

}
