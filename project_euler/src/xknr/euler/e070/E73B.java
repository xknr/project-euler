package xknr.euler.e070;

import xknr.euler.Solution;
import xknr.euler.util.Util;

public class E73B extends Solution
{	
	public E73B(boolean doPrint) {
		super(doPrint);
	}

	public long solve() 
	{	
		int count = 0;
		
		for(int d = 5; d <= 12000; d++) 
		{	
			int first = d / 3 + 1;
			int last = (d - 1) / 2;
			
			for(int n = first; n <= last; n++)
			{
				assert 3*n > d && 2 * n < d;
				
				long g = Util.gcd(n, d);
				
				if (g == 1) 
				{
					count++;
				}
			}
		}
		
		return count;
	}
}
