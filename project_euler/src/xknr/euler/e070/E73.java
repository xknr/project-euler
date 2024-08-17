package xknr.euler.e070;

import xknr.euler.Solution;
import xknr.euler.util.Util;

public class E73 extends Solution
{	
	public E73(boolean doPrint) {
		super(doPrint);
	}

	public long solve() 
	{	
		int count = 0;
		
		for(int d = 4; d <= 12000; d++) 
		{			
			for(int n = 1; n < d; n++)
			{
				if (3 * n > d && 2 * n < d)
				{					
					if (Util.gcd(n, d) == 1) 
					{
						println(n, + d);
						count++;
					}
				}
			}
			
		}
		
		println(count);
		
		return count;
	}
}
