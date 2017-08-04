package com.alza.quiz.qfactory.geom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TriangularPrism implements Shapes3D{
	private Triangle baseTriangle;
	private double prismHeight;
	public TriangularPrism() {
		
	}
	public TriangularPrism(Triangle baseTriangle, double prismHeight) {
		super();
		this.baseTriangle = baseTriangle;
		this.prismHeight = prismHeight;
	}
	public Triangle getBaseTriangle() {
		return baseTriangle;
	}
	public void setBaseTriangle(Triangle baseTriangle) {
		this.baseTriangle = baseTriangle;
	}
	public double getPrismHeight() {
		return prismHeight;
	}
	public void setPrismHeight(double prismHeight) {
		this.prismHeight = prismHeight;
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
		return baseTriangle.getArea() * prismHeight;
	}

	@Override
	public List<Shapes2D> getFaces() {
		List<Shapes2D> fcs = new ArrayList<Shapes2D>();
		fcs.add(baseTriangle);
		fcs.add(baseTriangle);
		fcs.add(new Rectangle(baseTriangle.getBaseLine(), prismHeight));
		fcs.add(new Rectangle(baseTriangle.getLeftEdge(), prismHeight));
		fcs.add(new Rectangle(baseTriangle.getRightEdge(), prismHeight));
		return fcs;
	}
	@Override
	public String getName() {
		return "triangularprism";
	}

	@Override
	public Shapes3D createExample() {
		Triangle bs = (Triangle) new Triangle().createExample();
		int height = ThreadLocalRandom.current().nextInt(5, 26);
		return new TriangularPrism(bs, height);
	}

	@Override
	public List<Point3D> getVertices() {
		List<Point3D> points = new ArrayList<Point3D>();
		Point3D a,b,c;
		Point3D d,e,f;
		a = new Point3D(0, 0, 0);
		b = new Point3D(this.baseTriangle.getBaseLine(), 0, 0);
		c = new Point3D(this.baseTriangle.getShear(), 0 , this.baseTriangle.getHeight());
		
		d = new Point3D(0, prismHeight, 0);
		e = new Point3D(this.baseTriangle.getBaseLine(), prismHeight, 0);
		f = new Point3D(this.baseTriangle.getShear(), prismHeight , this.baseTriangle.getHeight());
		
		
		points.add(a);points.add(b);points.add(c);
		points.add(d);points.add(e);points.add(f);
		return points;
	}
	@Override
	public int getEdgeCount() {
		return 6;
	}
	@Override
	public List<Path> getPaths() {
		List<Path> l = new ArrayList<Path>();
		
		l.add(Path.createLinePath(getVertices().get(0), getVertices().get(1)));
		l.add(Path.createLinePath(getVertices().get(1), getVertices().get(2)));
		l.add(Path.createLinePath(getVertices().get(2), getVertices().get(0)));
		
		l.add(Path.createLinePath(getVertices().get(3), getVertices().get(4)));
		l.add(Path.createLinePathDotted(getVertices().get(4), getVertices().get(5)));
		l.add(Path.createLinePathDotted(getVertices().get(5), getVertices().get(3)));
		
		l.add(Path.createLinePath(getVertices().get(0), getVertices().get(3)));
		l.add(Path.createLinePath(getVertices().get(1), getVertices().get(4)));
		l.add(Path.createLinePathDotted(getVertices().get(2), getVertices().get(5)));
		return l;
	}
}
