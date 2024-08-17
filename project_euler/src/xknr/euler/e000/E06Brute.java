package xknr.euler.e000;

import xknr.euler.Solution;

public class E06Brute extends Solution
{
	public E06Brute(boolean doPrint) {
		super(doPrint);
	}

	public long solve(int lim)
	{	
		int sum1 = 0;
		
		for(int counter = 1; counter <= lim; counter++)
		{
			sum1 += counter;
		}
		
		int sum2 = 0;
		
		for(int counter = 1; counter <= lim; counter++)
		{
			sum2 += counter * counter;
		}
		
		return sum1 * sum1 - sum2;
	}

}
