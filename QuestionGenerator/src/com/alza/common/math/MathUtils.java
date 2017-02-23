package com.alza.common.math;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
/**
 * 
 * @author ewin.sutriandi@gmail.com
 * Various simple math algorithms
 * AZ
 *
 */
public class MathUtils {
	/** 
	 * Find greatest common denominator of two int
	 * using euclidean algorithm
	 * @param a first integer
	 * @param b second integer
	 * @return gcd
	 */
	public static int findGCDEuclid(int a, int b) { 
		if(b == 0){ 
			return a; 
		} 
		return findGCDEuclid(b, a%b); 
	}
	/** 
	 * Find greatest common denominator of two int
	 * using euclidean algorithm
	 * @param a first integer
	 * @param b second integer
	 * @return gcd
	 */
	public static int findGCDDjikstra(int m, int n) {
		if (m==0){
			return n;
		}
		if (n==0){
			return m;
		}
		m = Math.abs(m);
		n = Math.abs(n);
		if(m == n)
			return m;
		else if (m > n)
			return findGCDDjikstra(m-n, n);
		else
			//System.out.println(m+"  "+n);
			return findGCDDjikstra(m, n-m);
	}
	public static int findGCD(int...args){
		int gcd = args[0];
		for (int i = 1; i < args.length; i++) {
			gcd = findGCDDjikstra(gcd, args[i]);
		}
		return gcd;
	}
	
	/**
	 * Find least common multiple of two int
	 * @param a first integer
	 * @param b second integer
	 * @return lcm
	 */
	public static int findLCM(int a, int b)
	{
		return a * (b / findGCDDjikstra(a, b));
	}

	/**
	 * @param arr array of integer
	 * @return lcm
	 */
	public static int findLCM(int[] arr){
		int lcm = arr[0];
		for (int i = 1; i < arr.length; i++){
			lcm = findLCM(lcm,arr[i]);
		}
		return lcm;
	}
	/**
	 * Find divisors of an Integer
	 * @param num the number 
	 * @return divisors in list
	 */
	public static List<Integer> findDivisors(int num){
		List<Integer> divisors = new ArrayList<>();
		for (int i = 2; i <= num / 2; i++) {
			if (num % i == 0) {
				divisors.add(i);
			}
		}
		return divisors;
	}
	/**
	 * Find greatest fraction
	 * @param fracs array of Fraction
	 * @return greatest Fraction from array fracs
	 */
	public static Fraction findGreatest(Fraction[] fracs){
		if (fracs.length>0){
			Fraction f = fracs[0];
			for (Fraction fr : fracs) {
				if (fr.isGreaterThan(f)){
					f = fr;
				}
			}
			return f;
		}
		return null;
	}
	
	private final static TreeMap<Integer, String> romansMap = new TreeMap<Integer, String>();
	static {
		romansMap.put(1000, "M");
		romansMap.put(900, "CM");
		romansMap.put(500, "D");
		romansMap.put(400, "CD");
		romansMap.put(100, "C");
		romansMap.put(90, "XC");
		romansMap.put(50, "L");
		romansMap.put(40, "XL");
		romansMap.put(10, "X");
		romansMap.put(9, "IX");
		romansMap.put(5, "V");
		romansMap.put(4, "IV");
		romansMap.put(1,"I");
	}
	/**
	 * Find equivalent roman numerals of integer
	 * @param num
	 * @return roman numerals of integer num
	 */
	public static String toRomanNumeral(int num){
		int l = romansMap.floorKey(num);
		if (num == l){
			return romansMap.get(num);
		} 
		return romansMap.get(l) + toRomanNumeral(num -l);
	}
}
