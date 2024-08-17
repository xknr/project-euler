package xknr.euler.e050;

import java.util.List;

import xknr.euler.Solution;
import xknr.euler.prime.Primes3;

public class E50 extends Solution
{
	public E50(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{		
		sieve = Primes3.makeSieve(LIM - 1);
		primes = Primes3.collect(sieve, LIM - 1, null);

		// Try every possible consecutive sum of primes < 1M.
		for(int a = 0; a < primes.size(); a++) 
		{
			int sum = 0; // Sum of primes between [start, i]
			
			for(int j = a; j < primes.size(); j++) 
			{
				sum += primes.get(j);
				
				if (sum >= LIM)
				{
					break;
				}

				checkSum(sum, j - a + 1);
			}
		}
		
		return bestSum;
	}

	private void checkSum(int sum, int numTerms) 
	{
		if (!Primes3.isPrime(sieve, sum))
		{
			return;
		}
		
		if (numTerms <= bestCount)
		{
			return;
		}
		
		// Found the longest chain so far.
		bestCount = numTerms;
		bestSum = sum;
	}

	private static final int LIM = 1000_000;

	private long[] sieve;
	private List<Integer> primes;

	private int bestCount = 0, bestSum = 0;
}
