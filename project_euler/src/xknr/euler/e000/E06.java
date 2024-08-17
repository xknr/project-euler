package xknr.euler.e000;

import xknr.euler.Solution;
import xknr.euler.util.Util;

public class E06 extends Solution
{
	public E06(boolean doPrint) {
		super(doPrint);
	}

	public long solve(int n) 
	{		
		long tri = Util.triangular(n);
		return tri * tri - Util.pyramidal(n);
	}

}
