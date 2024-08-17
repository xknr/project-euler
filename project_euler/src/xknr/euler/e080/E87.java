package xknr.euler.e080;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import xknr.euler.prime.Primes3;


public class E87
{
	public int solve()
	{		 
		int limit = LIMIT;
		
		int maxPrime = limit - 1;
		
		long[] sieve = Primes3.makeSieve(maxPrime);
		
		List<Integer> primes = Primes3.collect(sieve, maxPrime, null);
			
		Set<Integer> set = new HashSet<Integer>();
		
		for(int a: primes)
		{
			final int aterm = a * a * a * a;
			
			if (aterm >= limit) 
			{
				break;
			}
			
			for(int b: primes) 
			{
				final int bterm = b * b * b;
				
				if (aterm + bterm >= limit)
				{
					break;
				}
				
				for(int c: primes) 
				{
					final int cterm = c * c;
					
					int sum = aterm + bterm + cterm;
					
					if (sum >= limit)
					{
						break;
					}
					
					// System.out.format("%d %d %d %d\n", a, b, c, sum);
					
					set.add(sum);
				}
			}
		}		
		return set.size();
	}

	public static final int LIMIT = 50_000_000;
}

