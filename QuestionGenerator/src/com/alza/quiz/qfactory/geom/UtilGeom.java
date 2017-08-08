package com.alza.quiz.qfactory.geom;

import java.util.ArrayList;
import java.util.List;

import com.alza.quiz.qfactory.geom.model.Circle;
import com.alza.quiz.qfactory.geom.model.Cylinder;
import com.alza.quiz.qfactory.geom.model.Path;
import com.alza.quiz.qfactory.geom.model.Point2D;
import com.alza.quiz.qfactory.geom.model.Point3D;
import com.alza.quiz.qfactory.geom.model.Shapes2D;
import com.alza.quiz.qfactory.geom.model.Shapes3D;

public class UtilGeom {
	public static Point2D getMaxXY(List<Point2D> points) {
		double maxX = 0;
		double maxY = 0;
		for (Point2D p : points) {
			if (p.x>maxX) maxX = p.x;
			if (p.y>maxY) maxY = p.y;
		}
		return new Point2D(maxX, maxY);
	}
	public static Point2D getMaxXY3D(List<Point3D> points) {
		double maxX = 0;
		double maxY = 0;
		for (Point3D p : points) {
			if (p.getProjectedPoint().x > maxX) maxX = p.getProjectedPoint().x;
			if (p.getProjectedPoint().y > maxY) maxY = p.getProjectedPoint().y;
		}
		return new Point2D(maxX, maxY);
	}
	public static Point2D getMinXY3D(List<Point3D> points) {
		double minX = 0;
		double minY = 0;
		for (Point3D p : points) {
			if (p.getProjectedPoint().x < minX) minX = p.getProjectedPoint().x;
			if (p.getProjectedPoint().y < minY) minY = p.getProjectedPoint().y;
		}
		return new Point2D(minX, minY);
	}
	private static Point2D getTransformedPointOnScreen(double scale,Point2D margin, Point2D p) {
		double newX = (p.x * scale) + margin.x;
		double newY = (p.y * scale) + margin.y;
		return new Point2D(newX,newY);
	}
	private static Point2D getTransformedPointOnScreen(double scale,Point2D margin, Point2D corr, Point2D p) {
		
		double newX = (p.x * scale) + margin.x - corr.x;
		double newY = (p.y * scale) + margin.y - corr.y;
		Point2D transformed = new Point2D(newX,newY); 
		//System.out.println("scale "+scale+" corr "+corr+" margin "+margin+" ori "+p+" transformed "+ transformed);
		return transformed;
	}
	public static List<Path> transformPaths(int canvasWidth, int canvasHeight, List<Path> paths, Point2D bound){
		double marginRatio = 0.1;
		double scaleY = (canvasHeight-(canvasHeight*marginRatio*2))/bound.y;
		double scaleX = (canvasWidth -(canvasWidth*marginRatio*2) )/bound.x;
		double scale = Math.min(scaleY, scaleX);
		double marginX = (canvasWidth - (scale * bound.x))/2;
		double marginY = (canvasHeight - (scale * bound.y))/2;
		Point2D margin = new Point2D(marginX, marginY);
		List<Path> l = new ArrayList<Path>();
		for (Path p : paths) {
			switch (p.pathType) {
			case Path.PATH_TYPE_LINE:
				l.add(Path.createLinePath(getTransformedPointOnScreen(scale, margin, p.start),getTransformedPointOnScreen(scale, margin, p.finish)));
				break;
			case Path.PATH_TYPE_LINE_DOTTED:
				l.add(Path.createLinePathDotted(getTransformedPointOnScreen(scale, margin, p.start),getTransformedPointOnScreen(scale, margin, p.finish)));
				break;
			case Path.PATH_TYPE_CIRCLE:
				l.add(Path.createCirclePath(getTransformedPointOnScreen(scale, margin, p.center), p.radHoriz * scale));
				break;
			case Path.PATH_TYPE_OVAL:
				l.add(Path.createOvalPath(getTransformedPointOnScreen(scale, margin,p.center), 
						p.radHoriz * scale,p.radVert * scale, p.startAngle, p.sweepAngle));
				break;
			case Path.PATH_TYPE_ARC:
				//l.add(Path.createArcPath(getTransformedPointOnScreen(scale, margin, p.start),getTransformedPointOnScreen(scale, margin, p.finish)));
				break;
			default:
				break;
			}
		}
		return l;
	}
	public static List<Path> transformPaths(int canvasWidth, int canvasHeight, Shapes2D shp){
		//double marginRatio = 0.1;
		Point2D bound = new Point2D(0, 0);
		if (shp.getVertices()!=null) {
			bound = getMaxXY(shp.getVertices());
		} else {
			if (shp instanceof Circle) {
				Circle c = (Circle) shp;
				bound = new Point2D(c.getRadius()*2, c.getRadius()*2);
			}
		}
		return transformPaths(canvasWidth, canvasHeight, shp.getPaths(), bound);
		/**
		double scaleY = (canvasHeight-(canvasHeight*marginRatio*2))/maxP.y;
		double scaleX = (canvasWidth -(canvasWidth*marginRatio*2) )/maxP.x;
		double scale = Math.min(scaleY, scaleX);
		double marginX = (canvasWidth - (scale * maxP.x))/2;
		double marginY = (canvasHeight - (scale * maxP.y))/2;
		Point2D margin = new Point2D(marginX, marginY);
		//transform paths by scale
		List<Path> lOri = shp.getPaths();
		List<Path> l = new ArrayList<Path>();
		for (Path p : lOri) {
			switch (p.pathType) {
			case Path.PATH_TYPE_LINE:
				l.add(Path.createLinePath(getTransformedPointOnScreen(scale, margin, p.start),getTransformedPointOnScreen(scale, margin, p.finish)));
				break;
			case Path.PATH_TYPE_LINE_DOTTED:
				l.add(Path.createLinePathDotted(getTransformedPointOnScreen(scale, margin, p.start),getTransformedPointOnScreen(scale, margin, p.finish)));
				break;
			case Path.PATH_TYPE_CIRCLE:
				l.add(Path.createCirclePath(getTransformedPointOnScreen(scale, margin, p.center), p.radHoriz * scale));
				break;
			case Path.PATH_TYPE_OVAL:
				l.add(Path.createOvalPath(getTransformedPointOnScreen(scale, margin,p.center), 
						p.radHoriz * scale,p.radVert * scale, p.startAngle, p.sweepAngle));
				break;
			case Path.PATH_TYPE_ARC:
				//l.add(Path.createArcPath(getTransformedPointOnScreen(scale, margin, p.start),getTransformedPointOnScreen(scale, margin, p.finish)));
				break;
			default:
				break;
			}
		}
		return l;**/
	}
	public static List<Path> transformPaths(int canvasWidth, int canvasHeight, Shapes3D shp){
		double marginRatio = 0.1;
		Point2D maxP = new Point2D(0, 0);
		Point2D minP = new Point2D(0, 0);
		if (shp.getVertices()!=null) {
			maxP = getMaxXY3D(shp.getVertices());
			minP = getMinXY3D(shp.getVertices());
			if (shp instanceof Cylinder) {
				
			}
		} else {
			if (shp instanceof Circle) {
				Circle c = (Circle) shp;
				maxP = new Point2D(c.getRadius()*2, c.getRadius()*2);
			}
		} 
		double xDist = maxP.x-minP.x;
		double yDist = maxP.y-minP.y;
		double scaleY = (canvasHeight-(canvasHeight*marginRatio*2))/yDist;
		double scaleX = (canvasWidth -(canvasWidth*marginRatio*2) )/xDist;
		double scale = Math.min(scaleY, scaleX);
		double marginX = (canvasWidth - (scale * xDist))/2;
		double marginY = (canvasHeight - (scale * yDist))/2;
		double xCorr = 0;
		double yCorr = 0;
		if (minP.x < 0) {
			xCorr = minP.x * scale;
		}
		if (minP.y < 0) {
			yCorr = minP.y * scale;
		}
		Point2D margin = new Point2D(marginX, marginY);
		Point2D corr = new Point2D(xCorr, yCorr);
		//transform paths by scale and axis shift (correction)
		System.out.println("corr "+corr+" margin "+margin+ " scale "+scale);
		List<Path> lOri = shp.getPaths();
		List<Path> l = new ArrayList<Path>();
		for (Path p : lOri) {
			
			switch (p.pathType) {
			case Path.PATH_TYPE_LINE:
				//System.out.println("p start l "+p.start.x+" , "+p.start.y);
				l.add(Path.createLinePath(getTransformedPointOnScreen(scale, margin, corr, p.start),getTransformedPointOnScreen(scale, margin, corr, p.finish)));
				break;
			case Path.PATH_TYPE_LINE_DOTTED:
				l.add(Path.createLinePathDotted(getTransformedPointOnScreen(scale, margin, corr, p.start),getTransformedPointOnScreen(scale, margin, corr, p.finish)));
				break;
			case Path.PATH_TYPE_CIRCLE:
				l.add(Path.createCirclePath(getTransformedPointOnScreen(scale, margin, p.center), p.radHoriz * scale));
				break;
			case Path.PATH_TYPE_ARC:
				//System.out.println("p start arc "+p.start.x+" , "+p.start.y);
				l.add(Path.createArcPath(getTransformedPointOnScreen(scale,margin,corr,p.start), 
						getTransformedPointOnScreen(scale, margin, corr, p.finish), 
						p.radHoriz *scale, p.radVert*scale/2));
				break;
			case Path.PATH_TYPE_OVAL:
				
				l.add(Path.createOvalPath(getTransformedPointOnScreen(scale, margin, corr,p.center), 
						p.radHoriz * scale,p.radVert * scale, p.startAngle, p.sweepAngle));
				break;
			case Path.PATH_TYPE_OVAL_DOTTED:
				
				l.add(Path.createOvalPathDotted(getTransformedPointOnScreen(scale, margin, corr,p.center), 
						p.radHoriz * scale,p.radVert * scale, p.startAngle, p.sweepAngle));
				break;
			default:
				break;
			}
		}
		return l;
	}
	
	public static double distance(Point2D ori, Point2D dest) {
        return Math.sqrt(((dest.x - ori.x) * (dest.x - ori.x)) + ((dest.y - ori.y) * (dest.y - ori.y)));
    }
	
	public static Point2D getTopLeft(Point2D p1, Point2D p2) {
        double minX = Math.min(p1.x, p2.x);
        double minY = Math.min(p1.y, p2.y);
        return new Point2D(minX, minY);
	}
	public static Point2D getBottomRight(Point2D p1, Point2D p2) {
        double minX = Math.max(p1.x, p2.x);
        double minY = Math.max(p1.y, p2.y);
        return new Point2D(minX, minY);
	}
	
    public static double angle(Point2D ori, Point2D dest) {
        return Math.toDegrees(Math.atan2(ori.y - dest.y, ori.x - dest.x));
    }
    
    public static double angleDiff(double a, double b) {
        double d = b - a;
        while (d >= 180d) { d -= 360d; }
        while (d < -180d) { d += 360d; }
        return d;
    }
    
}
