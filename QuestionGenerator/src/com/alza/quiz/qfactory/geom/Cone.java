package com.alza.quiz.qfactory.geom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Cone implements Shapes3D{
	private Circle base;
	private double height;
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
		
		l.add(Path.createOvalPath(centerBottom, this.base.getRadius(), 
				this.base.getRadius()/2, 0, -180));
		l.add(Path.createOvalPathDotted(centerBottom, this.base.getRadius(), 
				this.base.getRadius()/2, 0, 180));
		
		return l;
	}
}
