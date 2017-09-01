package com.alza.quiz.qfactory.geom.model;

public class Fill {
	public int color;//refer to Geom ColorPrimary
	/**
	 * Create a default fill i,e : fill with primary color
	 */
	public Fill() {
		this.color = Geom.COLOR_PRIMARY;
	}
	/**
	 * 
	 * @param color, primary, secondary, ... refer to Geom color constants
	 */
	public Fill(int type, int color) {
		super();
		this.color = color;
	}
	
}
