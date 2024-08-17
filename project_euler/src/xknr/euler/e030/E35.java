package xknr.euler.e030;

import xknr.euler.Solution;
import xknr.euler.prime.Primes3;

public class E35 extends Solution
{
	public E35(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{
		sieve = Primes3.makeSieve(LIMIT - 1); 
	
		int count = 0;
		
		// Our main loop skips even numbers but
		// 2 is a circular prime. 
		count++; 
		
				
		// We use this to perform the rotations.
		int mask = 1;
		
		for(int n = 3; n < LIMIT; n += 2) 
		{
			// Update mask so that it's always the power of 
			// 10 with the same number of digits as n. 
			if (n >= mask * 10)
				mask *= 10;

			count += countCirculars(n, mask);
		}
	
		return count;
	}
	
	/**
	 * Discovers if x is the smallest prime in a circular prime group, 
	 * if so, returns the size of the group.
	 * 
	 * @param n Original prime number
	 * @return
	 */
	public int countCirculars(int n, int mask)
	{				
		// Current rotation
		int rot = n;	
		
		int count = 0;

		do 
		{	
			// We do the primality check at the beginning
			// of the loop, in order to detect n being non-prime 
			// as quick as possible. 
			
			// If any rotation is not prime, cancel.
			if (!Primes3.isPrime(sieve, rot)) 
			{
				return 0;
			}

			// Take last digit.
			int digit = rot % 10;
			
			// Even digits cannot appear as the last digit.
			if (digit % 10 == 0)
			{
				return 0;
			}
			
			// Make a new rotation.
			rot = (rot / 10) + digit * mask;
			
			count++;
			
			// Should only accept if the first number is the smallest rotation.
			if (rot < n)
			{
				return 0;
			}
		} while(n != rot);
		
		// Count all rotations.
		return count;
	}

	private static final int LIMIT = 1000_000;

	private long[] sieve;
}

