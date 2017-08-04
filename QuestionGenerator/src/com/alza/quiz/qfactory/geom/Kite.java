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
		return l;
	}
	public String toString(){
		String s = "Kite with "+this.diagonalHoriz+" horizontal diagonal, "+this.diagonalVert+
				" vertical diagonal "+this.shear+" shear";
		return s;
	}
}
