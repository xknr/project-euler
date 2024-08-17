package xknr.euler.e040;

import xknr.euler.Solution;
import xknr.euler.util.Util;

public class E45 extends Solution
{
	public E45(boolean doPrint) {
		super(doPrint);
	}
	
	public long solve()
	{
		// No need to test triangular numbers bc 
		// All of these hexagonal numbers are also 
		// triangular, 
		
		for(int n = KNOWN + 1; ; n++)
		{
			long h = Util.hexagonal(n);
			
			if (Util.isPentagonal(h)) 
			{ 
				return h;
			}
		}
	}

	// Given in the question.
	private static final int KNOWN = 143;
}

