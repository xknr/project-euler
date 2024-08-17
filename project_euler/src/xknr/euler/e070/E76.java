package xknr.euler.e070;

import java.util.HashMap;

import xknr.euler.Solution;

public class E76 extends Solution
{
	public E76(boolean doPrint) {
		super(doPrint);
	}

	public long solve() 
	{
		HashMap<Integer, Integer> cache = createCache();
		return f(100, 99, cache);
	}

	public static int f(int a, int b, HashMap<Integer, Integer> cache) 
	{		
		b = Math.min(a, b);

		if (b < 2) 
		{
			return 1;
		}
		else if (b == 2) 
		{
			return ((int)(a / 2)) + 1;
		}
		
		int key = a * 1000 + b;
		Integer cached = cache.get(key);
		if (cached != null)
		{
			return cached;
		}
		
		int sum = 0;
		for(int i = b; i > 0; i--) 
		{
			sum += f(a-i, i, cache);			
		}
		
		cache.put(key, sum);
		
		return sum;		
	}

	public static HashMap<Integer, Integer> createCache() 
	{
		return new HashMap<Integer, Integer>(); 
	}
}
