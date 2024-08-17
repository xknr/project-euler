package xknr.euler.e040;

import xknr.euler.Solution;

public class E48 extends Solution
{
	public E48(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{
		long sum = 0;
		
		for(long x = 1; x <= 1000; x++) 
		{			
			long p = 1;

			for(int i = 0; i < x; i++)
			{
				p = (p * x) % MASK;
			}
			
			sum = (sum + p) % MASK;
		}

		return sum;
	}

	private static final long MASK = 10_000_000_000L;
}
