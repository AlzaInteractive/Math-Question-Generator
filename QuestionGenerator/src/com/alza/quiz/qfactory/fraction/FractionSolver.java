package com.alza.quiz.qfactory.fraction;

import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import com.alza.common.math.Fraction;
import com.alza.common.math.MathUtils;

public class FractionSolver {
	ResourceBundle bundle;
	Locale loc;
	public FractionSolver() {
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	public FractionSolver(Locale loc) {
		this.loc = loc;
		initStringFromLocale();
	}
	private void initStringFromLocale(){
		bundle = ResourceBundle.getBundle("lang.fraction-solver", loc);
	}
	public List<String> simplify(Fraction f) {
		List<String> result = new ArrayList<>();
		int gcd = MathUtils.findGCD(f.a,f.b);
		// check if numerator/denominator equals 0 
		if (f.a==0 || f.b==0) {
			result.add(bundle.getString("zeroemptynum"));
			return result;
		}
		String line1 = bundle.getString("gcdof")+" "
				+f.a +" "+bundle.getString("and")+" "
				+f.b +" "+bundle.getString("is")+" "+gcd;
		String gcdnum = "(" +f.a+"\u00f7"+gcd+")";
		String gcddiv = "(" +f.b+"\u00f7"+gcd+")";
		String line2 = f.toHtmlString()+ " = "+ gcdnum +"/"+ gcddiv; 
		String line3 = f.toHtmlString()+ " = "+ f.getSimplestForm().toHtmlString();
		String line4 = bundle.getString("simplestformof")+" "+f.toHtmlString()+ " "+ bundle.getString("is") +" "+ f.getSimplestForm().toHtmlString();
		result.add(line1);
		result.add(line2);
		result.add(line3);
		result.add(line4);
		return result;
	}
	public List<String> add(Fraction f1,Fraction f2) {
		List<String> result = new ArrayList<>();
		// check if numerator/denominator equals 0
		if (f1.a==0 || f1.b==0 || f2.a==0 || f2.b==0) {
			result.add(bundle.getString("zeroemptynum"));
			return result;
		}

		int lcm = MathUtils.findLCM(f1.b,f2.b);
		int div1 = lcm / f1.b ;
		int div2 = lcm / f2.b;
		Fraction equi1 = new Fraction(f1.a*div1,lcm);
		Fraction equi2 = new Fraction(f2.a*div2,lcm);
		Fraction fresult = new Fraction(equi1.a+equi2.a,lcm);

		String line1 = bundle.getString("lcmofdivisor")+" "
				+f1.b +" "+bundle.getString("and")+" "
				+f2.b +" "+bundle.getString("is")+" "+lcm;
		//String line2 = bundle.getString("multiplierfor")+" "+f1.toHtmlString()+" "
		//		+bundle.getString("is")+" "+lcm+":"+f1.b+" = "+div1;
		String line3 = bundle.getString("equivalent") +" "+ f1.toHtmlString()+" = " 
				+ "(" +f1.a+" x "+div1+")"
				+"/"
				+ "(" +f1.b+" x "+div1+")"
				+ " = "+equi1.toHtmlString();
		//String line4 = bundle.getString("multiplierfor")+" "+f2.toHtmlString()+" "
		//		+bundle.getString("is")+" "+lcm+":"+f2.b+" = "+div2;
		String line5 = bundle.getString("equivalent") +" "+ f2.toHtmlString()+" = " 
				+ "(" +f2.a+" x "+div2+")"
				+"/"
				+ "(" +f2.b+" x "+div2+")"
				+ " = "+equi2.toHtmlString();
		String line6 = bundle.getString("finally")
				+ " "+f1.toHtmlString()+" + "+ f2.toHtmlString()
				+ " = " + equi1.toHtmlString()+" + "+equi2.toHtmlString()
				+ " = " + fresult.toHtmlString();
		result.add(line1);
		//result.add(line2);
		result.add(line3);
		//result.add(line4);
		result.add(line5);
		result.add(line6);
		return result;
	}
	public List<String> subtract(Fraction f1,Fraction f2) {
		List<String> result = new ArrayList<>();
		// check if numerator/denominator equals 0
		if (f1.a==0 || f1.b==0 || f2.a==0 || f2.b==0) {
			result.add(bundle.getString("zeroemptynum"));
			return result;
		}		


		int lcm = MathUtils.findLCM(f1.b,f2.b);
		int div1 = lcm / f1.b ;
		int div2 = lcm / f2.b;
		Fraction equi1 = new Fraction(f1.a*div1,lcm);
		Fraction equi2 = new Fraction(f2.a*div2,lcm);
		Fraction fresult = new Fraction(equi1.a-equi2.a,lcm);

		String line1 = bundle.getString("lcmofdivisor")+" "
				+f1.b +" "+bundle.getString("and")+" "
				+f2.b +" "+bundle.getString("is")+" "+lcm;
		//String line2 = bundle.getString("multiplierfor")+" "+f1.toHtmlString()+" "
		//		+bundle.getString("is")+" "+lcm+":"+f1.b+" = "+div1;
		String line3 = bundle.getString("equivalent") +" "+ f1.toHtmlString()+" = " 
				+ "(" +f1.a+" x "+div1+")"
				+"/"
				+ "(" +f1.b+" x "+div1+")"
				+ " = "+equi1.toHtmlString();
		//String line4 = bundle.getString("multiplierfor")+" "+f2.toHtmlString()+" "
		//		+bundle.getString("is")+" "+lcm+":"+f2.b+" = "+div2;
		String line5 = bundle.getString("equivalent") +" "+ f2.toHtmlString()+" = " 
				+ "(" +f2.a+" x "+div2+")"
				+"/"
				+ "(" +f2.b+" x "+div2+")"
				+ " = "+equi2.toHtmlString();
		String line6 = bundle.getString("finally")
				+ " "+f1.toHtmlString()+" - "+ f2.toHtmlString()
				+ " = " + equi1.toHtmlString()+" - "+equi2.toHtmlString()
				+ " = " + fresult.toHtmlString();
		result.add(line1);
		//result.add(line2);
		result.add(line3);
		//result.add(line4);
		result.add(line5);
		result.add(line6);
		return result;
	}
	public List<String> multiply(Fraction f1,Fraction f2) {
		List<String> result = new ArrayList<>();

		// check if numerator/denominator equals 0
		if (f1.a==0 || f1.b==0 || f2.a==0 || f2.b==0) {
			result.add(bundle.getString("zeroemptynum"));
			return result;
		}

		Fraction fresult = new Fraction(f1.a*f2.a,f1.b*f2.b);
		String line1 = bundle.getString("howmultiply.1");
		String line2 = bundle.getString("howmultiply.2");
		String line3 = f1.toHtmlString() +" x "+ f2.toHtmlString()+" = " 
				+ "(" +f1.a+" x "+f2.a+")"
				+"/"
				+ "(" +f1.b+" x "+f2.b+")"
				+ " = "+fresult.toHtmlString();
		result.add(line1);
		result.add(line2);
		result.add(line3);
		return result;
	}
	public List<String> divide(Fraction f1,Fraction f2) {
		List<String> result = new ArrayList<>();

		// check if numerator/denominator equals 0
		if (f1.a==0 || f1.b==0 || f2.a==0 || f2.b==0) {
			result.add(bundle.getString("zeroemptynum"));
			return result;
		}

		Fraction f2inverse = new Fraction (f2.b,f2.a);
		Fraction fresult = new Fraction(f1.a*f2.b,f1.b*f2.a);
		String line1 = bundle.getString("howdivide.1");
		String line2 = bundle.getString("howdivide.2");
		String line3 = f1.toHtmlString() +" \u00f7 "+ f2.toHtmlString()+" = "
				+ f1.toHtmlString() +" x "+ f2inverse.toHtmlString();
		String line4 = f1.toHtmlString() +" x "+ f2inverse.toHtmlString()+" = " 
				+ "(" +f1.a+" x "+f2inverse.a+")"
				+"/"
				+ "(" +f1.b+" x "+f2inverse.b+")"
				+ " = "+fresult.toHtmlString();
		result.add(line1);
		result.add(line2);
		result.add(line3);
		result.add(line4);
		return result;
	}
}
