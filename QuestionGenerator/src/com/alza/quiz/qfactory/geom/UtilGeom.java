package com.alza.quiz.qfactory.geom;

import java.util.ArrayList;
import java.util.List;

import com.alza.quiz.qfactory.geom.model.Circle;
import com.alza.quiz.qfactory.geom.model.Geom;
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
	private static Point2D getBoundMin(List<Path> paths) {
		double minx=0,miny=0;
		for (Path path : paths) {
			if (path.boundMin.x<minx) {
				minx = path.boundMin.x;
			}
			if (path.boundMin.y<miny) {
				miny = path.boundMin.y;
			}
		}
		return new Point2D(minx, miny);
	}
	private static Point2D getBoundMax(List<Path> paths) {
		double maxx=0,maxy=0;
		for (Path path : paths) {
			if (path.boundMax.x>maxx) {
				maxx = path.boundMax.x;
			}
			if (path.boundMax.y>maxy) {
				maxy = path.boundMax.y;
			}
		}
		return new Point2D(maxx, maxy);
	}
	private static Point2D getTransformedPointOnScreen(double scale,Point2D margin, Point2D corr, Point2D p) {
		double newX = (p.x * scale) + margin.x - corr.x;
		double newY = (p.y * scale) + margin.y - corr.y;
		Point2D transformed = new Point2D(newX,newY); 
		return transformed;
	}
	public static List<Path> transformPaths(int canvasWidth, int canvasHeight, List<Path> paths){
		return transformPaths(canvasWidth, canvasHeight, paths, getBoundMax(paths),getBoundMin(paths));
	}
	public static List<Path> transformPaths(int canvasWidth, int canvasHeight, List<Path> paths, Point2D bound ){
		return transformPaths(canvasWidth, canvasHeight, paths, bound, new Point2D(0, 0));
	}
	public static List<Path> transformPaths(int canvasWidth, int canvasHeight, List<Path> paths, Point2D boundMax, Point2D boundMin){
		//calculate scale and margin
		double marginRatio = 0.1;
		double xDist = boundMax.x-boundMin.x;
		double yDist = boundMax.y-boundMin.y;
		double scaleY = (canvasHeight-(canvasHeight*marginRatio*2))/yDist;
		double scaleX = (canvasWidth -(canvasWidth*marginRatio*2) )/xDist;
		double scale = Math.min(scaleY, scaleX);
		double marginX = (canvasWidth - (scale * xDist))/2;
		double marginY = (canvasHeight - (scale * yDist))/2;
		Point2D margin = new Point2D(marginX, marginY);
		
		// identify negative values use it to shift coordinate(s)
		double xCorr = 0;
		double yCorr = 0;
		if (boundMin.x < 0) {
			xCorr = boundMin.x * scale;
		}
		if (boundMin.y < 0) {
			yCorr = boundMin.y * scale;
		}
		Point2D corr = new Point2D(xCorr, yCorr);
		
		List<Path> l = new ArrayList<Path>();
		for (Path p : paths) {
			Path pt = new Path();
			switch (p.pathType) {
			case Path.PATH_TYPE_LINE:
				if (p.stroke.type == Geom.STROKE_LINE) {
					pt = Path.createLinePath(getTransformedPointOnScreen(scale, margin, corr, p.start),
							getTransformedPointOnScreen(scale, margin, corr, p.finish));
				} else if(p.stroke.type == Geom.STROKE_DASH) {
					pt = Path.createLinePathDashed(getTransformedPointOnScreen(scale, margin, corr, p.start),
							getTransformedPointOnScreen(scale, margin, corr, p.finish));
				}
				break;
			case Path.PATH_TYPE_OVAL:
				if (p.stroke.type == Geom.STROKE_LINE) {
					pt = Path.createOvalPath(getTransformedPointOnScreen(scale, margin,corr,p.center), 
							p.radHoriz * scale,p.radVert * scale);
				} else if(p.stroke.type == Geom.STROKE_DASH) {
					pt = Path.createOvalPathDashed(getTransformedPointOnScreen(scale, margin, corr,p.center), 
							p.radHoriz * scale,p.radVert * scale);
				}
				break;
			case Path.PATH_TYPE_ARC:
				if (p.stroke.type == Geom.STROKE_LINE) {
					pt=Path.createArcPath(getTransformedPointOnScreen(scale, margin,corr,p.center), 
							p.radHoriz * scale,p.radVert * scale, p.startAngle, p.sweepAngle);
				} else if(p.stroke.type == Geom.STROKE_DASH) {
					pt=Path.createArcPathDashed(getTransformedPointOnScreen(scale, margin, corr,p.center), 
							p.radHoriz * scale,p.radVert * scale, p.startAngle, p.sweepAngle);
				}

				break;
			default:
				break;
			}
			pt.arcUseCenter=p.arcUseCenter;
			pt.fill = p.fill;
			pt.stroke = p.stroke;
			l.add(pt);
		}
		return l;
	}
	public static List<Path> transformPaths(int canvasWidth, int canvasHeight, Shapes2D shp){
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
	}
	public static List<Path> transformPaths(int canvasWidth, int canvasHeight, Shapes3D shp){
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
		return transformPaths(canvasWidth, canvasHeight, shp.getPaths(), maxP, minP);
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
