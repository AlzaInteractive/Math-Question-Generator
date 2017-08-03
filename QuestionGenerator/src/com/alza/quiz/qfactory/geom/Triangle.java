package com.alza.quiz.qfactory.geom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Triangle implements Shapes2D{
	private double baseLine,height,shear;
	public static final int SCALENE = 1;
	public static final int ISCOSCELES = 2;
	public static final int EQUILATERAL = 3;
	public Triangle() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * @param baseLine 
	 * @param height, orthogonal
	 * @param shear
	 */
	public Triangle(double baseLine, double height, double shear) {
		super();
		this.baseLine = baseLine;
		this.height = height;
		this.shear = shear;
	}
	
	public double getBaseLine() {
		return baseLine;
	}
	public void setBaseLine(double baseLine) {
		this.baseLine = baseLine;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getShear() {
		return shear;
	}
	public void setShear(double shear) {
		this.shear = shear;
	}
	@Override
	public double getArea() {
		return baseLine * height / 2;
	}
	
	public double getLeftEdge() {
		return Math.sqrt((height*height)+(shear*shear));
	}
	
	public double getRightEdge() {
		return Math.sqrt((height*height)+((baseLine-shear)*(baseLine-shear)));
	}
	
	public int getType() {
		if (baseLine==getLeftEdge()||baseLine==getRightEdge()) {
			if (getLeftEdge()==getRightEdge()) {
				return EQUILATERAL;
			}
			return ISCOSCELES;
		}
		return SCALENE;
	}
	

	@Override
	public double getPerimeter() {
		return baseLine + getLeftEdge() + getRightEdge();
	}
	

	@Override
	public int getReflectionalSymmetryCount() {
		switch (getType()) {
		case EQUILATERAL:
			return 3;
		case ISCOSCELES:
			return 1;
		default:
			return 0;
		}
		
	}

	@Override
	public int getRotationalSymmetryCount() {
		switch (getType()) {
		case EQUILATERAL:
			return 3;
		default:
			return 0;
		}
	}

	@Override
	public double getOccupiedLength() {
		return baseLine;
	}

	@Override
	public double getOccupiedHeight() {
		return height;
	}

	@Override
	public String getName() {
		return "triangle";
	}

	@Override
	public Shapes2D createExample() {
		int bs = ThreadLocalRandom.current().nextInt(5, 26);
		int ht = ThreadLocalRandom.current().nextInt(5, 26);
		int sh = ThreadLocalRandom.current().nextInt(0, bs+1);
		return new Triangle(bs, ht, sh);
	}

	@Override
	public List<Path> getPaths(int pxWidth, int pxHeight, int margin) {
		if (margin >= pxWidth/2 || margin >= pxHeight/2){
			margin = 0;
		}
		List<Path> l = new ArrayList<Path>();
		int scale=0;
		double maxHeightRatio = ((double) (pxHeight-margin*2)) / ((double) getOccupiedHeight());
		double maxLengthRatio = ((double) (pxWidth-margin*2)) / ((double) getOccupiedLength());
		if (maxHeightRatio>maxLengthRatio){
			scale = (int) maxLengthRatio;
		} else {
			scale = (int) maxHeightRatio;
		}
		System.out.println("scale  "+scale);
		int pxBs = (int) (baseLine * scale);
		int pxH  = (int) (height * scale);
		int pxSh  = (int) (shear * scale);
		System.out.println("Mapped bs h sh:  "+pxBs+","+pxH+","+pxSh);
		int x1,x2,x3;
		int y1,y2,y3;
		x1 = (pxWidth-pxBs)/2; 
		x2 = x1 + pxSh;
		x3 = x1+pxBs;
		y2 = (pxHeight - pxH) / 2; 
		y1 = y2+pxH;
		y3 = y1;
		l.add(Path.createLinePath(new Point2D(x1, y1), new Point2D(x2, y2)));
		l.add(Path.createLinePath(new Point2D(x2, y2), new Point2D(x3, y3)));
		l.add(Path.createLinePath(new Point2D(x3, y3), new Point2D(x1, y1)));
		return l;
	}

	@Override
	public List<Path> getPaths(int width, int height) {
		int margin;
		if (width >= height) {
			margin = height / 5;
		} else margin = width / 5;
		return getPaths(width, height, margin);
	}
	public String toString(){
		String s = "Triangle with "+this.baseLine+" base"+this.height+" height"+this.shear+" shear";
		return s;
	}

}
