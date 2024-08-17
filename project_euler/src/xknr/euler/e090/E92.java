package xknr.euler.e090;

import xknr.euler.Solution;

public class E92 extends Solution
{
	public E92(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{
		int count = 0;
		
		boolean yield1[] = new boolean[LIMIT];
		
		for(int x = 1; x < LIMIT; x++) 
		{			
			int n = x;
			
			while(n != 1 && n != 89) 
			{
				n = sumOfDigitSquares(n);
			}
			
			if (n == 1) 
			{
				yield1[x] = true;
			} 
			else 
			{
				count++;
			}
		}
		
		return count;
	}

	public static int sumOfDigitSquares(int n) 
	{
		int sum = 0;
		
		for(; n > 0; n /= 10) 
		{
			final int d = n % 10;
			sum += d * d;
		}
		
		return sum;
	}

	private static final int LIMIT = 10_000_000;
}
