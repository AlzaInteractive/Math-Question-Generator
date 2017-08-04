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
			if (p.getProjectedPoint().x > maxX) maxX = p.x;
			if (p.getProjectedPoint().y > maxY) maxY = p.y;
		}
		return new Point2D(maxX, maxY);
	}
	private static Point2D getTransformedPointOnScreen(double scale,Point2D margin, Point2D p) {
		double newX = (p.x * scale) + margin.x;
		double newY = (p.y * scale) + margin.y;
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
}
