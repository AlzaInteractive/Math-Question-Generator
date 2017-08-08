package com.alza.quiz.qfactory.geom;

public class Point2D {
	public double x,y;

	public Point2D(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "("+(int)x+","+(int)y+")";
	}
}
