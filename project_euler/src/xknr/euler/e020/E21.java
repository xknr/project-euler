package xknr.euler.e020;

import xknr.euler.Solution;
import xknr.euler.util.Util;

/**
 * 
 * https://oeis.org/wiki/Sum_of_divisors_function
 * (Not sure why this is related)
 * 
 */
public class E21 extends Solution
{
	public E21(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{
		int sumAll = 0;
		
		int[] cached = new int[LIM];
		
		for(int n = 2; n < LIM; n++)
		{			
			// We call our common function for this.
			int s = cached[n] = (int)Util.sumOfDivisors(n) - n;
			
			// Check if the reverse is true.
			// If s is the smaller one, we must have recorded it.
			// Notice that we didn't say s <= n bc they can't be equal.			
			if (s < n && cached[s] == n) {
				// Add both numbers.
				sumAll += n + s;
			}
		}
		
		return sumAll;
	}
	
	private static final int LIM = 10000;
}

