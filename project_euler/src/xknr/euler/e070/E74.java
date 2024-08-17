package xknr.euler.e070;

import java.util.HashMap;

import xknr.euler.Solution;

public class E74 extends Solution
{	
	public E74(boolean doPrint) {
		super(doPrint);
	}

	public long solve() 
	{		
		HashMap<Integer, Integer> cache = new HashMap<Integer, Integer>();
		
		cache.put(169, 3);
		cache.put(363601, 3);
		cache.put(1454, 3);

		cache.put(871, 2);
		cache.put(45361, 2);
		cache.put(872, 2);
		cache.put(45362, 2);
		
		int count = 0;
		
		for(int i = 1; i < LIM; i++)
		{
			int x = i;
			
			int g = g(x, cache);
			
			if (g == 60) 
			{
				count++;
			}			
		}
				
		return count;
	}
	
	public static int g(int x, HashMap<Integer, Integer> cache)
	{
		Integer cached = cache.get(x);
		
		if (cached != null) 
		{
			return cached;
		}

		int x2 = next(x);
		
		if (x2 == x) 
		{
			cache.put(x, 1);
			return 1;
		}
		
		int result = 1 + g(x2, cache);
		cache.put(x, result);
		return result;
	}	
	
	public static int g2(int x)
	{
		for(int j = 0; ; j++)
		{	
			if (x == 169) return j + 3;
			if (x == 363601) return j + 3;
			if (x == 1454) return j + 3;
			if (x == 871) return j + 2;
			if (x == 45361) return j + 2;
			if (x == 872) return j + 2;
			if (x == 45362) return j + 2;
							
			int x2 = next(x);
			if (x2 == x) return j + 1;
			x = x2;			
		}
	}

	private static int next(int x)
	{		
		int sum = 0;
		while(x > 0) 
		{
			int d = x % 10;
			
			sum += FACTORIALS[d];
			
			if(sum > 500_000_000) 
			{
				throw new AssertionError(); 
			}
			
			x /= 10;
		}
		
		return sum;		
	}

	private static final int LIM = 1_000_000;

	private static final int FACTORIALS[] = {
		1,
		1,
		2,
		6,
		24,
		120,
		720,
		5040,
		40320,
		362880,
	};
}
