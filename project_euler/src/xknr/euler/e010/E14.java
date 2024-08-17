package xknr.euler.e010;

import xknr.euler.Solution;

public class E14 extends Solution
{
	public E14(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{	
		CollatzLen col = new CollatzLen();
		
		col.setCache(new int[CACHE_SIZE]);
		
		// Initialize best found.
		int best_len = 0, best_n = 0;
		
		// Check all numbers [1, LIM)
		for(int n = 1; n < LIM; n++) 
		{
			int len = col.collatzLen(n);

			// Update best.
			if (len > best_len) 
			{
				best_len = len;
				best_n = n;
			}			
		}
		
		return best_n;
	}
	
	public static long collatzNext(long n) 
	{
		if (n % 2L == 0L) 
		{
			// Even:
			return n / 2L;
		}
		else 
		{
			// Odd:
			return 3L * n + 1L;
		}
	}

	
	public static class CollatzLen 
	{
		protected int[] cachedLen;

		public void setCache(int[] arr) 
		{
			cachedLen = arr;

			// Termination condition for recursive function.
			cachedLen[1] = 1;
		}

		public int collatzLen(long n) 
		{
			assert n >= 1;

			// If n is outside cache range:  
			if (n >= cachedLen.length) 
			{ 
				// Perform recursive calculation normally.
				return collatzLen(E14.collatzNext(n)) + 1;
			}

			// Check cache.
			int result = cachedLen[(int)n];
			
			if (result != 0) 
			{  
				// Found in cache.
				return result;
			}
			
			// Perform recursive calculation.
			result = collatzLen(E14.collatzNext(n)) + 1;

			// Store in cache.
			cachedLen[(int)n] = result;

			// Return the result.
			return result;
		}		
	}
	
	private static final int LIM = 1000_000;
	
	// Higher numbers are not queried very often, so 
	// we can reducethe cache size to reduce 
	// the initial allocation cost.
	private static final int CACHE_SIZE = 900_000;
}

