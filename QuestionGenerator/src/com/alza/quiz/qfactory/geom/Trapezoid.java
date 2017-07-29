package com.alza.quiz.qfactory.geom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.alza.common.math.MathUtils;

public class Trapezoid implements Shapes2D{
	private double topLength;
	private double bottomLength;
	private double height;
	private double shearLeft;
	
	public Trapezoid(){
		
	}
	public Trapezoid(double topLength, double bottomLength, double height, double shearLeft){
		this.topLength = topLength;
		this.bottomLength = bottomLength;
		this.height = height;
		this.shearLeft = shearLeft;
	}
	
	public double getTopLength() {
		return topLength;
	}

	public void setBottomLength(double length) {
		this.topLength = length;
	}

	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getShearLeft() {
		return shearLeft;
	}
	public void setShearLeft(double shearLeft) {
		this.shearLeft = shearLeft;
	}
	public double getShearRight() {
		double sr = Math.abs(bottomLength-topLength) - shearLeft;
		//return sr;
		return bottomLength - (topLength + shearLeft);
	}
	public double getLeftSlope(){
		double slope;
		double s = shearLeft;
		slope = Math.sqrt((s * s) + (height * height)) ; 
		return slope;
	}
	public double getRightSlope(){
		double slope;
		double s = getShearRight();
		slope = Math.sqrt((s * s) + (height * height)) ; 
		return slope;
	}
	
	@Override
	public double getArea() {
		return height * (topLength+bottomLength) * 0.5;
	}

	@Override
	public double getPerimeter() {
		return topLength + bottomLength + shearLeft + getShearRight();
	}
	@Override
	public int getReflectionalSymmetryCount() {
		return 0;
	}
	@Override
	public int getRotationalSymmetryCount() {
		return 0;
	}
	@Override
	public String getName() {
		return "trapezoid";
	}
	@Override
	public Shapes2D createExample() {
		int tl,h,sl,bl;
		do {
			tl = ThreadLocalRandom.current().nextInt(5, 26);
			h = ThreadLocalRandom.current().nextInt(5, 10);
			bl = ThreadLocalRandom.current().nextInt(5, 26);
			sl = ThreadLocalRandom.current().nextInt(5, 10);
			if (bl<tl) {
				sl = -1 * sl;
			}
		} while (tl==bl || sl>Math.abs(tl-bl));
		
		return new Trapezoid(tl, bl, h, sl);
	}
	@Override
	public double getOccupiedLength() {
		double dtm = Math.min(bottomLength, topLength);
		return dtm + Math.abs(shearLeft+getShearRight());
	}
	@Override
	public double getOccupiedHeight() {
		return height;
	}
	public List<Path> getPaths(int pxWidth, int pxHeight, int margin) {
		
		if (margin >= pxWidth/2 || margin >= pxHeight/2){
			margin = 0;
		}
		System.out.println("margin "+margin);
		List<Path> l = new ArrayList<Path>();
		int scale=0;
		double maxHeightRatio = ((double) (pxHeight-margin*2)) / ((double) getOccupiedHeight());
		double maxLengthRatio = ((double) (pxWidth-margin*2)) / ((double) getOccupiedLength());
		if (maxHeightRatio>maxLengthRatio){
			scale = (int) maxLengthRatio;
		} else {
			scale = (int) maxHeightRatio;
		}
		int pxTL = (int) (topLength * scale);
		int pxBL = (int) (bottomLength * scale);
		int pxH  = (int) (height * scale);
		int pxSh = (int) (shearLeft * scale);
		int pxShR = (int) (getShearRight() * scale);
		int pxOL = (int) (getOccupiedLength() * scale);
		System.out.println("Occupied length : "+pxOL);
		System.out.println("Mapped TBHSLR:  "+pxTL+","+pxBL+","+pxH+","+pxSh+","+pxShR);
		int x1,x2,x3,x4;
		int y1,y2,y3,y4;
		if (pxTL<pxBL) {
			x1 = ((pxWidth - pxOL)/2) + pxSh;
		} else {
			x1 = ((pxWidth - pxOL)/2);
		}
		x2 = x1 + pxTL;
		x4 = x1 + pxSh;
		x3 = x2 + pxBL;
		y1 = (pxHeight - pxH) / 2; 
		y2 = y1;
		y3 = y1 + pxH;
		y4 = y3;
		System.out.println("x1-x4 :  "+x1+","+x2+","+x3+","+x4);
		l.add(Path.createLinePath(new Point(x1, y1), new Point(x2, y2)));
		l.add(Path.createLinePath(new Point(x2, y2), new Point(x3, y3)));
		l.add(Path.createLinePath(new Point(x3, y3), new Point(x4, y4)));
		l.add(Path.createLinePath(new Point(x4, y4), new Point(x1, y1)));
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
		String s = "Trapezoid with "+this.topLength+" top length, "+
				this.bottomLength+" bottom length, "+
				this.height+ " height "
				+this.shearLeft+" shear left "+getShearRight()+" shear right";
		return s;
	}
}
