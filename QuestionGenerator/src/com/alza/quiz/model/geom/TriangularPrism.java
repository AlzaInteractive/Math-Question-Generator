package com.alza.quiz.model.geom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TriangularPrism implements Shapes3D{
	private Triangle baseTriangle;
	private double prismHeight;
	private boolean showPrismHeight = true;
	private boolean showBaseTriangleHeightLine = true;
	private boolean showBaseTriangleHeightValue = true;
	private boolean showBaseTriangleBaselineValue = true;
	private boolean showBaseTriangleBaseLengthValue = true;
	private boolean showVerticesLabel = true;
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
	public boolean isShowPrismHeight() {
		return showPrismHeight;
	}

	public void setShowPrismHeight(boolean showPrismHeight) {
		this.showPrismHeight = showPrismHeight;
	}

	public boolean isShowBaseTriangleBaselineValue() {
		return showBaseTriangleBaselineValue;
	}

	public void setShowBaseTriangleBaselineValue(boolean showBaseTriangleBaselineValue) {
		this.showBaseTriangleBaselineValue = showBaseTriangleBaselineValue;
	}

	public boolean isShowVerticesLabel() {
		return showVerticesLabel;
	}

	public void setShowVerticesLabel(boolean showVerticesLabel) {
		this.showVerticesLabel = showVerticesLabel;
	}

	public boolean isShowPrismHeightLength() {
		return showPrismHeight;
	}

	public void setShowPrismHeightLength(boolean showPrismHeightLength) {
		this.showPrismHeight = showPrismHeightLength;
	}

	public boolean isShowBaseTriangleHeightLine() {
		return showBaseTriangleHeightLine;
	}

	public void setShowBaseTriangleHeightLine(boolean showBaseTriangleHeightLine) {
		this.showBaseTriangleHeightLine = showBaseTriangleHeightLine;
	}

	public boolean isShowBaseTriangleHeightValue() {
		return showBaseTriangleHeightValue;
	}

	public void setShowBaseTriangleHeightValue(boolean showBaseTriangleHeightValue) {
		this.showBaseTriangleHeightValue = showBaseTriangleHeightValue;
	}

	public boolean isShowBaseTriangleBaseLengthValue() {
		return showBaseTriangleBaseLengthValue;
	}

	public void setShowBaseTriangleBaseLengthValue(boolean showBaseTriangleBaseLengthValue) {
		this.showBaseTriangleBaseLengthValue = showBaseTriangleBaseLengthValue;
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
		Triangle bs;
		int height;
		do {
			bs = (Triangle) new Triangle().createExample();
			height = ThreadLocalRandom.current().nextInt(5, 26);
		} while (bs.getShear()/bs.getBaseLine()>0.3);
		
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
		
		Point3D baseTriangleShear = getVertices().get(3).move(baseTriangle.getShear(), 0, 0);
		
		if (showBaseTriangleHeightLine) {
			l.add(Path.createLinePathDotted(getVertices().get(5), 
					baseTriangleShear));
		}
		if (showBaseTriangleHeightValue) {
			l.add(Path.createTextPath(Geom.formatMeasurement(baseTriangle.getHeight()), 
					Point3D.getMidPoint(baseTriangleShear, getVertices().get(5))
					, Path.SHIFT_DOWN,Path.SHIFT_RIGHT));
		}
		if (showBaseTriangleBaselineValue) {
			l.add(Path.createTextPath(Geom.formatMeasurement(baseTriangle.getBaseLine()), 
					Point3D.getMidPoint(getVertices().get(3), getVertices().get(4))
					, Path.SHIFT_DOWN,Path.SHIFT_NONE));
		}
		if (showPrismHeight) {
			l.add(Path.createTextPath(Geom.formatMeasurement(prismHeight), 
					Point3D.getMidPoint(getVertices().get(1), getVertices().get(4))
					, Path.SHIFT_DOWN,Path.SHIFT_RIGHT));
		}
		if (showVerticesLabel) {
			l.add(Path.createTextPath(String.valueOf("A"), 
					getVertices().get(0),
					Path.SHIFT_UP, Path.SHIFT_LEFT));
			l.add(Path.createTextPath(String.valueOf("B"),
					getVertices().get(1),
					Path.SHIFT_UP, Path.SHIFT_RIGHT));
			l.add(Path.createTextPath(String.valueOf("C"),
					getVertices().get(2),
					Path.SHIFT_UP, Path.SHIFT_NONE));
			l.add(Path.createTextPath(String.valueOf("D"),
					getVertices().get(3),
					Path.SHIFT_DOWN, Path.SHIFT_LEFT));
			l.add(Path.createTextPath(String.valueOf("E"), 
					getVertices().get(4),
					Path.SHIFT_DOWN, Path.SHIFT_RIGHT));
			l.add(Path.createTextPath(String.valueOf("F"),
					getVertices().get(5),
					Path.SHIFT_DOWN, Path.SHIFT_NONE));
		}
		
		return l;
	}
}
