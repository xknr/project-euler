package xknr.euler.e000;

import xknr.euler.Solution;

/**
 * Exhaust all prime factors by repeatedly trying 
 * to divide by increasing candidates. 
 */
public class E03 extends Solution
{
	public E03(boolean doPrint) {
		super(doPrint);
	}

	public long solve(long x)
	{		
		long p = 2;
		
		while(x > 1) 
		{
			long d = x / p;
			
			if (d * p == x) 
			{
				// Found a prime factor:
				x = d;
			}
			else 
			{
				// Next number in 2,3,5,7,9,...
				p += 1 + (p & 1); 
			}
		}
		
		return p;
	}
}
