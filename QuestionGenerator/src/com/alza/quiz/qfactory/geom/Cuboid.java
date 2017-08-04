package com.alza.quiz.qfactory.geom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Cuboid implements Shapes3D{
	private double edgeLength;
	private double edgeWidth;
	private double edgeHeight;
	public Cuboid() {
		
	}
	
	
	public Cuboid(double edgeLength, double edgeWidth, double edgeHeight) {
		super();
		this.edgeLength = edgeLength;
		this.edgeWidth = edgeWidth;
		this.edgeHeight = edgeHeight;
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
		return edgeLength * edgeWidth * edgeHeight;
	}

	@Override
	public List<Shapes2D> getFaces() {
		List<Shapes2D> fcs = new ArrayList<Shapes2D>();
		for (int i = 0; i < 2; i++) {
			Rectangle r1 = new Rectangle(edgeLength, edgeWidth);
			Rectangle r2 = new Rectangle(edgeLength, edgeHeight);
			Rectangle r3 = new Rectangle(edgeHeight, edgeWidth);
			fcs.add(r1);
			fcs.add(r2);
			fcs.add(r3);
		}
		return fcs;
	}

	@Override
	public String getName() {
		return "cuboid";
	}

	@Override
	public Shapes3D createExample() {
		double el,wi,hi;
		do {
			el = ThreadLocalRandom.current().nextInt(5, 26);
			wi = ThreadLocalRandom.current().nextInt(5, 26);
			hi = ThreadLocalRandom.current().nextInt(5, 26);
		} while (el==wi||el==hi);
		
		return new Cuboid(el,wi,hi);
	}

	@Override
	public List<Point3D> getVertices() {
		List<Point3D> points = new ArrayList<Point3D>();
		Point3D a,b,c,d;
		Point3D e,f,g,h;
		a = new Point3D(0, 0, 0);
		b = new Point3D(this.edgeLength, 0, 0);
		c = new Point3D(this.edgeLength, this.edgeHeight,0);
		d = new Point3D(0, this.edgeHeight,0);
		
		e = new Point3D(0, 0, this.edgeWidth);
		f = new Point3D(this.edgeLength, 0, this.edgeWidth);
		g = new Point3D(this.edgeLength, this.edgeHeight,this.edgeWidth);
		h = new Point3D(0, this.edgeHeight,this.edgeWidth);
		
		points.add(a);points.add(b);points.add(c);points.add(d);
		points.add(e);points.add(f);points.add(g);points.add(h);
		return points;
	}


	@Override
	public int getEdgeCount() {
		return 12;
	}


	@Override
	public List<Path> getPaths() {
		List<Path> l = new ArrayList<Path>();
		
		l.add(Path.createLinePath(getVertices().get(0), getVertices().get(1)));
		l.add(Path.createLinePath(getVertices().get(1), getVertices().get(2)));
		l.add(Path.createLinePath(getVertices().get(2), getVertices().get(3)));
		l.add(Path.createLinePath(getVertices().get(3), getVertices().get(1)));
		
		l.add(Path.createLinePath(getVertices().get(4), getVertices().get(5)));
		l.add(Path.createLinePath(getVertices().get(5), getVertices().get(6)));
		l.add(Path.createLinePathDotted(getVertices().get(6), getVertices().get(7)));
		l.add(Path.createLinePathDotted(getVertices().get(7), getVertices().get(4)));
		
		l.add(Path.createLinePath(getVertices().get(0), getVertices().get(4)));
		l.add(Path.createLinePath(getVertices().get(1), getVertices().get(5)));
		l.add(Path.createLinePath(getVertices().get(2), getVertices().get(6)));
		l.add(Path.createLinePathDotted(getVertices().get(3), getVertices().get(7)));
		return l;
	}
}
