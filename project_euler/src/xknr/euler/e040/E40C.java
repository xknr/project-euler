package xknr.euler.e040;

import xknr.euler.Solution;

public class E40C extends Solution
{

	public E40C(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{
		
		int prod = 1, curr = 0, tInd = 0;		
		
		for(int i = 1; ; ) 
		{
			int len = len(i);
			
			int place = TS[tInd] - 1 - curr;
			
			if (place < len) 
			{				
				// Get digit from i at place.
				int d = String.valueOf(i).charAt(place) - '0';
				
				// println(ts[tInd], curr, curr + len, i, d);
				
				// Multiply product with this digit.
				prod *= d;
				
				// Choose the next target.
				tInd++;
				
				// No targets left.
				if (tInd >= TS.length) 
				{
					// Return result.
					return prod;
				}
			}

			// How many items of len digits are there left?
			int count = (int)Math.pow(10, len) - i;
			
			// Does adding all of them go beyond current target? 
			if (curr + count * len > TS[tInd]) 
			{				
				// Calculate how many we can add until next target.
				count = (TS[tInd] - curr) / len;
			}
			
			// Add this many items of this len.
			i += count;
			
			curr += count * len;
		}
		
	}

	public static int len(int i) {
		return String.valueOf(i).length();
	}

	// All targets.
	private static final int[] TS = {
		1, 10, 100, 1000, 10000, 100000, 1000000
	};


}