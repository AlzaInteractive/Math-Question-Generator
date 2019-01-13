package com.alza.quiz.model.geom.solid;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.alza.quiz.model.geom.Path;
import com.alza.quiz.model.geom.Point3D;
import com.alza.quiz.model.geom.Shapes2D;
import com.alza.quiz.model.geom.Shapes3D;
import com.alza.quiz.model.geom.plane.Circle;
import com.alza.quiz.model.geom.plane.Geom;

public class Cone implements Shapes3D{
	private Circle base;
	private double height;
	private boolean showBaseRadius=true;
	private boolean showBaseRadiusLength=true;
	private boolean showHeightLine=true;
	private boolean showHeightLength=true;
	public Cone() {
		
	}
	public Cone(Circle baseCircle, double height) {
		super();
		this.base = baseCircle;
		this.height = height;
	}
	public Circle getBaseCircle() {
		return base;
	}
	public void setBaseRectangle(Circle base) {
		this.base = base;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public Circle getBase() {
		return base;
	}
	public void setBase(Circle base) {
		this.base = base;
	}
	public boolean isShowBaseRadius() {
		return showBaseRadius;
	}
	public void setShowBaseRadius(boolean showBaseRadius) {
		this.showBaseRadius = showBaseRadius;
	}
	public boolean isShowBaseRadiusLength() {
		return showBaseRadiusLength;
	}
	public void setShowBaseRadiusLength(boolean showBaseRadiusLength) {
		this.showBaseRadiusLength = showBaseRadiusLength;
	}
	public boolean isShowHeightLine() {
		return showHeightLine;
	}
	public void setShowHeightLine(boolean showHeightLine) {
		this.showHeightLine = showHeightLine;
	}
	public boolean isShowHeightLength() {
		return showHeightLength;
	}
	public void setShowHeightLength(boolean showHeightLength) {
		this.showHeightLength = showHeightLength;
	}
	@Override
	public double getSurfaceArea() {
		List<Shapes2D> fcs = getFaces();
		double surfaceArea = 0.0;
		for (Shapes2D shapes2d : fcs) {
			surfaceArea = surfaceArea + shapes2d.getArea(); 
		}
		return surfaceArea;
	}
	@Override
	public double getVolume() {
		return base.getArea() * height / 3;
	}

	@Override
	public List<Shapes2D> getFaces() {
		List<Shapes2D> fcs = new ArrayList<Shapes2D>();
		fcs.add(base);
		return fcs;
	}
	@Override
	public String getName() {
		return "cone";
	}

	@Override
	public Shapes3D createExample() {
		Circle bs;
		int height;
		do {
			bs = (Circle) new Circle().createExample();
			height = ThreadLocalRandom.current().nextInt(5, 26);
		} while (((double)height/(double)bs.getRadius())<2.5);
		return new Cone(bs, height);
	}

	@Override
	public List<Point3D> getVertices() {
		List<Point3D> points = new ArrayList<Point3D>();
		Point3D a,b,c,d;
		
		a = new Point3D(0, height, 0);
		b = new Point3D(this.base.getRadius()*2, height, 0);
		c = new Point3D(this.base.getRadius(), 0 , 0);
		d = new Point3D(base.getRadius(), height, -base.getRadius());//bottom front
		
		
		points.add(a);points.add(b);points.add(c);points.add(d);
		return points;
	}
	@Override
	public int getEdgeCount() {
		return 8;
	}
	@Override
	public List<Path> getPaths() {
		List<Path> l = new ArrayList<Path>();
		
		l.add(Path.createLinePath(getVertices().get(0), getVertices().get(2)));
		l.add(Path.createLinePath(getVertices().get(1), getVertices().get(2)));
		
		Point3D centerBottom = new Point3D(this.base.getRadius(),height,0);
		//draw base
		l.add(Path.createArcPath(centerBottom, this.base.getRadius(), 
				this.base.getRadius()/2, 0, -180));
		l.add(Path.createArcPathDashed(centerBottom, this.base.getRadius(), 
				this.base.getRadius()/2, 0, 180));
		
		//draw base radius
		if (showBaseRadius) {
			l.add(Path.createLinePathDotted(centerBottom, getVertices().get(1)));
		}
		if(showBaseRadiusLength) {
			l.add(Path.createTextPath(Geom.formatMeasurement(this.base.getRadius()), 
					Point3D.getMidPoint(getVertices().get(1), centerBottom)
					, Path.SHIFT_UP,Path.SHIFT_NONE));
		}
		
		if (showHeightLine) {
			l.add(Path.createLinePathDotted(centerBottom, getVertices().get(2)));
		}
		if (showHeightLength) {
			l.add(Path.createTextPath(Geom.formatMeasurement(height), 
					Point3D.getMidPoint(getVertices().get(2), centerBottom)
					, Path.SHIFT_NONE,Path.SHIFT_RIGHT));
		}
		
		
		return l;
	}
}
