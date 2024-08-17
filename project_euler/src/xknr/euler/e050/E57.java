package xknr.euler.e050;

import xknr.euler.Solution;
import xknr.euler.util.BigFraction;

public class E57 extends Solution
{	
	public E57(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{	
		int found = 0;
		
		BigFraction a = new BigFraction(1, 2);
		
		for(int c = 0; c < LIM; c++)
		{
			BigFraction b = a.addOne();			
			
			if (isNumLongerThanDen(b))
			{
				found++;
			}
			
			a = a.addTwo().reciprocal();
		}
		
		return found;
	}

	public static boolean isNumLongerThanDen(BigFraction b)
	{
		return b.num().toString().length() > b.den().toString().length();
	}

	private static final int LIM = 1000;
}

