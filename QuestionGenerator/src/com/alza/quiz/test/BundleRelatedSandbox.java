package com.alza.quiz.test;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class BundleRelatedSandbox {
	static Locale loc;
	static ResourceBundle bundle;
	public static void main(String[] args) {
		loc = new Locale("in", "ID");
		bundle = ResourceBundle.getBundle("lang.langbundle", loc);
		Enumeration<String> a = bundle.getKeys();
		List<String> l = new ArrayList<String>();
		
		while (a.hasMoreElements()) {
			String string = (String) a.nextElement();
			if (string.startsWith("geom")) {
				System.out.println(string);
				l.add(string);
			}
		}
	}

}
