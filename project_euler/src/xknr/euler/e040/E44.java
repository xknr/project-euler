package xknr.euler.e040;

import xknr.euler.Solution;
import xknr.euler.util.Util;

public class E44 extends Solution
{
	public E44(boolean doPrint) {
		super(doPrint);
	}
	
	public long solve()
	{
		long tested = 0;
		
		// We iterate over increasing values of D candidates.
		for(int di = 1; ; di++) 
		{
			// If this d can satisfy, it is the smallest answer.
			long d = Util.pentagonal(di);
			
			long bPrev = d; // Will be used for stop condition.
			
			// Iterate over every b > d.
			for(int bi = di + 1; ; bi++) 
			{
				tested++;
				
				long b = Util.pentagonal(bi);
				
				// If difference between B and BPrev is greater than D,
				// it means we can no longer find a pentagonal A by doing (B-D)
				if (b - bPrev > d)
				{
					break;
				}
				
				// A can be much smaller than d.
				long a = b - d;

				//format("%d (%d) %d (%d) -- %d %d\n", di, d, bi, b, a, 0);
				
				// Check whether this (d, b) satisfies the condition.
				if (Util.isPentagonal(a)) 
				{
					long c = a + b;
					
					if (Util.isPentagonal(c)) 
					{ 
						println("tested", tested);
						return d;
					}	
				}
				
				bPrev = b;
			}
		}
	}
}

