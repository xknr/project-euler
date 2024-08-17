package xknr.euler.prime;


import java.util.ArrayList;
import java.util.List;

public class Primes 
{	
	/**
	 * Produce a prime number sieve where non-primes are marked as true.
	 * 
	 * @param maxPrime Largest prime candidate to be checked by the algorithm, inclusive.
	 * @return boolean array indicating non-primes.
	 */
	public static boolean[] makeSieve(long maxPrime)
	{
		assert maxPrime <= MAX_ARRAY_SIZE - 1L; 

		long arrSize = maxPrime + 1;
		
		// Redundant bc of the first assertion.
		assert arrSize <= MAX_ARRAY_SIZE; 
		
		boolean[] sieve = new boolean[(int)arrSize];

		// Set 0 and 1 as non-prime.
		sieve[0] = sieve[1] = true;

		// 2,3,5,7,9,...
		// Only need to check p when p * p <= maxPrime.
		// Condition equivalent to p <= maxPrime / p.
		for (int p = 2; p <= maxPrime / p; p++) 
		{
			// If p is prime,
			if (!sieve[p])
			{
				// Mark all multiples.
				// Multiples < p * p are already marked.
				for (int k = p * p; k <= maxPrime; k += p) 
				{
					// Mark as non-prime.
					sieve[k] = true;					
				}
			}
		}

		return sieve;
	}

	public static List<Integer> collect(boolean[] sieve, long maxPrime, List<Integer> li)
	{		
		// Create a list if not supplied. 
		if (li == null) 
		{
			li = new ArrayList<Integer>();
		}
		
		// Handle 2 separately.
		li.add(2);
		
		// For all odd numbers >= 3.
		for (int i = 3; i <= maxPrime; i += 2)
		{
			// If prime,
			if (!sieve[i]) 
			{
				// Add to list.
				li.add(i);
			}
		}
		
		return li;
	}
	
	// TODO Look this up, it may be defined somewhere.
	public static final long MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
}
