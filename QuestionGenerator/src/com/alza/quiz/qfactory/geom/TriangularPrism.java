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
	public int getVerticesCount() {
		return 9;

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
	public double get2DOccupiedLength(double projRatio, double projAngle) {
		return baseTriangle.getBaseLine();
	}

	@Override
	public double get2DOccupiedHeight(double projRatio, double projAngle) {
		double addHeight = projRatio * baseTriangle.getLeftEdge() * Math.sin(projAngle);
		return addHeight+prismHeight;
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
	public List<Path> getPaths(int canvasWidth, int canvasHeight, int margin, double projRatio, double projAngle) {
		if (margin >= canvasWidth/2 || margin >= canvasHeight/2){
			margin = 0;
		}
		System.out.println("margin "+margin+ " OH "+ get2DOccupiedHeight(projRatio, projAngle));
		List<Path> l = new ArrayList<Path>();
		int scale=0;
		double maxHeightRatio = ((double) (canvasHeight-margin*2)) / ((double) get2DOccupiedHeight(projRatio, projAngle));
		double maxLengthRatio = ((double) (canvasWidth-margin*2)) / ((double) get2DOccupiedLength(projRatio, projAngle));
		if (maxHeightRatio>maxLengthRatio){
			scale = (int) maxLengthRatio;
		} else {
			scale = (int) maxHeightRatio;
		}
		int canvasTriangleBaseWidth = (int) (scale * baseTriangle.getBaseLine());
		int canvasPrismHeight = (int) (scale * prismHeight);
		int pxOl = (int) (scale * get2DOccupiedLength(projRatio, projAngle));
		int pxOh = (int) (scale * get2DOccupiedHeight(projRatio, projAngle));
		int x1,x2,x3,x4,x5,x6;
		int y1,y2,y3,y4,y5,y6;
		System.out.println("scale "+scale+" pxol "+pxOl+" pxoh "+pxOh+" pxCvOL "+canvasTriangleBaseWidth+" pxCvOH "+pxOh);
		x1 = (canvasWidth-pxOl)/2;
		x2 = x1 + (int) (projRatio * baseTriangle.getLeftEdge() * Math.cos(projAngle));
		x3 = x1 + canvasTriangleBaseWidth;
		x4 = x1; 
		x5 = x2;
		x6 = x3;
		
		
		y5 = (canvasHeight-pxOh)/2;
		y4 = y5 + (int) (projRatio * baseTriangle.getLeftEdge() * Math.sin(projAngle));
		y6 = y4;
		y2 = y5 + canvasPrismHeight;
		y1 = y4 + canvasPrismHeight;
		y3 = y1;
		l.add(Path.createLinePathDotted(new Point2D(x1, y1), new Point2D(x2, y2)));
		l.add(Path.createLinePathDotted(new Point2D(x2, y2), new Point2D(x3, y3)));
		l.add(Path.createLinePath(new Point2D(x3, y3), new Point2D(x1, y1)));
		
		l.add(Path.createLinePath(new Point2D(x1, y1), new Point2D(x4, y4)));
		l.add(Path.createLinePathDotted(new Point2D(x2, y2), new Point2D(x5, y5)));
		l.add(Path.createLinePath(new Point2D(x3, y3), new Point2D(x6, y6)));
		
		l.add(Path.createLinePath(new Point2D(x4, y4), new Point2D(x5, y5)));
		l.add(Path.createLinePath(new Point2D(x5, y5), new Point2D(x6, y6)));
		l.add(Path.createLinePath(new Point2D(x6, y6), new Point2D(x4, y4)));
		
		System.out.println(x1+" "+x2+" "+x3+" "+x4);
		return l;
	}

	@Override
	public List<Path> getPaths(int width, int height) {
		double defRatio = 1;
		double defAngle = Math.PI/4;
		int margin;
		if (width >= height) {
			margin = height / 4;
		} else margin = width / 4;
		return getPaths(width, height, margin, defRatio, defAngle);
	}
}
