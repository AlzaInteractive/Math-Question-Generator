package com.alza.quiz.model.geom.plane;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.alza.quiz.model.geom.Path;
import com.alza.quiz.model.geom.Point2D;
import com.alza.quiz.model.geom.Shapes2D;

public class Kite implements Shapes2D{
	private double diagonalHoriz;
	private double diagonalVert;
	private double shear;
	private boolean showVerticeLabel=true;
	private boolean showDiagonalLine=true;
	private boolean showDiagonalLength=true;
	private boolean showTopSlopeLength=true;
	private boolean showBottomSlopeLength=true;
	public Kite(){
		
	}
	
	@Override
	public void hideTextsAndMeasurements() {
		showVerticeLabel = false;
		showDiagonalLine = false;
		showDiagonalLength = false;
		showTopSlopeLength = false;
		showBottomSlopeLength = false;
	}
	
	public Kite(double diagHoriz, double diagVert, double shear){
		this.diagonalHoriz = diagHoriz;
		this.diagonalVert = diagVert;
		this.shear = shear;
	}
	public Kite(double bc,int topAngle, int bottomAngle) {
		this.shear = bc * Math.cos(Math.toRadians(((double)topAngle)/2));
		this.diagonalHoriz = 2 * bc * Math.sin(Math.toRadians(((double)topAngle)/2));
		this.diagonalVert = this.shear + 
				(this.diagonalHoriz/(2 * Math.tan(Math.toRadians(((double) bottomAngle)/2))));
	}
	public Kite(double topSlope, double bottomSlope, int topAngle) {
		// System.out.println(topSlope+" "+bottomSlope+" "+topAngle);
		double co = topSlope * Math.sin(Math.toRadians(((double)topAngle)/2));
		this.shear = topSlope * Math.cos(Math.toRadians(((double)topAngle)/2));
		this.diagonalHoriz = 2 * co;
		this.diagonalVert = this.shear+Math.sqrt(((bottomSlope * bottomSlope) - (co * co)));
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
	public boolean isShowVerticeLabel() {
		return showVerticeLabel;
	}
	public void setShowVerticeLabel(boolean showVerticeLabel) {
		this.showVerticeLabel = showVerticeLabel;
	}
	public boolean isShowDiagonalLine() {
		return showDiagonalLine;
	}
	public void setShowDiagonalLine(boolean showDiagonalLine) {
		this.showDiagonalLine = showDiagonalLine;
	}
	public boolean isShowDiagonalLength() {
		return showDiagonalLength;
	}
	public void setShowDiagonalLength(boolean showDiagonalLength) {
		this.showDiagonalLength = showDiagonalLength;
	}
	public boolean isShowTopSlopeLength() {
		return showTopSlopeLength;
	}

	public void setShowTopSlopeLength(boolean showTopSlopeLength) {
		this.showTopSlopeLength = showTopSlopeLength;
	}

	public boolean isShowBottomSlopeLength() {
		return showBottomSlopeLength;
	}

	public void setShowBottomSlopeLength(boolean showBottomSlopeLength) {
		this.showBottomSlopeLength = showBottomSlopeLength;
	}
	
	public double getTopSlope() {
		double topSlope = Math.sqrt((this.shear * this.shear) + (this.diagonalHoriz/2) * (this.getDiagonalHoriz()/2));
		return topSlope;
	}
	
	public double getBottomSlope() {
		double bs = this.diagonalVert - this.shear;
		double bottomSlope = Math.sqrt((bs * bs) + (this.diagonalHoriz/2) * (this.getDiagonalHoriz()/2));
		return bottomSlope;
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
		return 1;
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
		double p,q,s;
		do {
			p = ThreadLocalRandom.current().nextInt(5, 20); // d horiz
			q = ThreadLocalRandom.current().nextInt(5, 20); // d vert
			s = ThreadLocalRandom.current().nextInt(2, ((int)q)); // shear
		} while (s<(q/5)||s==q/2);
		System.out.println("d hor:"+p+" d vert:"+q+" shear:"+s);
		Kite k = new Kite(p, q, s);
		return k;
	}
	@Override
	public List<Point2D> getVertices() {
		List<Point2D> points = new ArrayList<Point2D>();
		Point2D a,b,c,d;
		a = new Point2D(0, shear);
		b = new Point2D(diagonalHoriz/2, 0);
		c = new Point2D(diagonalHoriz, shear);
		d = new Point2D(diagonalHoriz/2, diagonalVert);
		points.add(a);points.add(b);points.add(c);points.add(d);
		return points;
	}
	@Override
	public int getEdgeCount() {
		return 4;
	}
	@Override
	public List<Path> getPaths() {
		List<Path> l = new ArrayList<Path>();
		l.add(Path.createLinePath(getVertices().get(0), getVertices().get(1)));
		l.add(Path.createLinePath(getVertices().get(1), getVertices().get(2)));
		l.add(Path.createLinePath(getVertices().get(2), getVertices().get(3)));
		l.add(Path.createLinePath(getVertices().get(3), getVertices().get(0)));
		if (showDiagonalLine) {
			l.add(Path.createLinePathDashed(getVertices().get(0), getVertices().get(2)));
			l.add(Path.createLinePathDashed(getVertices().get(1), getVertices().get(3)));
		}
		if (showDiagonalLength) {
			l.add(Path.createTextPath(Geom.formatMeasurement(diagonalHoriz), 
					new Point2D(diagonalHoriz/3, shear),
					Path.SHIFT_UP,Path.SHIFT_NONE));
			l.add(Path.createTextPath(Geom.formatMeasurement(shear), new Point2D(diagonalHoriz/2, shear/2),
					Path.SHIFT_NONE,Path.SHIFT_RIGHT));
			l.add(Path.createTextPath(Geom.formatMeasurement(diagonalVert-shear), 
					new Point2D(diagonalHoriz/2, shear + (diagonalVert-shear)/2),
					Path.SHIFT_DOWN,Path.SHIFT_NONE));
		}
		if (showVerticeLabel) {
			l.add(Path.createTextPath(String.valueOf("A"), getVertices().get(0), Path.SHIFT_UP,Path.SHIFT_LEFT));
			l.add(Path.createTextPath(String.valueOf("B"), getVertices().get(1), Path.SHIFT_UP,Path.SHIFT_LEFT));
			l.add(Path.createTextPath(String.valueOf("C"), getVertices().get(2), Path.SHIFT_UP,Path.SHIFT_RIGHT));
			l.add(Path.createTextPath(String.valueOf("D"), getVertices().get(3), Path.SHIFT_DOWN,Path.SHIFT_LEFT));
		}
		if (showTopSlopeLength) {
			l.add(Path.createTextPath(Geom.formatMeasurement(getTopSlope()), 
					Point2D.getMidPoint(getVertices().get(1), getVertices().get(2)),
					Path.SHIFT_UP,Path.SHIFT_NONE));
		}
		if (showBottomSlopeLength) {
			l.add(Path.createTextPath(Geom.formatMeasurement(getBottomSlope()), 
					Point2D.getMidPoint(getVertices().get(2), getVertices().get(3)),
					Path.SHIFT_DOWN,Path.SHIFT_NONE));
		}
		
		return l;
	}
	public String toString(){
		String s = "Kite with "+this.diagonalHoriz+" horizontal diagonal, "+this.diagonalVert+
				" vertical diagonal "+this.shear+" shear";
		return s;
	}
}
