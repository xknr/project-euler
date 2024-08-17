package xknr.euler.e000;

import xknr.euler.Solution;
import xknr.euler.prime.Primes3;

public class E03C extends Solution
{	
	public E03C(boolean doPrint) {
		super(doPrint);
	}

	public long solve(long x) 
	{
		long maxPrime = (long)Math.ceil(Math.sqrt(x));
		
		long[] sieve = Primes3.makeSieve(maxPrime);
		
		long p = 2;
		
		while(x > 1) 
		{
			long d = x / p;
			
			if (d * p == x) 
			{
				// Found a prime factor:
				x = d;
			}
			else if (d < p) 
			{
				// No more prime factors are possible and 
				// current value of x is the last prime factor.
				p = x;
			}
			else 
			{
				// Assign next prime to p. 
				do {
					p++;
				} while(!Primes3.isPrime(sieve, p));
			}
		}
		
		return p;
		
	}
}
