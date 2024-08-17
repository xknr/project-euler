package xknr.euler.e040;

import java.util.Map;

import xknr.euler.Solution;
import xknr.euler.util.Util;

public class E47 extends Solution
{
	public E47(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{
		int seqCounter = 0;
		
		for(int x = 1; ; x++)
		{
			// Counts of prime factors.
			Map<Long, Integer> pf = Util.countDPF((long)x);

			// Number of distinct prime factors
			int numDistinct = pf.keySet().size();
			
			if (numDistinct >= 4)
			{
				// Increment counter each time we find such number.
				seqCounter++;
				
				// A four-sequence was found.
				if (seqCounter == 4) 
				{					
					// First number in the sequence.
					return x - 3;
				}
			}
			else
			{
				// Reset counter if sequence ended.
				seqCounter = 0;
			}
		}
	}
}

