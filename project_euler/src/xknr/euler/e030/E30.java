package xknr.euler.e030;

import xknr.euler.Solution;

public class E30 extends Solution
{
	public E30(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{		
		int sumAll = 0;
		
		for(int a = 2; a < LIMIT; a++) 
		{
			if (sumOfFifthPowersOfDigits(a) == a) 
			{
				println(a);
				sumAll += a;
			}
		}

		return sumAll;
	}

	/**
	 * Sum of fifth power of each digit in x.
	 */
	public static int sumOfFifthPowersOfDigits(int x)
	{
		int sum = 0;
		
		while(x > 0)
		{
			// Find the next digit:
			int d = x % 10;
			
			// Add its fifth power to sum:
			sum += d * d * d * d * d;
			
			// Remove the last digit:
			x /= 10;
		}
		
		return sum;
	}

	/*
	 * Max possible sum for 6 digit number = f(999999)  = 6*9^5 = 6*59049 = 354294  
	 * Max possible sum for 7 digit number = f(9999999) = 7*9^5 = 7*59049 = 413343
	 * 
	 * The number can have at most 6 digits. 
	 */
	private static final int LIMIT = 1000_000;
}