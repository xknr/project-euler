package xknr.euler.e000;

import xknr.euler.Solution;
import xknr.euler.util.Util;

public class E07 extends Solution
{
	public E07(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{
		int sieveSize = (int)Util.sieveSizeUpperLimit(N);
		boolean notPrime[] = new boolean[sieveSize];

		int found = 0;
		for(int p = 2; ; p += (p & 1) + 1) // 2,3,5,7,9,11,...
		{
			// Skip non-primes.
			if (notPrime[p])
			{
				continue;
			}
			
			found++;
			if (found == N)
			{
				return p;
			}
			
			// Mark all multiples of p as non-prime.
			
			// No need to mark when p > sqrt(sieveSize)
			// The conditional p*p <= s is equivalent to p <= s/p
			if (p > sieveSize / p)
			{
				continue;
			}
			
			// Start with p squared
			for(int i = p * p; i < sieveSize; i += p)
			{
				notPrime[i] = true;
			}
		}
	}
	
	private static final int N = 10001;
}
