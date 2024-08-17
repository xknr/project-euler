package xknr.euler.e010;

import xknr.euler.Solution;
import xknr.euler.prime.Primes3;

public class E10B extends Solution
{
	public E10B(boolean doPrint) {
		super(doPrint);
	}

	public long solve(int lim)
	{		
		// Make prime number sieve, with maxPrime = lim - 1.
		long[] sieve = Primes3.makeSieve(lim - 1);
		
		// Handle 2 separately.
		long sum = 2;

		// All odd numbers.
		for (long i = 3; i < lim; i += 2)
		{
			// If prime.
			if (Primes3.isPrime(sieve, i)) 
			{
				// Add to sum.
				sum += i;
			}
		}
		
		return sum;
	}

}
