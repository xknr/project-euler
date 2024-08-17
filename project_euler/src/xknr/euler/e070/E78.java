package xknr.euler.e070;

import java.util.HashMap;
import java.util.Map;

import xknr.euler.Solution;

/*
 * Not using BigInteger.
 */
public class E78 extends Solution
{	
	public E78(boolean doPrint) {
		super(doPrint);
	}

	/**
	 * https://en.wikipedia.org/wiki/Partition_(number_theory)
	 */	
	public long solve() 
	{
		cache = new HashMap<Integer, Integer>();
		
		for(int i = 1; ; i++) 
		{
			int b = p(i);
			if(b % 1000000 == 0) 
			{ 
				cache = null;
				return i;
			}
		}
	}
	
	public int p(int k) 
	{		
		if (k < 0) return 0;
		else if (k == 0) return 1;
		else if (k == 1) return 1;
		
		Integer cached = cache.get(k);
		if (cached != null) 
		{
			return cached;
		}
		
		int sum = 0;
		
		for(int m = 1; ; m++) 
		{	
			// m = 0 1 2 3 4 5 6
			// n = 0 1 1 2 2 3 3
			//sn = - + - + - + -
			int sn = (m % 2) * 2 - 1;
			int n = ((m+1) / 2) * sn;			
			int k2 = (3*n*n - n) / 2;
			
			if (k-k2 < 0)
			{
				break;
			}
			
			int sign = ((((m - 1) / 2) % 2) == 0) ? 1 : -1;
			format("%3d %3d %3d %3d\n", sn, n, k2, sign);
			sum = sum + p(k-k2) * sign;
			sum = sum % 1_000_000;			
		}
		
		cache.put(k, sum);
		
		return sum;		
	}

	private Map<Integer, Integer> cache = new HashMap<Integer, Integer>();
}

