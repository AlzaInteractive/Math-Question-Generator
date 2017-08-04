package com.alza.quiz.qfactory.geom;

import java.util.ArrayList;
import java.util.List;

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
		return new Point2D(newX,newY);
	}
	
	public static List<Path> getPaths(int canvasWidth, int canvasHeight, Shapes2D shp){
		double marginRatio = 0.1;
		Point2D maxP = new Point2D(0, 0);
		if (shp.getVertices()!=null) {
			maxP = getMaxXY(shp.getVertices());
		} else {
			if (shp instanceof Circle) {
				Circle c = (Circle) shp;
				maxP = new Point2D(c.getRadius()*2, c.getRadius()*2);
			}
		} 
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
				l.add(Path.createCirclePath(getTransformedPointOnScreen(scale, margin, p.center), p.radius * scale));
				break;
			case Path.PATH_TYPE_ARC:
				l.add(Path.createArcPath(getTransformedPointOnScreen(scale, margin, p.start),getTransformedPointOnScreen(scale, margin, p.finish)));
				break;
			default:
				break;
			}
		}
		return l;
	}
	public static List<Path> getPaths(int canvasWidth, int canvasHeight, Shapes3D shp){
		double marginRatio = 0.1;
		Point2D maxP = new Point2D(0, 0);
		Point2D minP = new Point2D(0, 0);
		if (shp.getVertices()!=null) {
			maxP = getMaxXY3D(shp.getVertices());
			minP = getMinXY3D(shp.getVertices());
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
		List<Path> lOri = shp.getPaths();
		List<Path> l = new ArrayList<Path>();
		for (Path p : lOri) {
			switch (p.pathType) {
			case Path.PATH_TYPE_LINE:
				l.add(Path.createLinePath(getTransformedPointOnScreen(scale, margin, corr, p.start),getTransformedPointOnScreen(scale, margin, corr, p.finish)));
				break;
			case Path.PATH_TYPE_LINE_DOTTED:
				l.add(Path.createLinePathDotted(getTransformedPointOnScreen(scale, margin, corr, p.start),getTransformedPointOnScreen(scale, margin, corr, p.finish)));
				break;
			case Path.PATH_TYPE_CIRCLE:
				l.add(Path.createCirclePath(getTransformedPointOnScreen(scale, margin, p.center), p.radius * scale));
				break;
			case Path.PATH_TYPE_ARC:
				l.add(Path.createArcPath(getTransformedPointOnScreen(scale, margin,  p.start),getTransformedPointOnScreen(scale, margin, p.finish)));
				break;
			default:
				break;
			}
		}
		return l;
	}
}
