package com.alza.quiz.qfactory.geom;

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
	public Point2D center;
	public double radHoriz;
	public double radVert;
	
	public static Path createLinePath(Point2D start, Point2D finish){
		Path p = new Path();
		p.start = start;
		p.finish = finish;
		p.pathType = PATH_TYPE_LINE;
		return p;
	}
	public static Path createLinePath(Point3D start, Point3D finish){
		Path p = new Path();
		p.start = start.getProjectedPoint();
		p.finish = finish.getProjectedPoint();
		p.pathType = PATH_TYPE_LINE;
		
		System.out.println("ori "+start+" to "+finish);
		System.out.println("trf "+p.start+" to "+p.finish);
		return p;
	}
	public static Path createCirclePath(Point2D center, double radius){
		Path p = new Path();
		p.radHoriz = radius;
		p.center = center;
		p.pathType = PATH_TYPE_CIRCLE;
		return p;
	}
	public static Path createOvalPath(Point2D center, double radiusX, double radiusY){
		Path p = new Path();
		p.radHoriz = radiusX;
		p.radVert = radiusY;
		p.center = center;
		p.pathType = PATH_TYPE_OVAL;
		p.startAngle = 0;
		p.sweepAngle = 360;
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
		System.out.println("center "+p.center);
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
	public static Path createArcPath(Point2D p1, Point2D p2, double width, double height){
		//System.out.println("p start l create path "+p1.x+" , "+p1.y);
		double centerX = p1.x + (p2.x-p1.x)/2;
		double centerY = p1.y + (p2.y-p1.y)/2;
		Point2D topLeft = UtilGeom.getTopLeft(p1, p2);
		Point2D botRight = UtilGeom.getBottomRight(p1, p2);
		Path p = new Path();
		p.start = topLeft;
		p.finish = botRight;
		p.center = new Point2D(centerX, centerY);
		p.radHoriz = width;
		p.radVert = height;
		p.startAngle = UtilGeom.angle(p1, p.center);
		p.endAngle = UtilGeom.angle(p2, p.center);
		System.out.println("angle "+p.startAngle+" "+p.endAngle);
		p.pathType = PATH_TYPE_ARC;
		return p;
	}
	public static Path createArcPath(Point3D p13d, Point3D p23d, double width, double height){
		Point2D p1 = p13d.getProjectedPoint();
		Point2D p2 = p23d.getProjectedPoint();
		return createArcPath(p1, p2, width, height);
	}
	
	public static Path createLinePathDotted(Point2D start, Point2D finish){
		Path p = new Path();
		p.start = start;
		p.finish = finish;
		p.pathType = PATH_TYPE_LINE_DOTTED;
		return p;
	}
	public static Path createLinePathDotted(Point3D start, Point3D finish){
		Path p = new Path();
		p.start = start.getProjectedPoint();
		p.finish = finish.getProjectedPoint();
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
