package xknr.euler.e000;

import xknr.euler.Solution;
import xknr.euler.util.Fib;
import xknr.euler.util.Util;

/**
 * Brute force.
 * Uses a generator class for fibonacci numbers.
 */
public class E02B extends Solution
{
	public E02B(boolean doPrint) {
		super(doPrint);
	}

	public int solve() 
	{
		int sum = 0;	
		
		Fib fib = new Fib();
				
		for(long term; (term = fib.adv()) < LIMIT; )
		{
			if (Util.isEven(term))
			{
				sum += term;
			}
		}
		
		return sum;
	}
	
	private static final int LIMIT = 4_000_000;
	
}
