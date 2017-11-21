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
	
	public static Point2D getMidPoint(Point2D p1, Point2D p2){
		double x = p1.x + (p2.x-p1.x)/2;
		double y = p1.y + (p2.y-p1.y)/2;
		return new Point2D(x, y);
	}
	
	@Override
	public String toString() {
		return "("+(int)x+","+(int)y+")";
	}
}
