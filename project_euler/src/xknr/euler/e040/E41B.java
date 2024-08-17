package xknr.euler.e040;


import java.util.Arrays;
import java.util.Comparator;
import java.util.function.IntConsumer;

import xknr.euler.Solution;
import xknr.euler.e020.E24;
import xknr.euler.miller.MillerRabin3;
import xknr.euler.util.Util;

public class E41B extends Solution
{
	public E41B(boolean doPrint) {
		super(doPrint);
	}

	/*
	 * All 9-digit and 8-digit permutations can be divided by 3.
	 * Search in 7-digit ones.
	 */
	public long solve()
	{	
		// Start from last permuation and go back.
		// The first prime number we find, is the result.
		
		// Starting digits. 
		int [] arr = new int[ARRAY_LEN];
		
		// 7 6 5 4 3 2 1
		Arrays.setAll(arr, i -> arr.length - i);

		do 
		{
			// Interpret current digits as a single number.  
			int p = (int)Util.read(arr, 0, arr.length);
			
			// Check for primality.
			if (MillerRabin3.isPrime(p)) 
			{
				return p;
			}
			
			// Coninue to next (previous) lex. perm.
		} while(Util.nextPerm(arr, Util.reverseComp));

		return 0; 
	}
	
	
	private static final int ARRAY_LEN = 7;
}
