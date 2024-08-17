package xknr.euler.e000;

import xknr.euler.Solution;

public class E08 extends Solution
{
	public E08(boolean doPrint) {
		super(doPrint);
	}

	public long solve(int window)
	{
		final String data = E08Data.DATA;

		long best = 0;
		
		for(int i = 0; i <= data.length() - window; i++) 
		{
			best = Math.max(best, sumAt(data, i, window));
		}
		
		return best;
	}

	/**
	 * Sum n digits starting from given index.
	 * @param start Starting position.
	 * @param window Window size.
	 * @return
	 */
	public static long sumAt(String data, int start, int window)
	{
		long p = 1;
		
		for(int j = start; j < start + window; j++) 
		{
			p *= getDigit(data, j);
		}
		
		return p;
	}

	public static int getDigit(String data, int ind) 
	{
		final char c = data.charAt(ind);
		final int d = c - '0';
		return d;
	}
}
