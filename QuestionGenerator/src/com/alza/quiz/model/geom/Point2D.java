package com.alza.quiz.model.geom;

public class Point2D {
	public double x,y;

	public Point2D(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Point2D move(double xDistance, double yDistance){
		return new Point2D(x+xDistance, y+yDistance);
	}
	
	@Override
	public String toString() {
		return "("+(int)x+","+(int)y+")";
	}
}
