package com.alza.quiz.util;

import java.util.List;

public class StringUtils {
	/**
	 * Convert list of string to delimited form
	 * @param l, list of string
	 * @param delim, delimiter
	 * @return 
	 * ref http://stackoverflow.com/questions/6244823/convert-liststring-to-delimited-string
	 */
	public static String join(List<?> l, String delim){
		StringBuilder sb = new StringBuilder();
		for(Object s: l) {
		   sb.append(String.valueOf(s)).append(delim);
		}
		sb.deleteCharAt(sb.length()-1); //delete last comma
		return sb.toString();
	}

}
