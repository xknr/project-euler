package xknr.euler.e070;

import java.util.List;

import xknr.euler.Solution;
import xknr.euler.util.Util;

public class E70 extends Solution
{		
	public E70(boolean doPrint) {
		super(doPrint);
	}
	
	public long solve()
	{
		List<Integer> primes = Util.generatePrimes(MAX_PRIME);

		resetBest();
		
		// For every prime pi:
		for(int i = 0 ; i < primes.size(); i++)
		{
			int pi = primes.get(i);
			
			// For every prime pj > pi:
			for(int j = i + 1 ; j < primes.size(); j++)
			{
				final int pj = primes.get(j);
				
				if (pi * pj >= MAX_N)
				{
					// pi cannot produce a smaller multiple. 
					break;
				}
				
				testTwoPrimes(pi, pj);				
			}
		}
		
		//println(best_n, best_phi, best_ratio);
		println(best_n, best_ratio);
		
		return best_n;
	}
	
	private void testTwoPrimes(int pi, int pj) 
	{
		// Product of two primes.
		final int n = pi * pj;
		
		// Product of one minus prime's.
		final int phi = (pi - 1) * (pj - 1);
		
		if (!Util.arePermutations(n, phi))
			return;
		
		double r = n / (double)phi;
		
		println(n, phi, r);
		
		if (r < best_ratio)
		{
			best_ratio = r;
			best_n = n;
			//best_phi = phi;
		}
	}

	private void resetBest() 
	{
		best_ratio = 1e20d;
		best_n = 0;
		//best_phi = 0;
	}

	private double best_ratio;
	private int best_n;
	//private int best_phi;

	private static final int MAX_PRIME = 100_000;
	private static final int MAX_N = 10 * 1000 * 1000;
}



