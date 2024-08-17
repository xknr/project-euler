package xknr.euler.e040;

import xknr.euler.Solution;

public class E47B extends Solution
{
	public E47B(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{
		int lim = 200_000;
		
		// Keeps track of number of distinct prime factors.
		int[] sieve = new int[lim];

		// A prime number sieve but every element keeps track of 
		// number of distinct prime divisors.
		makeSieve(sieve, lim);
	
		return findConsecutive(
				i -> sieve[i] == NUM_FACTORS, 
				lim, SEQ_LENGTH) 
			- SEQ_LENGTH + 1;
	}

	private void makeSieve(int[] sieve, int lim) 
	{
		// 2,3,5,7,9,...
		// We can stop when p * smallest 3 primes >= lim
		
		for(int p = 2; p * 2 * 3 * 5 < lim; p += (p & 1) + 1) 
		{
			// If p is prime,
			if (sieve[p] == 0) 
			{				
				// Increment counter for all multiples of p.
				// We cannot start from p * p because the elements 
				// between p and p * p have to be marked with this 
				// prime number. We can skip p itself though.
				for(int j = p * 2; j < lim; j += p) 
				{
					sieve[j]++;
				}
			}
		}
	}
	
	@FunctionalInterface
	interface ComparatorTask 
	{
	   public boolean check(int i);
	}

	private int findConsecutive(ComparatorTask comp, int lim, int targetLen) 
	{
		// Find targetLen consecutive numbers which satisfy the condition
		
		int seqCounter = 0;
		
		for(int i = 0; i < lim; i++)
		{
			if (comp.check(i))
			{
				// Increment counter each time we find such number.
				seqCounter++;
				
				// A four-sequence was found.
				if (seqCounter == targetLen) 
				{					
					// First number in the sequence.
					return i;
				}
			}
			else
			{
				// Reset counter if sequence ended.
				seqCounter = 0;
			}
		}
		
		return 0;
	}
	
	public static final int NUM_FACTORS = 4;
	public static final int SEQ_LENGTH = 4;
}
