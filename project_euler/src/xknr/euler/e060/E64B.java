package xknr.euler.e060;

import xknr.euler.Solution;

public class E64B extends Solution
{	
	/*
	 * Better method
	 * 
	 * https://en.wikipedia.org/wiki/Methods_of_computing_square_roots#
	 * Continued_fraction_expansion
	 * 
	 * TODO The algorithm terminates when this triplet is the same as one
	 * encountered before. The algorithm can also terminate on ai when ai = 2
	 * a0, which is easier to implement.
	 */

	public E64B(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{
		int count = 0;
		
		for(int n = 1; n <= 10000; n++) 
		{
			int len = periodLength(n);
			
			if (len == 0)
			{
				continue;
			}
			
			if (len % 2 == 1)
			{
				count++;
			}
		}		
		
		return count;
	}

	private int periodLength(int n) 
	{
		// Quick & dirty integer square root
		double fsqrt = Math.sqrt(n);
		int isqrt = (int)(Math.floor(fsqrt));
		int isqrt_ceil = (int)(Math.ceil(fsqrt));
		
		if (isqrt == isqrt_ceil) 
		{
			// perfect square
			return 0;
		}
		
		int m = 0;
		int d = 1;
		int a = isqrt;
		
		int count = 0;
		for(count = 0; ; count++) 
		{
			format("%d %d %d\n", m, d, a);
			
			if (a == 2 * isqrt) 
			{
				return count;
			}
			
			m = d * a - m;
			d = (n - m * m) / d;
			a = (isqrt + m) / d;
		}
	}
}
