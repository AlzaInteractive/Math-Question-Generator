package com.alza.quiz.qfactory.geom.model;


public class Path {
	public static final int PATH_TYPE_LINE=11;
	public static final int PATH_TYPE_ARC=13;
	public static final int PATH_TYPE_OVAL=15;
	public boolean arcUseCenter=false;
	public Point2D start;
	public Point2D finish;
	public double startAngle,endAngle,sweepAngle;
	public int pathType;
	public Fill fill;
	public Stroke stroke;
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
		p.stroke = new Stroke();
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
	public static Path createLinePathDashed(Point2D start, Point2D finish){
		Path p = Path.createLinePath(start, finish);
		p.stroke = new Stroke(Geom.STROKE_DASH);
		return p;
	}
	public static Path createLinePathDotted(Point3D start, Point3D finish){
		Path p = Path.createLinePath(start, finish);
		p.stroke = new Stroke(Geom.STROKE_DASH);
		return p;
	}
	public static Path createCirclePath(Point2D center, double radius){
		Path p = createOvalPath(center,radius,radius);
		return p;
	}
	public static Path createOvalPath(Point2D center, double radiusX, double radiusY){
		Path p = new Path();
		p.center = center;
		p.radHoriz = radiusX;
		p.radVert = radiusY;
		p.stroke = new Stroke();
		p.pathType = PATH_TYPE_OVAL;
		p.boundMin = new Point2D(center.x - radiusX, center.y -radiusY);
		p.boundMax = new Point2D(center.x + radiusX, center.y +radiusY);
		return p;
	}
	public static Path createOvalPathDashed(Point2D center, double radiusX, double radiusY){
		Path p = createOvalPath(center, radiusX, radiusY);
		p.stroke = new Stroke(Geom.STROKE_DASH);		
		return p;
	}
	public static Path createArcPath(Point2D center, double radiusX, double radiusY, double angleStart, double angleSweep){
		Path p = new Path();
		p.center = center;
		p.radHoriz = radiusX;
		p.radVert = radiusY;
		p.startAngle = angleStart;
		p.sweepAngle = angleSweep;
		p.stroke = new Stroke();
		p.pathType = PATH_TYPE_ARC;
		p.boundMin = new Point2D(center.x - radiusX, center.y -radiusY);
		p.boundMax = new Point2D(center.x + radiusX, center.y +radiusY);
		return p;
	}
	
	
	public static Path createArcPathDashed(Point2D center, double radiusX, double radiusY, double angleStart, double angleSweep){
		Path p = createArcPath(center, radiusX, radiusY, angleStart, angleSweep);
		p.stroke = new Stroke(Geom.STROKE_DASH);		
		return p;
	}
	public static Path createArcPathFilled(Point2D center, double radiusX, double radiusY, double angleStart, double angleSweep){
		Path p = createArcPath(center, radiusX, radiusY, angleStart, angleSweep);
		p.fill = new Fill();		
		return p;
	}
	public static Path createArcPath(Point3D center, double radiusX, double radiusY, double angleStart, double angleSweep){
		return Path.createArcPath(center.getProjectedPoint(), radiusX, radiusY, angleStart, angleSweep);
	}
	public static Path createArcPathDashed(Point3D center, double radiusX, double radiusY, double angleStart, double angleSweep){
		Path p = Path.createArcPathDashed(center.getProjectedPoint(), radiusX, radiusY, angleStart, angleSweep);
		return p;
	}
	public String toString(){
		String s="";
		switch (pathType) {
		case PATH_TYPE_LINE:
			s = "Line from: "+start.x+","+start.y +" to " +finish.x+","+finish.y;
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
