package com.alza.quiz.model.geom;

public class Stroke {
	public int type;//refer to Geom ColorSecondary
	public int color;//refer to Geom ColorPrimary
	/**
	 * Create a default stroke i,e : line stroke type with primary color
	 */
	public Stroke() {
		this.type = Geom.STROKE_LINE;
		this.color = Geom.STROKE_COLOR_PRIMARY;
	}
	/**
	 * @param type, line, dash ...refer to Geom stroke constants
	 */
	public Stroke(int type) {
		super();
		this.type = type;
	}
	/**
	 * @param type, line, dash ...refer to Geom stroke constants
	 * @param color, primary, secondary, ... refer to Geom color constants
	 */
	public Stroke(int type, int color) {
		super();
		this.type = type;
		this.color = color;
	}
	
}
