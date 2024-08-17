package xknr.euler.e020;

import java.util.HashMap;
import java.util.Map;

import xknr.euler.Solution;

public class E26 extends Solution
{
	
	public E26(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{		
		int bestNum = 0, bestLen = 0;
		
		// Try each number in [1, LIMIT), find the best one.
		for(int num = 1; num < LIMIT; num++)
		{
			int len = test(num);
			if (len > bestLen)
			{
				bestLen = len;
				bestNum = num;
			}
		}
		
		return bestNum;
	}
	
	private int test(final int div)
	{
		int current = 1;

		// Keep track of when each divisor was last seen.
		Map<Integer, Integer> lastSeen = new HashMap<Integer, Integer>();
		
		for(int i = 1; ; i++)
		{
			// If this div was seen before:
			if (lastSeen.containsKey(current)) 
			{
				// Interval between two occurrences.
				int whenLastSeen = lastSeen.get(current);
				int len = i - whenLastSeen;
				return len;				
			}
			
			lastSeen.put(current, i);
			
			// Simulate one step of long division:
			current *= 10;
			
			int d = current / div;
			current -= div * d;
		}
	}

	private static final int LIMIT = 1000;
}

