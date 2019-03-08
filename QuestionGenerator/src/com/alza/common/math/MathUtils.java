package com.alza.common.math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;
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
	 * using djikstra's algorithm
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
	 * @param args array of integer
	 * @return lcm
	 */
	public static int findLCM(int...args){
		int lcm = args[0];
		for (int i = 1; i < args.length; i++){
			lcm = findLCM(lcm,args[i]);
		}
		return lcm;
	}
	/**
	 * Find divisors of a given Integer
	 * @param num the number 
	 * @return divisors in list
	 */
	public static List<Integer> findDivisors(int num){
		List<Integer> divisors = new ArrayList<>();
		divisors.add(1);
		for (int i = 2; i <= num / 2; i++) {
			if (num % i == 0) {
				divisors.add(i);
			}
		}
		divisors.add(num);
		return divisors;
	}
	/**
	 * Find prime factors of a given Integer 
	 * @param num
	 * @return prime factors in list
	 */
	public static List<Integer> findPrimeFactors(int num){
		List<Integer> primesF = new ArrayList<>();
		//divide until it's no longer even
        while (num%2==0) {
            num = num / 2;
            primesF.add(2);
        }
        // divide remaining value with odd numbers
        for (int i = 3; i <= Math.sqrt(num); i+= 2) {
            // While i divides n, print i and divide n
            while (num%i == 0)
            {
                num = num / i;
                primesF.add(i);
            }
        }
        // If there's any value remaining, it's a prime number
        if (num > 2) {
        	primesF.add(num);
        }
        return primesF;
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
	/**
	 * 
	 */
	public static FactorNode FactorTree(int number) {
		FactorNode root = new FactorNode(number);
		List<Integer> primes = findPrimeFactors(number);
		FactorNode curNode = root;
		int remainingNumber = number;
		for (Integer curNum : primes) {
			remainingNumber = remainingNumber / curNum;
			curNode.leftNode = new FactorNode(curNum);
			curNode.rightNode = new FactorNode(remainingNumber);
			curNode = curNode.rightNode;
		}
		return root;
	}
	/**
	 * 
	 * @param ints
	 * @return maximum integer from given array
	 */
	public static int findMax(int...ints) {
		int max = ints[0];
		for (int i = 1; i < ints.length; i++) {
			if (max<ints[i]) max = ints[i];
		}
		return max;
	}
	public static int[] sort(int...ints) {
		Arrays.sort(ints);
		return ints;
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
	/**
	 * 
	 * @return array of phytagorean triples:a,b,c
	 * using euclidean theorem where
	 * given two number m,n where m < n
	 * a = n2 - m2
	 * b = 2mn
	 * c = n2 + m2
	 * @param maxM = max value of m
	 * @param maxDistance = max distance between m and n
	 * */
	public static int[] generateRandomPythagoreanTriples(int maxDistance, int maxM) {
	    int m, n, a, b, c;
	    m = ThreadLocalRandom.current().nextInt(1, maxM);
	    n = ThreadLocalRandom.current().nextInt(m+1, m+maxDistance);		
	    a = (n*n) - (m*m);
	    b = 2*m*n;
	    c = m*m + n*n;
	    int[] triples = {a,b,c};
	    return triples;
	}
	/**
	 * 
	 * @return unicode division symbol ÷
	 */
	public static String UnicodeDivisionSymbol() {
		Character c = '\u00F7';
		return c.toString();
	}
}
