package xknr.euler.e070;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import xknr.euler.Solution;
import xknr.euler.util.Util;

public class E78B extends Solution
{	
	public E78B(boolean doPrint) {
		super(doPrint);
	}

	/**
	 * https://en.wikipedia.org/wiki/Partition_(number_theory)
	 */	
	public long solve() 
	{		
		cache.clear();
		
		for(int i = 1; ; i++) 
		{			
			BigInteger b = p(i);
			
			if(b.toString().endsWith("000000"))
			{
				return i;
			}
		}
	}
	
	public BigInteger p(int k)
	{		
		if (k < 0) return Util.B0;
		else if (k == 0) return Util.B1;
		else if (k == 1) return Util.B1;
		
		BigInteger cached = cache.get(k);
		
		if (cached != null) 
		{
			return cached;
		}
		
		BigInteger sum = Util.B0;
		
		for(int m = 1; ; m++)
		{	
			// m = 0 1 2 3 4 5 6
			// n = 0 1 1 2 2 3 3
			//sn = - + - + - + -
			int sn = (m % 2) * 2 - 1;
			int n = ((m+1) / 2) * sn;			
			int k2 = (3*n*n - n) / 2;
			
			if (k-k2 < 0) break;
			
			int sign = ((((m - 1) / 2) % 2) == 0) ? 1 : -1;
			
			format("%3d %3d %3d %3d\n", sn, n, k2, sign);
			
			if (sign == 1) 
			{
				sum = sum.add( p(k-k2) );	
			}
			else 
			{
				sum = sum.subtract( p(k-k2) );
			}			
		}
		
		cache.put(k, sum);
		
		return sum;		
	}

	private Map<Integer, BigInteger> cache = new HashMap<Integer, BigInteger>();
}

