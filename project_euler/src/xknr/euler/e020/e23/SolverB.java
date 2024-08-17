package xknr.euler.e020.e23;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntConsumer;

import xknr.euler.prime.Primes3;
import xknr.euler.util.Util;

public class SolverB extends SolverA 
{
	public SolverB(int lim) 
	{
		super(lim);			

		// We won't need primes > sqrt(28123).
		int maxPrime = (int)Math.ceil(Math.sqrt(E23A.LIM));
		maxPrime += 10; // We need a few extra to fetch the next prime.
		
		long[] sieve = Primes3.makeSieve(maxPrime);	
		primes = Primes3.collect(sieve, maxPrime, null);				
	}

	@Override
	protected void collect(IntConsumer collector)
	{
		// For all n <= lim.
		for(int n = 1; n <= lim; n++) 
		{
			// Calc proper divisor sum.
			long s = Util.sumOfDivisors(n, primes) - n;

			// If greater, then abundant.
			if (s > n) 
			{
				// Mark and collect.
				isAbundant[n] = true;
				collector.accept(n);
			}
		}		
	}

	private List<Integer> primes;

}

