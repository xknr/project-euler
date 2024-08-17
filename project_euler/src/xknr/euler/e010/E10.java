package xknr.euler.e010;

import xknr.euler.Solution;

public class E10 extends Solution
{	
	public E10(boolean doPrint) {
		super(doPrint);
	}

	public long solve(int lim)
	{
		// Inclusive.
		int maxPrime = lim - 1;
		
		// Allows maxPrime to be marked.
		boolean notPrime[] = new boolean[maxPrime + 1];
		
		long sum = 0;

		// 2,3,5,7,9,...
		for(int p = 2; p < notPrime.length; p += (p & 1) + 1)
		{
			if (notPrime[p]) 
				continue;
			
			sum += p;
			
			// Mark all multiples.
			// Multiples < p * p are already marked.
			// Only start if p * p <= maxPrime.
			// Condition equivalent to p <= maxPrime / p.
			if (p > maxPrime / p) 
			{
				continue;
			}
			
			for(int i = p * p; i < notPrime.length; i += p)
			{
				notPrime[i] = true;
			}
		}
		
		return sum;
	}
}
