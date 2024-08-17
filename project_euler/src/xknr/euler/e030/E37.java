package xknr.euler.e030;

import xknr.euler.Solution;
import xknr.euler.prime.Primes3;

public class E37 extends Solution
{
	public E37(boolean doPrint) {
		super(doPrint);
	}

	long[] sieve;
	
	public static final int MAX_PRIME = 1000_000;
	
	// Given in the question.
	public static final int TARGET_COUNT = 11;

	public long solve()
	{
		sieve = Primes3.makeSieve(MAX_PRIME); 

		// We will use the count to validate.
		int count = 0;
		
		int sum = 0;

		// Skip 2,3,5,7
		int first = 11; 
		
		for(int p = first; p <= MAX_PRIME; p += 2)
		{
			if (isPrime(p)) {
				if (validFromRight(p) && validFromLeft(p)) {
					count++;
					sum += p;
				}
			}
		}
		
		// Validate.
		assert count == TARGET_COUNT;

		return sum;
	}

	/**
	 * 
	 * Every number produced by removing digits from right to left must be a prime.
	 * 
	 * The method does not check p itself, p must be a prime.
	 * 
	 * @param p The number tested for validity.
	 * @return
	 */
	private boolean validFromRight(int p)
	{
		// We start from p / 10 because p was already tested for prime.
		
		for(int t = p / 10; t > 0; )
		{
			// Found a non prime.
			if (!isPrime(t)) 
				return false;
			
			// Remove last digit.
			t /= 10;
		}
		
		return true;
	}

	
	/**
	 * 
	 * Every number produced by removing digits from left to right must be a prime.
	 * 
	 * The method does not check p itself, p must be a prime.
	 * @param p The number tested for validity.
	 * @return
	 */
	private boolean validFromLeft(int p)
	{
		// Calculate a mask to remove first digit.
		int mask = makeMask(p);
		
		for(int t = p; mask > 1; )
		{
			// Remove the first digit.
			t = t % mask;

			// Test for prime.
			if (!isPrime(t)) 
				return false;	
			
			// Shorten the mask as well.
			mask /= 10;
		}
		
		return true;
	}
	
	/**
	 * Returns a power of ten with same number of digits as x.
	 * "Largest (nearest) smaller or equal power of ten"
	 * @param x
	 * @return
	 */
	public static int makeMask(int x) 
	{
		int mask = 1;
		for(int t = x / 10; t > 0; t /= 10)
			mask *= 10;
		return mask;
	}

	private boolean isPrime(long x) 
	{
		return Primes3.isPrime(sieve, x);
	}
	


}