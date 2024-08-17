package xknr.euler.e090;

import xknr.euler.Solution;

public class E92B extends Solution
{
	public E92B(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{
		byte cached[] = new byte[LIMIT];
		
		cached[1] = 1;
		cached[89] = 89;
		
		int count = 0;
				
		for(int x = 1; x < LIMIT; x++) 
		{
			if (f(cached, x) == 89) 
			{
				count++;
			}
		}
		
		return count;
	}

	static byte f(byte[] cached, int n) 
	{
		if (cached[n] != 0) 
		{
			return cached[n];
		}
		
		byte result = f(cached, E92.sumOfDigitSquares(n));
		cached[n] = result;
		return result;
	}

	private static final int LIMIT = 10_000_000;
}

