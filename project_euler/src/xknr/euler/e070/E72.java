package xknr.euler.e070;

import java.util.ArrayList;
import java.util.List;

import xknr.euler.Solution;
import xknr.euler.prime.Primes3;
import xknr.euler.util.Util;

public class E72 extends Solution
{	
	public E72(boolean doPrint) {
		super(doPrint);
	}

	public long solve(int N) 
	{	
		List<Integer> primes = genPrimes(N);
		
		long count = 0;
		
		count += N - 1; // number of reduced  fractions in the form of 1/x where x <= N
		
		for(int i = 2; i <= N; i++) 
		{
			int c = i;
			int pindex = 0;
			int prime = primes.get(pindex);
			boolean used = false;
			List<Integer> upf = new ArrayList<Integer>(); // unique prime factors
		
			while(c > 1) 
			{
				if (c % prime == 0) 
				{
					if (!used) 
					{
						upf.add(prime);
					}
					
					c /= prime;
					used = true;
				} 
				else 
				{
					if (c / prime < prime) 
					{
						upf.add(c);
						break;
					}
					
					used = false;
					prime = primes.get(++pindex);
				}
			}
			
			int co[] = new int[1];
			int sign = 1;
			
			for(int j = 1; j <= upf.size(); j++) 
			{
				combinations(upf, new int[j], 0, co, sign, N);
				sign = -sign;
			}

			count += N - co[0];
		}		

		return count / 2;		
	}
	
	public static List<Integer> genPrimes(int count)
	{
		int maxPrime = (int)Util.sieveSizeUpperLimit(count);
		// 16_441_303
		
		long[] sieve = Primes3.makeSieve(maxPrime);
		return Primes3.collect(sieve, maxPrime, null);
	}

	private static void combinations(List<Integer> upf, 
		int indexes[], int depth, int[] co, int sign, int N)
	{		
		if (depth == indexes.length) 
		{			
			long mul = 1;
			
			for(int index: indexes) 
			{
				int p = upf.get(index);
				mul *= p;
			}
			
			co[0] += N / (sign * mul);
		}
		else 
		{
			int start = depth == 0 ? 0 : indexes[depth-1] + 1;
			
			for(indexes[depth] = start; indexes[depth] < upf.size(); indexes[depth]++) 
			{
				combinations(upf, indexes, depth+1, co, sign, N);
			}
		}
	}
}
