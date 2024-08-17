package xknr.euler.e010;

import xknr.euler.Solution;


public class E13B extends Solution
{
	public E13B(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{		
		/*
		 * Proof:
		 * 
		 * Maximum change the sum for nth place can make on 
		 * the sum for (n+1)'th place can be 99.
		 * 
		 * Maximum change the sum for nth place can make on 
		 * the sum for (n+2)'th place can be 10:
		 * 
		 * When sum(n+1) = 70
		 * If carry from n  =  0 -> carry from n+1 = 7
		 * If carry from n  = 99 -> carry from n+1 = 16
		 * 
		 * When sum(n+1) = 71
		 * If carry from n  =  0 -> carry from n+1 = 7
		 * If carry from n  = 99 -> carry from n+1 = 17
		 * 
		 * When sum(n+1) = 79
		 * If carry from n  =  0 -> carry from n+1 = 7
		 * If carry from n  = 99 -> carry from n+1 = 17
		 * 
		 * Maximum change the sum for nth place can make on 
		 * the sum for (n+2)'th place can be 1.
		 * 
		 * When sum(n+2) = 70
		 * If carry from n  =  0 -> carry from n+1 = 7
		 * If carry from n  = 10 -> carry from n+1 = 8
		 * 
		 * When sum(n+2) = 79
		 * If carry from n  =  0 -> carry from n+1 = 7
		 * If carry from n  = 10 -> carry from n+1 = 8
		 * 
		 * nth place cannot affect beyond (n+3)'th place.
		 * 
		 */
		
		
		String[] lines = E13Data.DATA;
		
		long sum = 0;
		for(String line: lines)
		{
			// Take first 13 digits
			String head = line.substring(0, 13);
			long term = Long.parseLong(head);
			sum += term;
		}
		
		// Take first 10 digits
		while(sum >= _1E10) 
		{
			sum /= 10;
		}
		
		return sum;
	}

	private static final long _1E10 = 10_000_000_000L;
}


