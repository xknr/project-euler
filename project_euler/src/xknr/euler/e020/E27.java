package xknr.euler.e020;

import xknr.euler.Solution;
import xknr.euler.prime.Primes3;

public class E27 extends Solution
{
	public E27(boolean doPrint) {
		super(doPrint);
	}

	private static final int MAX_PRIME = 100_000;
	private long[] sieve;
	
	public long solve()
	{	
		sieve = Primes3.makeSieve(MAX_PRIME);
		
		// Initialize best found.
		int bestNum = 0, bestProduct = 0;
		
		// Search all a, b combinations within the given limits.
		for(int a = -999; a <= 999; a++) 
		{
			for(int b = -1000; b <= 1000; b++) 
			{				
				// Count number of primes generated.
				int num = numGeneratedPrimes(a, b);
				
				// Choose best.
				if (num > bestNum) 
				{
					bestNum = num;
					bestProduct = a * b;
				}
			}
		}
				
		return bestProduct;		
	}	
	
	/**
	 * Number of primes that can be generated with a pair of integers a, b
	 * using n * n + a * n + b.
	 * 
	 * @param a Integer no 1
	 * @param b Integer no 2
	 * @return Number of primes that can be generated.
	 */
	private int numGeneratedPrimes(int a, int b)
	{
		int n;
		
		for(n = 0; ; n++)
		{
			int term = n * n + a * n + b;

			// Check that we are not 
			if (term > MAX_PRIME) 
			{
				throw new RuntimeException(
					String.format("term = %d, max = %d", term, MAX_PRIME)
				);
			}
			
			// If term not prime:
			if (!Primes3.isPrime(sieve, term))
			{				
				// Search ends.
				break;
			}
			
			println(term, n);
		}
		
		return n;
	}

}