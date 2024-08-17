package xknr.euler.e030;

import xknr.euler.Solution;

public class E34 extends Solution
{
	public E34(boolean doPrint) {
		super(doPrint);
	}

	/* 
	 * 9! = 362880 
	 * f(9999999) = 2540160, which is 7 digits, 
	 * f(99999999) = 2903040, which is still 7 digits.
	 * Which means we don't have to check >= 8 digits.
	 */
	public long solve()
	{	
		int limit = 7 * factorials[9];
		
		int sumAll = 0;
		
		for(int x = 3; x <= limit; x++) 
		{
			if (f(x) == x) 
			{
				sumAll += x;
			}			
		}
		
		return sumAll;
	}	
	
	/**
	 * Sum of factorials of each digit of x.
	 */
	public static int f(int x)
	{
		int result = 0;
		
		for(int t = x; t > 0; t /= 10)
		{
			int d = t % 10;
			result += factorials[d];
			
			// cancel
			if (result > x)
			{
				return 0;
			}
		}
		return result;
	}

	// Factorials of each digit: 
	private static int factorials[] = new int[10];
	
	static {
		calcDigitFactorials(factorials, 0);
	}

	public static void calcDigitFactorials(int[] arr, int offset)
	{
		arr[offset++] = 1; // Special value
		arr[offset++] = 1; // Must initialize the first one.

		for(int i = offset; i < arr.length; i++) 
		{
			arr[i] = arr[i - 1] * i;
		}
	}
}