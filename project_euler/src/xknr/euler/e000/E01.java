package xknr.euler.e000;

import static xknr.euler.util.Util.divides;

public class E01 
{
	/**
	 * Brute force.
	 */
	public int solve(int lim)
	{		
		int sum = 0;
		
		// For each integer in [1, lim).
		for(int i = 1; i < lim; i++) 
		{			
			// If divides with either 3 or 5, 
			if (divides(i, 3) || divides(i, 5)) 
			{				
				// Add to sum.
				sum += i;
			}
		}
		
		return sum;		
	}
}
