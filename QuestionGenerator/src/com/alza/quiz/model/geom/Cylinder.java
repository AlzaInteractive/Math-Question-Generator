package com.alza.quiz.model.geom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Cylinder implements Shapes3D{
	private Circle base;
	private double height;
	private boolean showHeightValue=true;
	private boolean showBaseRadiusValue=true;
	private boolean showBaseRadius=true;
	public Cylinder() {
		
	}
	public Cylinder(Circle baseCircle, double height) {
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
	public boolean isShowHeightValue() {
		return showHeightValue;
	}
	public void setShowHeightValue(boolean showHeightValue) {
		this.showHeightValue = showHeightValue;
	}
	public boolean isShowBaseRadiusValue() {
		return showBaseRadiusValue;
	}
	public void setShowBaseRadiusValue(boolean showBaseRadiusValue) {
		this.showBaseRadiusValue = showBaseRadiusValue;
	}
	
	public boolean isShowBaseRadius() {
		return showBaseRadius;
	}
	public void setShowBaseRadius(boolean showBaseRadius) {
		this.showBaseRadius = showBaseRadius;
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
		return base.getArea() * height;
	}

	@Override
	public List<Shapes2D> getFaces() {
		List<Shapes2D> fcs = new ArrayList<Shapes2D>();
		fcs.add(base);
		fcs.add(base);
		fcs.add(new Rectangle(base.getPerimeter(),height));
		return fcs;
	}
	@Override
	public String getName() {
		return "cylinder";
	}

	@Override
	public Shapes3D createExample() {
		Circle bs;
		int height;
		do {
			bs = (Circle) new Circle().createExample();
			height = ThreadLocalRandom.current().nextInt(5, 26);
		} while (false);
		return new Cylinder(bs, height);
	}

	@Override
	public List<Point3D> getVertices() {
		List<Point3D> points = new ArrayList<Point3D>();
		Point3D a,b,c,d,e,f;
		
		a = new Point3D(0, 0, 0);
		b = new Point3D(this.base.getRadius()*2, 0, 0);
		c = new Point3D(0, height, 0);
		d = new Point3D(this.base.getRadius()*2, height, 0);
		e = new Point3D(base.getRadius(), 0, base.getRadius());
		f = new Point3D(base.getRadius(), height, -base.getRadius());
				
		points.add(a);points.add(b);points.add(c);points.add(d);
		points.add(e);points.add(f);
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
		l.add(Path.createLinePath(getVertices().get(1), getVertices().get(3)));
		
		Point3D centerTop = new Point3D(this.base.getRadius(),0,0);
		Point3D centerBottom = new Point3D(this.base.getRadius(),height,0);
		
		//double arcWidth = this.base.getRadius() * 2;
		//double arcHeight = this.base.getRadius() * 2;
		
		//l.add(Path.createArcPath(getVertices().get(0),getVertices().get(1),arcWidth,arcHeight));
		l.add(Path.createArcPath(centerTop, this.base.getRadius(), 
				this.base.getRadius()/2, 0, 360));
		l.add(Path.createArcPath(centerBottom, this.base.getRadius(), 
				this.base.getRadius()/2, 0, -180));
		l.add(Path.createArcPathDashed(centerBottom, this.base.getRadius(), 
				this.base.getRadius()/2, 0, 180));
		
		if (showHeightValue) {
			l.add(Path.createTextPath(Geom.formatMeasurement(height), 
					Point3D.getMidPoint(getVertices().get(1), getVertices().get(3))
					, Path.SHIFT_NONE,Path.SHIFT_RIGHT));
		}
		if (showBaseRadius) {
			l.add(Path.createLinePathDotted(centerBottom, getVertices().get(3)));
		}
		if (showBaseRadiusValue) {
			l.add(Path.createTextPath(Geom.formatMeasurement(base.getRadius()), 
					Point3D.getMidPoint(centerBottom, getVertices().get(3))
					, Path.SHIFT_UP,Path.SHIFT_NONE));
		}
		
		return l;
	}
}
