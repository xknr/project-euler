package xknr.euler.e090;

import xknr.euler.Solution;
import xknr.euler.util.LSQRT;
import xknr.euler.util.Maartinus;
import xknr.euler.util.Util;

public class E94 extends Solution
{
	
	public E94(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{
		long sum = 0;
		
		// a is the length of the differing edge.
		for(long a = 1; ; a++)
		{
			// It differs by either -1 or +1.
			for(int i = -1; i <= 1; i += 2)
			{
				// b is the length of the other edges.
				final long b = a + i;
				
				if (b < 1)
				{
					continue;
				}
				
				final long peri = a + b + b;
				
				if (peri > LIM)
				{
					return sum;
				}
				
				final long h2s = 4 * b * b - a * a;
				
				if (h2s < 1) 
				{
					continue;
				}
				
				if (!Maartinus.isSquare(h2s)) 
				{
					continue;
				}
				
				final long h2 = LSQRT.lsqrtB(h2s);
				
				final long area8 = h2 * a;
				
				if (area8 % 16 == 0) 
				{
					sum += peri;
					println(a, b, peri);
				}
			}
			
		}
		
	}

	private static final long LIM = 1000_000_000;
}

