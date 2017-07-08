package com.alza.quiz.qfactory.geom;

import java.util.concurrent.ThreadLocalRandom;

public class Rhombus implements Shapes2D{
	private double diagonalHoriz;
	private double diagonalVert;
	public Rhombus(){
		
	}
	public Rhombus(double p, double q){
		this.diagonalHoriz = p;
		this.diagonalVert = q;
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
		return 2;
	}
	@Override
	public int getRotationalSymmetryCount() {
		return 2;
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
		int p,q;
		do {
			p = ThreadLocalRandom.current().nextInt(5, 26);
			q = ThreadLocalRandom.current().nextInt(5, 26);
		} while (p==q);
		Rhombus r = new Rhombus(p, q);
		return r;
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
