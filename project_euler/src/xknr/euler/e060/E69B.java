package xknr.euler.e060;

import java.util.HashSet;
import java.util.Set;

import xknr.euler.Solution;
import xknr.euler.util.Util;

public class E69B extends Solution
{
	public E69B(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{	
		long best_num = 0, best_den = 1;
		int best_n = 0;
		
		for(int n = 1; n <= LIMIT; n++) 
		{			
			Set<Integer> distinct = findDistinct(n);

			// A fraction equal to totient function:
			long num = 1, den = 1;
						
			for(int p: distinct) 
			{
				// multiply with 1 - 1 / p
				num *= p - 1;
				den *= p;
			}
			
			if (fractionCompare(den, num, best_num, best_den) > 0) 
			{				
				format("%d %s %d/%d\n", n ,distinct, num, den);			
				best_num = den;
				best_den = num;
				best_n = n;
			}
			
		}
		
		return best_n;		
	}

	public static Set<Integer> findDistinct(int n) 
	{
		Set<Integer> distinct = new HashSet<Integer>();
		
		int incr = 1;
		for(long x = n, p = 2; x > 1; ) 
		{
			long d = x / p;
			
			if (d * p == x)
			{
				x = d;
				distinct.add( (int)p );
			}
			else
			{
				if (p < d) 
				{
					p += incr;
					incr = 2;
				}
				else
				{
					// No higher factor can be found, current x is the last factor.
					p = x;
				}
			}
		}
		return distinct;
	}

	private int fractionCompare(long n1, long d1, long n2, long d2)
	{
		long gcd = Util.gcd(d1, d2);
		
		// lcm(d1, d2) = d1 * d2 / gcd(d1, d2)
		// multiply n1/d1 with lcm/d1 
		
		n1 *= d2 / gcd;
		n2 *= d1 / gcd;
		
		return Long.compare(n1, n2);
	}

	private static final int LIMIT = 1000_000;	
}

