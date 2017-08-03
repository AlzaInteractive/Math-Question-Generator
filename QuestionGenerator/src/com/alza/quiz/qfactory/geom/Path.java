package com.alza.quiz.qfactory.geom;

public class Path {
	public static final int PATH_TYPE_LINE=11;
	public static final int PATH_TYPE_CIRCLE=12;
	public static final int PATH_TYPE_ARC=13;
	public static final int PATH_TYPE_LINE_DOTTED=14;
	public Point2D start;
	public Point2D finish;
	public int pathType;
	public Point2D center;
	public int radius;
	
	public static Path createLinePath(Point2D start, Point2D finish){
		Path p = new Path();
		p.start = start;
		p.finish = finish;
		p.pathType = PATH_TYPE_LINE;
		return p;
	}
	public static Path createLinePath(Point3D start, Point3D finish){
		Path p = new Path();
		p.start = start.project2D();
		p.finish = finish.project2D();
		p.pathType = PATH_TYPE_LINE;
		return p;
	}
	public static Path createCirclePath(Point2D center, int radius){
		Path p = new Path();
		p.radius = radius;
		p.center = center;
		p.pathType = PATH_TYPE_CIRCLE;
		return p;
	}
	public static Path createArcPath(Point2D start, Point2D finish){
		Path p = new Path();
		p.start = start;
		p.finish = finish;
		p.pathType = PATH_TYPE_ARC;
		return p;
	}
	public static Path createLinePathDotted(Point2D start, Point2D finish){
		Path p = new Path();
		p.start = start;
		p.finish = finish;
		p.pathType = PATH_TYPE_LINE_DOTTED;
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
