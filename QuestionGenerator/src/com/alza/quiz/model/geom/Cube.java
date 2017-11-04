package com.alza.quiz.model.geom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Cube implements Shapes3D{
	private double edgeLength;
	public Cube() {
		
	}
	
	public Cube(double edgeLength) {
		super();
		this.edgeLength = edgeLength;
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
		return edgeLength * edgeLength * edgeLength;
	}

	@Override
	public List<Shapes2D> getFaces() {
		List<Shapes2D> fcs = new ArrayList<Shapes2D>();
		for (int i = 0; i < 6; i++) {
			Square shp = new Square(edgeLength);
			fcs.add(shp);
		}
		return fcs;
	}


	@Override
	public String getName() {
		return "cube";
	}

	@Override
	public Shapes3D createExample() {
		double el = ThreadLocalRandom.current().nextInt(5, 26);
		return new Cube(el);
	}

	@Override
	public List<Point3D> getVertices() {
		List<Point3D> points = new ArrayList<Point3D>();
		Point3D a,b,c,d;
		Point3D e,f,g,h;
		a = new Point3D(0, 0, 0);
		b = new Point3D(this.edgeLength, 0, 0);
		c = new Point3D(this.edgeLength, this.edgeLength,0);
		d = new Point3D(0, this.edgeLength,0);
		
		e = new Point3D(0, 0, this.edgeLength);
		f = new Point3D(this.edgeLength, 0, this.edgeLength);
		g = new Point3D(this.edgeLength, this.edgeLength,this.edgeLength);
		h = new Point3D(0, this.edgeLength,this.edgeLength);
		
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
		l.add(Path.createLinePath(getVertices().get(3), getVertices().get(0)));
		
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
