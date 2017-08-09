package com.alza.quiz.qfactory.geom.model;


public class Path {
	public static final int PATH_TYPE_LINE=11;
	public static final int PATH_TYPE_CIRCLE=12;
	public static final int PATH_TYPE_ARC=13;
	public static final int PATH_TYPE_LINE_DOTTED=14;
	public static final int PATH_TYPE_OVAL=15;
	public static final int PATH_TYPE_OVAL_DOTTED=16;
	public Point2D start;
	public Point2D finish;
	public double startAngle,endAngle,sweepAngle;
	public int pathType;
	public boolean fill;
	public Point2D center;
	public Point2D boundMax;
	public Point2D boundMin;
	public double radHoriz;
	public double radVert;
	
	public static Path createLinePath(Point2D start, Point2D finish){
		Path p = new Path();
		p.start = start;
		p.finish = finish;
		p.pathType = PATH_TYPE_LINE;
		double maxX = 0;
		double maxY = 0;
		double minX = 0;
		double minY = 0;
		maxX = Math.max(start.x, finish.x);
		maxY = Math.max(start.y, finish.y);
		minX = Math.min(start.x, finish.x);
		minY = Math.min(start.y, finish.y);
		p.boundMin = new Point2D(minX, minY);
		p.boundMax = new Point2D(maxX, maxY);
		return p;
	}
	public static Path createLinePath(Point3D start, Point3D finish){
		return Path.createLinePath(start.getProjectedPoint(), finish.getProjectedPoint());
	}
	public static Path createLinePathDotted(Point2D start, Point2D finish){
		Path p = Path.createLinePath(start, finish);
		p.pathType = PATH_TYPE_LINE_DOTTED;
		return p;
	}
	public static Path createLinePathDotted(Point3D start, Point3D finish){
		Path p = Path.createLinePath(start, finish);
		p.pathType = PATH_TYPE_LINE_DOTTED;
		return p;
	}
	public static Path createCirclePath(Point2D center, double radius){
		Path p = new Path();
		p.radHoriz = radius;
		p.center = center;
		p.pathType = PATH_TYPE_CIRCLE;
		p.boundMin = new Point2D(center.x - radius, center.y -radius);
		p.boundMax = new Point2D(center.x + radius, center.y +radius);
		return p;
	}
	public static Path createOvalPath(Point2D center, double radiusX, double radiusY, double angleStart, double angleSweep){
		Path p = new Path();
		p.center = center;
		p.radHoriz = radiusX;
		p.radVert = radiusY;
		p.startAngle = angleStart;
		p.sweepAngle = angleSweep;
		p.pathType = PATH_TYPE_OVAL;
		p.boundMin = new Point2D(center.x - radiusX, center.y -radiusY);
		p.boundMax = new Point2D(center.x + radiusX, center.y +radiusY);
		return p;
	}
	public static Path createOvalPath(Point2D center, double radiusX, double radiusY){
		Path p = Path.createOvalPath(center, radiusX, radiusY, 0, 360);
		return p;
	}
	
	public static Path createOvalPathDotted(Point2D center, double radiusX, double radiusY, double angleStart, double angleSweep){
		Path p = createOvalPath(center, radiusX, radiusY, angleStart, angleSweep);
		p.pathType = PATH_TYPE_OVAL_DOTTED;		
		return p;
	}
	public static Path createOvalPath(Point3D center, double radiusX, double radiusY, double angleStart, double angleSweep){
		return Path.createOvalPath(center.getProjectedPoint(), radiusX, radiusY, angleStart, angleSweep);
	}
	public static Path createOvalPathDotted(Point3D center, double radiusX, double radiusY, double angleStart, double angleSweep){
		Path p = Path.createOvalPathDotted(center.getProjectedPoint(), radiusX, radiusY, angleStart, angleSweep);
		return p;
	}
	public String toString(){
		String s="";
		switch (pathType) {
		case PATH_TYPE_LINE:
			s = "Line from: "+start.x+","+start.y +" to " +finish.x+","+finish.y;
			break;
		case PATH_TYPE_CIRCLE:
			s = "circle with "+ radHoriz+" radius";
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
