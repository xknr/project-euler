package xknr.euler.prime;

import static xknr.euler.prime.Primes.*;

import java.util.*;

public class Primes2
{	
	/**
	 * Skip even numbers.
	 * 
	 * @param maxPrime Largest prime candidate checked by the algorithm, inclusive.
	 */
	public static boolean[] makeSieve(long maxPrime)
	{
		// when maxPrime = 7, arrSize will be 4.
		// when maxPrime = 8, arrSize will be 4.
		// isPrime2(7) will look up arr position 3.
		// isPrime2(8) would use arr position 4 if not for the even number check.
		// when maxPrime = 8, isPrime(x) with x > 8 will throw exception.

		assert maxPrime <= MAX_PRIME2;

		final long arrSize = (maxPrime + 1) / 2;
		
		// Redundant bc of the first assertion.
		assert arrSize <= MAX_ARRAY_SIZE;
		
		boolean[] sieve = new boolean[(int)arrSize];

		// Mark 0 and 1 as non-prime.
		sieve[0] = true; 

		// 2,3,5,7,9,...
		// Only need to check p when p * p <= maxPrime.
		// Condition equivalent to p <= maxPrime / p.
		for (long p = 3; p <= maxPrime / p; p += 2)
		{
			// If p is prime,
			if (!sieve[(int)(p >>> 1)]) 
			{
				// Mark all multiples.
				// Multiples < p * p are already marked.
				for (long k = p * p; k <= maxPrime; k += p * 2) 
				{
					// Mark as non-prime.
					sieve[(int)(k >>> 1)] = true;
				}
			}
		}

		return sieve;
	}
	
	public static boolean isPrime(boolean[] sieve, long p)
	{
		if (p < 2) return false; // negative numbers and 0, 1 are not prime
		if (p < 4) return true; // 2 and 3 are primes
		if ((p & 1) == 0) return false; // even numbers > 2 are not prime
		
		return !sieve[(int)(p >>> 1)];
	}

	public static void listAdd(List<Integer> li, long x) 
	{
		li.add((Integer)(int)x);
	}
	
	public static void listAddL(List<Long> li, long x) 
	{
		li.add((Long)x);
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Number> void listAddGeneric(List<T> li, long x, boolean useInts) 
	{
		if (useInts) 
		{
			li.add((T)(Integer)(int)x);
		}
		else 
		{
			li.add((T)(Long)x);
		}
	}	
	
	public static List<Integer> collect(boolean[] sieve, long maxPrime, List<Integer> li)
	{
		// Create new list if none was supplied.
		if (li == null) 
		{ 
			li = new ArrayList<Integer>();
		}
		
		// Handle 2 separately.
		listAdd(li, 2);
		
		// For all odd numbers >= 3.
		for (long i = 3; i <= maxPrime; i += 2) 
		{
			// If prime,
			if (!sieve[(int)(i >>> 1)]) 
			{
				// Add to list.
				listAdd(li, i);
			}
		}
		
		return li;
	}	
	
	public static List<Long> collectL(boolean[] sieve, long maxPrime, List<Long> li)
	{	
		// Create new list if none was supplied.
		if (li == null) 
		{ 
			li = new ArrayList<Long>();
		}

		// Handle 2 separately.
		listAddL(li, 2);
		
		// For all odd numbers >= 3.
		for (long i = 3; i <= maxPrime; i += 2) 
		{
			// If prime,
			if (!sieve[(int)(i >>> 1)]) 
			{
				// Add to list.
				listAddL(li, i);
			}
		}
		
		return li;
	}
	
	public static List<? extends Number> collectGeneric(
			boolean[] sieve, long maxPrime, 
			List<? extends Number> li, 
			boolean useInt) 
	{
		// Create new list of proper type if none was supplied.
		if (li == null) 
		{			
			if (useInt)
			{
				li = new ArrayList<Integer>();
			}
			else
			{
				li = new ArrayList<Long>();
			}
		}
		
		// Handle 2 separately.
		listAddGeneric(li, 2, useInt);		
		
		// For all odd numbers >= 3.
		for (long i = 3; i <= maxPrime; i += 2) 
		{
			// If prime,
			if (!sieve[(int)(i >>> 1)]) 
			{
				// Add to list.
				listAddGeneric(li, i, useInt);
			}
		}
		
		return li;
	}
	
	// Simplified from ((MAX_ARRAY_SIZE - 1L) * 2L + 1L) - 1L
	public static final long MAX_PRIME2 =  (MAX_ARRAY_SIZE - 1L) * 2L;
}
