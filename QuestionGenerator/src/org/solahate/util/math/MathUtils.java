package org.solahate.util.math;
import java.util.ArrayList;
import java.util.List;
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
		   if(m == n)
		      return m;
		   else if (m > n)
		      return findGCDDjikstra(m-n, n);
		   else
		      return findGCDDjikstra(m, n-m);
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
}
