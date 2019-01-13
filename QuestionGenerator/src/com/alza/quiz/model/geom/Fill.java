package com.alza.quiz.model.geom;

import com.alza.quiz.model.geom.plane.Geom;

public class Fill {
	public int color;//refer to Geom ColorPrimary
	/**
	 * Create a default fill i,e : fill with primary color
	 */
	public Fill() {
		this.color = Geom.FILL_COLOR_PRIMARY;
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
