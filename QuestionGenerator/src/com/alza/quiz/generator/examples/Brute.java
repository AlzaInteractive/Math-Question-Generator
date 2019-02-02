package com.alza.quiz.generator.examples;

import com.alza.common.math.MathUtils;

public class Brute {
	
	public static void main(String[] args) {
		long start = System.nanoTime();
		int min = -100000;
		int max = 100000;
		int k = 0;
		for (int i = min; i < max; i++) {
			for (int j = min; j < max; j++) {
				int gcd = MathUtils.findGCDDjikstra(i, j);
				System.out.println("i="+i+"j="+j+", #"+k+", gcd="+gcd);
				k++;
			}
		}
		long end = System.nanoTime();
		long elapsed = end - start;
		System.out.println("Took: " + (elapsed / 1000000000) + "s");
	}

}
