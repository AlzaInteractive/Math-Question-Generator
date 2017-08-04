package com.alza.quiz.qfactory.geom;

import java.util.ArrayList;
import java.util.List;

public class UtilGeom {
	public static Point2D getMaxXY(List<Point2D> points) {
		double maxX = 0;
		double maxY = 0;
		for (Point2D p : points) {
			if (p.x>maxX) maxX = p.x;
			if (p.y>maxY) maxX = p.y;
		}
		return new Point2D(maxX, maxY);
	}
	public static Point2D getMaxXY3D(List<Point3D> points) {
		int maxX = 0;
		int maxY = 0;
		for (Point3D p : points) {
			if (p.project2D().x > maxX) maxX = p.x;
			if (p.project2D().y > maxY) maxX = p.y;
		}
		return new Point2D(maxX, maxY);
	}
	private static Point2D scalePoint(double scale,Point2D p) {
		double newX = p.x * scale;
		double newY = p.y * scale;
		return new Point2D(newX,newY);
	}
	public static List<Path> getPaths(int canvasWidth, int canvasHeight, Shapes2D shp){
		double margin = 0.1;
		Point2D maxP = getMaxXY(shp.getVertices());
		double scaleY = (canvasHeight-(canvasHeight*margin*2))/maxP.y;
		double scaleX = (canvasWidth -(canvasWidth*margin*2) )/maxP.x;
		double scale = Math.min(scaleY, scaleX);
		//transform paths by scale
		List<Path> lOri = shp.getPaths();
		List<Path> l = new ArrayList<Path>();
		for (Path p : lOri) {
			switch (p.pathType) {
			case Path.PATH_TYPE_LINE:
				l.add(Path.createLinePath(scalePoint(scale, p.start),scalePoint(scale, p.finish)));
				break;
			case Path.PATH_TYPE_LINE_DOTTED:
				l.add(Path.createLinePathDotted(scalePoint(scale, p.start),scalePoint(scale, p.finish)));
				break;
			case Path.PATH_TYPE_CIRCLE:
				l.add(Path.createCirclePath(scalePoint(scale, p.center), p.radius * scale));
				break;
			case Path.PATH_TYPE_ARC:
				l.add(Path.createArcPath(scalePoint(scale, p.start),scalePoint(scale, p.finish)));
				break;
			default:
				break;
			}
		}
		return l;
	}
}
