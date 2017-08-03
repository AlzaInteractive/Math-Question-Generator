package com.alza.quiz.qfactory.geom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Kite implements Shapes2D{
	private double diagonalHoriz;
	private double diagonalVert;
	private double shear;
	public Kite(){
		
	}
	public Kite(double diagHoriz, double diagVert, double shear){
		this.diagonalHoriz = diagHoriz;
		this.diagonalVert = diagVert;
		this.shear = shear;
	}
	public double getDiagonalHoriz() {
		return diagonalHoriz;
	}
	public void setDiagonalHoriz(double diagonalHoriz) {
		this.diagonalHoriz = diagonalHoriz;
	}
	public double getDiagonalVert() {
		return diagonalVert;
	}
	public void setDiagonalVert(double diagonalVert) {
		this.diagonalVert = diagonalVert;
	}
	public double getShear() {
		return shear;
	}
	public void setShear(double shear) {
		this.shear = shear;
	}
	@Override
	public double getArea() {
		return diagonalHoriz * diagonalVert / 2;
	}

	@Override
	public double getPerimeter() {
		return 2 * Math.sqrt((diagonalHoriz * diagonalHoriz) + (diagonalVert * diagonalVert));
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
		return "kite";
	}
	@Override
	public Shapes2D createExample() {
		int p,q,s;
		do {
			p = ThreadLocalRandom.current().nextInt(5, 26);
			q = ThreadLocalRandom.current().nextInt(5, 26);
			s = ThreadLocalRandom.current().nextInt(5, 26);
		} while (s>=q||s==q/2);
		Kite k = new Kite(p, q, s);
		return k;
	}
	@Override
	public double getOccupiedLength() {
		return diagonalHoriz;
	}
	@Override
	public double getOccupiedHeight() {
		return diagonalVert;
	}
	public List<Path> getPaths(int pxWidth, int pxHeight, int margin) {
		if (margin >= pxWidth/2 || margin >= pxHeight/2){
			margin = 0;
		}
		List<Path> l = new ArrayList<Path>();
		int scale=0;
		System.out.println("margin "+margin);
		double maxHeightRatio = ((double) (pxHeight-margin*2)) / ((double) getOccupiedHeight());
		double maxLengthRatio = ((double) (pxWidth-margin*2)) / ((double) getOccupiedLength());
		if (maxHeightRatio>maxLengthRatio){
			scale = (int) maxLengthRatio;
		} else {
			scale = (int) maxHeightRatio;
		}
		int pxDH = (int) (scale * diagonalHoriz);
		int pxDV = (int) (scale * diagonalVert);
		int pxSh = (int) (scale * shear);
		System.out.println("Mapped DHVS:  "+pxDH+","+pxDV+","+pxSh);
		int x1,x2,x3,x4;
		int y1,y2,y3,y4;
		x1 = (pxWidth - pxDH) / 2; 
		x2 = x1 + ( pxDH / 2);
		x3 = x1 + pxDH; 
		x4 = x2;
		y2 = (pxHeight - pxDV) /2 ;
		y1 = y2 + pxSh;
		y3 = y1;
		y4 = y2 + pxDV;
		l.add(Path.createLinePath(new Point2D(x1, y1), new Point2D(x2, y2)));
		l.add(Path.createLinePath(new Point2D(x2, y2), new Point2D(x3, y3)));
		l.add(Path.createLinePath(new Point2D(x3, y3), new Point2D(x4, y4)));
		l.add(Path.createLinePath(new Point2D(x4, y4), new Point2D(x1, y1)));
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
		String s = "Kite with "+this.diagonalHoriz+" horizontal diagonal, "+this.diagonalVert+
				" vertical diagonal "+this.shear+" shear";
		return s;
	}
}
