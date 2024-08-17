package xknr.euler.e060;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import xknr.euler.Solution;
import xknr.euler.miller.MillerRabinDk;
import xknr.euler.prime.Primes3;

public class E60 extends Solution
{	
	public E60(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{	
		int LIMIT = 10000;
		long[] sieve = Primes3.makeSieve(LIMIT - 1);
		List<Integer> primes = Primes3.collect(sieve, LIMIT - 1, null);
		
		primes.remove((Integer)2);
		primes.remove((Integer)5);
		
		//Map<Integer, Set<Integer>> matches = new HashMap<Integer, Set<Integer>>();
		
		@SuppressWarnings("unchecked")
		Set<Integer>[] ms = (Set<Integer>[])new Set[LIMIT];
		
		result = Integer.MAX_VALUE;
		
		for(int i = 0; i < primes.size(); i++)
		{
			int pi = primes.get(i);			
			if (pi >= result)
			{
				break;
			}
			
			ms[i] = new HashSet<Integer>();
			
			for(int j = 0; j < i; j++)
			{
				int pj = primes.get(j);
				if (pi + pj >= result)
				{
					break;
				}
				
				if (!calcPair(pi, pj))
				{
					continue;
				}
				
				ms[i].add(j);
				
				for(int k = 0; k < j; k++)
				{
					int pk = primes.get(k);
					
					if (pi + pj + pk >= result)
					{
						break;
					}
					
					if (!ms[i].contains(k) || !ms[j].contains(k))
					{
						continue;
					}
					
					check(primes, ms, i, j, k);
				}
			}
		}
		
		return result;
	}

	private void check(List<Integer> primes, Set<Integer>[] ms, int i, int j, int k) 
	{
		int pi = primes.get(i);
		int pj = primes.get(j);
		int pk = primes.get(k);
		
		for(int l = 0; l < k; l++) 
		{
			int pl = primes.get(l);
			
			if (pi + pj + pk + pl >= result)
			{
				break;
			}
			
			if (!ms[i].contains(l) || !ms[j].contains(l) || !ms[k].contains(l) )
			{
				continue;
			}
			
			for(int m = 0; m < l; m++) 
			{
				int pm = primes.get(m);
				
				if (pi+pj+pk+pl+pm >= result)
				{
					break;
				}
				
				if (!ms[i].contains(m) || !ms[j].contains(m) ||
					!ms[k].contains(m) || !ms[l].contains(m) )
				{
					continue;
				}
				
				int sum = pi + pj + pk + pl + pm;
				
				format("%d %d %d %d %d\n", pi, pj, pk, pl, pm);
				
				result = Math.min(result, sum);
			}
		}
	}

	public static long merge(int a, int b)
	{
		long result = 1;
		
		for(int t = b; t > 0; t /= 10) 
		{
			result *= 10;
		}
		
		return result * a + b;
	}

	public static boolean calcPair(int x, int y)
	{		
		long xy = merge(x, y);
		long yx = merge(y, x);
		
		assert xy <= Integer.MAX_VALUE;
		assert yx <= Integer.MAX_VALUE;
		
		if (!MillerRabinDk.isPrime((int)xy))
		{
			return false;
		}
		
		if (!MillerRabinDk.isPrime((int)yx))
		{
			return false;
		}
		
		return true;
	}
	
	private int result;
}
