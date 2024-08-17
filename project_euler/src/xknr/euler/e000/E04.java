package xknr.euler.e000;

import xknr.euler.Solution;
import xknr.euler.util.Util;

/**
 * Brute force.
 */
public class E04 extends Solution
{
	public E04(boolean doPrint) {
		super(doPrint);
	}

	/**
	 * @param lo Lowest n digit number. i.e. 10, 100, 1000
	 * @return
	 */
	public long solve(final int lo)
	{	
		// Highest number with same number of digits as lo.
		final int hi = lo * 10 - 1; 
		 
		assert lo != 10 || hi == 99; // For 10 it must be 99.
		assert lo != 100 || hi == 999;  // For 100 it must be 999.

		int best = lo * lo;
		
		for(int a = hi; a >= lo; a--) 
		{			
			if (a * hi <= best) 
			{
				// Impossible to find a better solution beyond this point.
				break;
			}
			
			for(int b = hi; b >= a; b--) 
			{
				int p = a * b;
				
				if (p <= best) 
				{
					// Impossible to find a better solution for this a.
					break;
				}
				
				if (Util.isPalindrome(p))
				{
					best = p;
				}
			}
		}
		
		return best;
	}

}
