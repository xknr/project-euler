package xknr.euler.e000;

import xknr.euler.Solution;

public class E08B extends Solution
{

	public E08B(boolean doPrint) {
		super(doPrint);
	}

	public long solve(int window)
	{
		String data = E08Data.DATA;
		
		// Best found so far.
		long best = 0;
		
		// Running product.
		long p = 1; 

		// Number of items in the running product.
		int count = 0;

		for(int i = 0; i < data.length(); i++)
		{
			int d = E08.getDigit(data, i);
			
			if (d == 0)
			{
				// Reset the running product.
				count = 0;
				p = 1;
			}
			else
			{
				// Multiply running product with this digit.
				p *= d; 
				count++;
				
				if (count > window)
				{
					// Divide running product by the discarded digit.
					p /= E08.getDigit(data, i - window); 
					count--;
				}
				
				if (count == window) 
				{
					// Update best.
					best = Math.max(best, p);
				}
			}
		}
		
		return best;
	}
		
}
