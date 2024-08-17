package xknr.euler.e010;

import xknr.euler.util.FactorsFixed;

/**
 * Inherits solve from E12, changes divisorCount method.
 */
public class E12B extends E12
{
	public E12B(boolean doPrint) {
		super(doPrint);
	}

	/**
	 * Calculate the number of divisors for a given number.
	 * 
	 * Product of powers of distinct prime factors + 1
	 * 2^3 * 5^2 * 7^4 -> (3+1)(2+1)(4+1)
	 * 
	 * @param x The input number.
	 * @return Count of divisors.
	 */
	public int calcDivisorCount(int x)
	{
		int prod = 1;
	
		// Previously found prime factor.
		int prevP = -1;
		
		// Counter for same prime factors. 
		int counter = 0;		
		
		for(FactorsFixed fac = new FactorsFixed(x); fac.has(); counter++) 
		{
			int p = (int)(long)fac.adv();
			
			// If found a different prime factor than the previous one:
			if (p != prevP) 
			{
				// Now pCount is the count of occurence of p in x's prime factors.
				prod *= counter + 1;
				
				// Reset counter.
				counter = 0;
			}
			
			prevP = p;			
		}
		
		// Count for the last distinct prime factor.
		prod *= counter + 1;
		
		return prod;
	}
}
