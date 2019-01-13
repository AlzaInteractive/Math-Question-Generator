package com.alza.quiz.model.geom.plane;

import java.text.DecimalFormat;

public class Geom {
	public static final double PI = 3.14;
	public static final int STROKE_COLOR_PRIMARY=1;
	public static final int STROKE_COLOR_SECONDARY=2;
	public static final int STROKE_COLOR_TERTIARY=3;
	public static final int FILL_COLOR_PRIMARY=1;
	public static final int FILL_COLOR_SECONDARY=2;
	public static final int FILL_COLOR_TERTIARY=3;
	public static final int STROKE_LINE=1;
	public static final int STROKE_DASH=2;
	
	public static String formatMeasurement(double measure){
		DecimalFormat df = new DecimalFormat("0.##");
		return df.format(measure);
	}
}
