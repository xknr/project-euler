package xknr.euler.e000;

import xknr.euler.Solution;

/**
 * Exhaust all prime factors by repeatedly trying 
 * to divide by increasing candidates. 
 * 
 * We can check if d < p and terminate early. 
 */
public class E03B extends Solution
{	
	public E03B(boolean doPrint) {
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
			else if (d < p) 
			{
				// No more prime factors are possible and 
				// current value of x is the last prime factor.
				p = x;
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
