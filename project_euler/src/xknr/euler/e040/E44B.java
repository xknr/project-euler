package xknr.euler.e040;

import xknr.euler.Solution;
import xknr.euler.util.Util;

/*
 * 
 * This finds the actual smallest solution, and is fast. 
 * But without the proof, this is likely a coincidence. 
 * 
 */
public class E44B extends Solution
{
	public E44B(boolean doPrint) {
		super(doPrint);
	}	

	public long solve()
	{		
		// The first such pentagonal difference we find will be 
		// the smallest one.
		
		for(int ai = 1; ; ai++)
		{
			long a = Util.pentagonal(ai);
			
			for(int bi = 1; bi < ai; bi++)
			{
				long b = Util.pentagonal(bi);
				
				long c = a + b;

				format("%d (%d) %d (%d) %d\n", ai, a, bi, b, c);

				if (Util.isPentagonal(c))
				{
					if (Util.isPentagonal(b + c)) 
					{
						return a;
					}
					
					if (Util.isPentagonal(a + c))
					{
						return b;
					}
				}
				
			}
		}
	}
}

