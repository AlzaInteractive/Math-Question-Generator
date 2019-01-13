package com.alza.quiz.model.geom.solid;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.alza.quiz.model.geom.Path;
import com.alza.quiz.model.geom.Point3D;
import com.alza.quiz.model.geom.Shapes2D;
import com.alza.quiz.model.geom.Shapes3D;
import com.alza.quiz.model.geom.plane.Geom;
import com.alza.quiz.model.geom.plane.Rectangle;
import com.alza.quiz.model.geom.plane.Triangle;

public class Pyramid implements Shapes3D{
	private Rectangle baseRectangle;
	private double height;
	private boolean showVerticeLabels = true;
	private boolean showBaseMeasurements = true;
	private boolean showHeightLine = true;
	private boolean showBaseDiagonals = false;
	private boolean showHeightLength = true;
	public Pyramid() {
		
	}
	
	public Pyramid(Rectangle base, double height) {
		super();
		this.baseRectangle = base;
		this.height = height;
	}

	public void setBaseRectangle(Rectangle baseTriangle) {
		this.baseRectangle = baseTriangle;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public boolean isShowVerticeLabels() {
		return showVerticeLabels;
	}

	public void setShowVerticeLabels(boolean showVerticeLabels) {
		this.showVerticeLabels = showVerticeLabels;
	}

	public boolean isShowBaseMeasure() {
		return showBaseMeasurements;
	}

	public void setShowBaseMeasure(boolean showBaseMeasure) {
		this.showBaseMeasurements = showBaseMeasure;
	}

	public boolean isShowHeightLine() {
		return showHeightLine;
	}

	public void setShowHeightLine(boolean showHeightLine) {
		this.showHeightLine = showHeightLine;
	}

	public boolean isShowBaseDiagonals() {
		return showBaseDiagonals;
	}

	public void setShowBaseDiagonals(boolean showBaseDiagonals) {
		this.showBaseDiagonals = showBaseDiagonals;
	}

	public boolean isShowHeightLength() {
		return showHeightLength;
	}

	public void setShowHeightLength(boolean showHeightLength) {
		this.showHeightLength = showHeightLength;
	}

	public Rectangle getBaseRectangle() {
		return baseRectangle;
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
		return baseRectangle.getArea() * height / 3;
	}

	@Override
	public List<Shapes2D> getFaces() {
		List<Shapes2D> fcs = new ArrayList<Shapes2D>();
		double lTrHeight = Math.sqrt((baseRectangle.getWidth()*baseRectangle.getWidth()/4)+(height*height));
		double wTrHeight = Math.sqrt((baseRectangle.getLength()*baseRectangle.getLength()/4)+(height*height));
		Triangle trL = new Triangle(baseRectangle.getLength(),lTrHeight);
		Triangle trW = new Triangle(baseRectangle.getWidth(),wTrHeight);
		fcs.add(baseRectangle);
		fcs.add(trL);
		fcs.add(trL);
		fcs.add(trW);
		fcs.add(trW);
		return fcs;
	}
	@Override
	public String getName() {
		return "pyramid";
	}

	@Override
	public Shapes3D createExample() {
		Rectangle r;
		int height;
		do {
			r = (Rectangle) new Rectangle().createExample();
			height = ThreadLocalRandom.current().nextInt(5, 26);
		} while (height * 0.60 < r.getLength());
		return new Pyramid(r, height);
	}

	@Override
	public List<Point3D> getVertices() {
		List<Point3D> points = new ArrayList<Point3D>();
		Point3D a,b,c,d,e;
		
		a = new Point3D(0, height, 0);
		b = new Point3D(0, height, this.baseRectangle.getWidth());
		c = new Point3D(this.baseRectangle.getLength(), height , this.baseRectangle.getWidth());
		d = new Point3D(this.baseRectangle.getLength(), height , 0);
		e = new Point3D(this.baseRectangle.getLength()/2, 0 , this.baseRectangle.getWidth()/2);
		points.add(a);points.add(b);points.add(c);points.add(d);
		points.add(e);
		return points;
	}
	@Override
	public int getEdgeCount() {
		return 8;
	}
	@Override
	public List<Path> getPaths() {
		List<Path> l = new ArrayList<Path>();
		
		l.add(Path.createLinePathDotted(getVertices().get(0), getVertices().get(1)));
		l.add(Path.createLinePathDotted(getVertices().get(1), getVertices().get(2)));
		l.add(Path.createLinePath(getVertices().get(2), getVertices().get(3)));
		l.add(Path.createLinePath(getVertices().get(3), getVertices().get(0)));
		
		l.add(Path.createLinePath(getVertices().get(0), getVertices().get(4)));
		l.add(Path.createLinePathDotted(getVertices().get(1), getVertices().get(4)));
		l.add(Path.createLinePath(getVertices().get(2), getVertices().get(4)));
		l.add(Path.createLinePath(getVertices().get(3), getVertices().get(4)));
		
		Point3D baseCenter = Point3D.getMidPoint(getVertices().get(1),
					getVertices().get(3));
		
		if (showBaseMeasurements) {
			l.add(Path.createTextPath(Geom.formatMeasurement(baseRectangle.getLength()), 
					Point3D.getMidPoint(getVertices().get(0),getVertices().get(3)),
					Path.SHIFT_DOWN, Path.SHIFT_NONE));
			l.add(Path.createTextPath(Geom.formatMeasurement(baseRectangle.getWidth()), 
					Point3D.getMidPoint(getVertices().get(2),getVertices().get(3)),
					Path.SHIFT_NONE, Path.SHIFT_RIGHT));
		}
		if (showBaseDiagonals) {
			l.add(Path.createLinePathDotted(getVertices().get(1), getVertices().get(3)));
			l.add(Path.createLinePathDotted(getVertices().get(0), getVertices().get(2)));
		}
		if (showHeightLine) {
			l.add(Path.createLinePathDotted(getVertices().get(4), baseCenter));
		}
		if (showHeightLength) {
			l.add(Path.createTextPath(Geom.formatMeasurement(height), 
					baseCenter.move(0, -height/3, 0),
					Path.SHIFT_UP, Path.SHIFT_LEFT));
		}
		
		
		if (showVerticeLabels) {
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
		}
		
		return l;
	}
}
