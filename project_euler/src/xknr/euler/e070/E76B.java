package xknr.euler.e070;

import xknr.euler.Solution;

public class E76B extends Solution
{
	public E76B(boolean doPrint) {
		super(doPrint);
	}

	public long solve() 
	{		
		int comb[] = new int[LIMIT + 1];
		
		comb[0] = 1;
		
		for(int c = 1; c <= LIMIT - 1; c++)
		{
			for(int j = c; j <= LIMIT; j++)
			{
				comb[j] += comb[j - c];
			}
		}
		
		return comb[LIMIT];	
	}
	
	private static final int LIMIT = 100;
}
