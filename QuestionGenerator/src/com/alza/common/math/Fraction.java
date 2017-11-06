package com.alza.common.math;

import java.text.DecimalFormat;
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
	public boolean isLessThan(Fraction f){
		if (this.compareTo(f)<0){
			return true;
		}
		return false;
	}
	public boolean isBetween(Fraction f1, Fraction f2){
		if (this.compareTo(f1)>=0&&this.compareTo(f2)<=0){
			return true;
		}
		return false;
	}
	public Fraction getSimplestForm(){
		int gcd = MathUtils.findGCDDjikstra(this.a, this.b);
		Fraction s = new Fraction(this.a/gcd,this.b/gcd);
		return s;
	}
	public void add(Fraction f){
		int newA1 = this.a * f.b;
		int newA2 = f.a * this.b;
		int denom = f.b * this.b;
		Fraction r = new Fraction(newA1+newA2, denom);
		r = r.getSimplestForm();
 		this.a = r.a;
 		this.b = r.b;
	}
	public void subtract(Fraction f){
		Fraction negF = new Fraction(f.a* -1, f.b);
		add(negF);
	}
	public Fraction inverse(){
		return new Fraction(this.b,this.a);
	}
	public void multiplyBy(Fraction f){
		int newA = this.a * f.a;
		int newB = this.b * f.b;
		Fraction r = new Fraction(newA, newB);
		r = r.getSimplestForm();
		this.a = r.a;
		this.b = r.b;
	}
	public void multiplyBy(int i){
		this.a = this.a * i;
	}
	public void divideBy(Fraction f){
		Fraction divtomult = new Fraction(f.b, f.a);
		this.multiplyBy(divtomult);
	}
	
	@Override
	public Fraction clone(){
		Fraction clone = new Fraction(this.a, this.b);
		return clone;
	}
	public Fraction getResultWhenAddedWith(Fraction f){
		Fraction fOri = this.clone();
		fOri.add(f);
		return fOri;
	}
	public Fraction getResultWhenSubtractWith(Fraction f){
		Fraction fOri = this.clone();
		fOri.subtract(f);
		return fOri;
	}
	public Fraction getResultWhenMultipliedBy(Fraction f){
		Fraction fOri = this.clone();
		fOri.multiplyBy(f);
		return fOri;
	}
	public Fraction getResultWhenMultipliedBy(int i){
		Fraction fOri = this.clone();
		fOri.multiplyBy(i);
		return fOri;
	}
	public Fraction getResultWhenDividedBy(Fraction f){
		Fraction fOri = this.clone();
		fOri.divideBy(f);
		return fOri;
	}
	public MixedFraction getMixedFraction(){
		int x = this.a / this.b;
		int a = Math.abs(this.a % this.b);
		int b = this.b;
		MixedFraction c = new MixedFraction(x,a,b);
		return c;
		
	}
	public int getOneDigitInteger(){
		return this.a / this.b;
	}
	public String getTwoDigitDecimalForm(){
		double i2=((double)this.a)/((double)this.b);
		String s = new DecimalFormat("##.##").format(i2);
		return s;
	}
	public String getThreeDigitDecimalForm(){
		double i2=((double)this.a)/((double)this.b);
		String s = new DecimalFormat("##.###").format(i2);
		return s;
	}
	public String getPercentage(){
		double i2=((double)this.a)/((double)this.b);
		DecimalFormat df = new DecimalFormat("##.##%");
		return df.format(i2);
	}
	public String getPercentageNoDecimal(){
		double i2=((double)this.a)/((double)this.b);
		DecimalFormat df = new DecimalFormat("##%");
		return df.format(i2);
	}
	public boolean equals(Fraction f){
		Fraction f1 = this.getSimplestForm();
		Fraction f2 = f.getSimplestForm();
		if (f1.a!=f2.a){
			return false;
		}
		if (f1.b!=f2.b){
			return false;
		}
		return true;
	}
	public String toString(){
		String s = this.a+"/"+this.b;
		return s;
	}
	public String toHtmlString(){
		String s = this.a+"/"+this.b;
		return s;
	}
	public String toMathJaxString(){
		String s = "\\(\\frac{"+this.a+"}{"+this.b+"}\\)";
		return s;
	}
	public class MixedFraction {
		public int x;
		public int a;
		public int b;
		public MixedFraction(int x, int a, int b){
			this.x = x;
			this.a = a;
			this.b = b;
		}
		public String toString(){
			String s;
			if (this.x==0 && this.a==0){
				s="0";
			} else if (this.x==0){
				s = this.a+"/"+this.b;
			} else if (this.a==0){
				s = ""+this.x;
			} else if (this.b==0){
				s = ""+this.x;
			} else {
				s = this.x+" "+this.a+"/"+this.b;
			}
			
			return s;
		}
		public String toHtmlString(){
			String s = this.x+" "+this.a+"/"+this.b;
			return s;
		}
		public String toMathJaxString(){
			String s = this.x+new Fraction(a, b).toMathJaxString();
			return s;
		}
	}
}
