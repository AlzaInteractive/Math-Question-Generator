package com.alza.common.math;

import java.util.Comparator;

public class Fraction implements Comparable<Fraction>,Comparator<Fraction>{
	public int a;
	public int b;
	public Fraction(int a, int b){
		this.a = a;
		this.b = b;
	}
	@Override
	public int compareTo(Fraction o) {
		int leftSide = this.a * o.b;
		int rightSide = this.b * o.a;
		return leftSide - rightSide;
	}
	@Override
	public int compare(Fraction o1, Fraction o2) {
		return o1.compareTo(o2);
	}
	public boolean isGreaterThan(Fraction f){
		if (this.compareTo(f)>0){
			return true;
		}
		return false;
	}
	

}
