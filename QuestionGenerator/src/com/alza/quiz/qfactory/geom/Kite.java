package com.alza.quiz.qfactory.geom;

import java.util.concurrent.ThreadLocalRandom;

public class Kite implements Shapes2D{
	private double diagonalHoriz;
	private double diagonalVert;
	private double shear;
	public Kite(){
		
	}
	public Kite(double p, double q, double shear){
		this.diagonalHoriz = p;
		this.diagonalVert = q;
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
	public String getLocalName(String lang) {
		switch (lang) {
		case "EN":
			return "rhombus";
		case "ID":
			return "belah ketupat";
		default:
			break;
		}
		return null;
	}
	@Override
	public Shapes2D createExample() {
		int p,q,s;
		p = ThreadLocalRandom.current().nextInt(5, 26);
		q = ThreadLocalRandom.current().nextInt(5, 26);
		do {
			s = ThreadLocalRandom.current().nextInt(5, 26);
		} while (s>=q/2);
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
	
	

}
