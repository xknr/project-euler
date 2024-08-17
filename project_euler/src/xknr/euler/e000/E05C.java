package xknr.euler.e000;

import xknr.euler.Solution;

public class E05C extends Solution
{
	public E05C(boolean doPrint) {
		super(doPrint);
	}

	public long solve(int hi, int[] primes)
	{	
		int prod = 1;

		// For each prime.
		for(int p: primes) 
		{
			// Multiply with largest power of p <= hi.
			prod *= largest(p, hi);
		}
		
		return prod;
	}

	/**
	 * Find largest power of d <= hi.
	 * @param d
	 * @param hi
	 * @return
	 */
	public static int largest(int d, int hi) 
	{
		int pow = 1;
		
		for(; pow <= hi; pow *= d) 
		{			
		}
		
		return pow / d;
	}

	public static final int[] PRIMES = {2,3,5,7,11,13,17,19};
}
