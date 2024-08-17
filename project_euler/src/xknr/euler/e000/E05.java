package xknr.euler.e000;

import xknr.euler.Solution;
import xknr.euler.util.Util;

public class E05 extends Solution
{
	public E05(boolean doPrint) {
		super(doPrint);
	}

	public long solve(int hi)
	{
		long prod = 1;
		
		for(int i = 2; i <= hi; i++) 
		{
			// prod is always the smallest number which can be
			// divided by all values of i seen so far.
						
			prod *= i / Util.gcd(prod, i);
		}
		
		return prod;
	}
}
