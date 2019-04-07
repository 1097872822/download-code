package com.wsy.struts.util;

public class umlChange {
	public String Change(String str){
		String s=str.replaceAll("\\[\\_\\[", "<").replaceAll("\\]\\_\\]", ">").replaceAll("\\|\\_\\|", " ");
		//System.out.println(str.replaceAll("\\[\\_\\[", "<"));
		return s.trim();
	}
	public static void main(String args[]){
		umlChange uml=new umlChange();
		//uml.Change("[_[html]_][_[/html]_]");
		String s=uml.Change("[_[font|_|color=]_]");
		//System.out.println(s);
		
	}
}
