package com.alza.quiz.qfactory.geom;

public class Path {
	public static final int PATH_TYPE_LINE=11;
	public static final int PATH_TYPE_CIRCLE=12;
	public static final int PATH_TYPE_ARC=13;
	public static final int PATH_TYPE_LINE_DOTTED=14;
	public Point start;
	public Point finish;
	public int pathType;
	public Point center;
	public int radius;
	
	public static Path createLinePath(Point start, Point finish){
		Path p = new Path();
		p.start = start;
		p.finish = finish;
		p.pathType = PATH_TYPE_LINE;
		return p;
	}
	public static Path createCirclePath(Point center, int radius){
		Path p = new Path();
		p.radius = radius;
		p.center = center;
		p.pathType = PATH_TYPE_CIRCLE;
		return p;
	}
	public static Path createArcPath(Point start, Point finish){
		Path p = new Path();
		p.start = start;
		p.finish = finish;
		p.pathType = PATH_TYPE_ARC;
		return p;
	}
	public String toString(){
		String s="";
		switch (pathType) {
		case PATH_TYPE_LINE:
			s = "Line from: "+start.x+","+start.y +" to " +finish.x+","+finish.y;
			break;
		case PATH_TYPE_CIRCLE:
			s = "circle with "+ radius+" radius";
			break;
		case PATH_TYPE_ARC:
			s = "Arc from: "+start.x+","+start.y +" to " +finish.x+","+finish.y; 
			break;
		default:
			break;
		}
		return s;
	}
	
}
