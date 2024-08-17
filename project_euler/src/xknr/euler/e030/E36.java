package xknr.euler.e030;

import xknr.euler.Solution;
import xknr.euler.util.Util;

public class E36 extends Solution
{
	public E36(boolean doPrint) {
		super(doPrint);
	}
	
	public long solve()
	{
		int sum = 0;
		
		// For all integers in [1, LIMIT)
		for(int x = 1; x < LIMIT; x++) 
		{
			if (Util.isPalindrome(x) && isPalindromicBase2(x))
			{
				println(x);
				sum += x;
			}
		}
		
		return sum;
	}
	
	public static boolean isPalindromicBase2(int x) {
		return x == reverseBase2(x);
	}

	public static int reverseBase2(int t)
	{
		int sum = 0;
		while(t > 0) {
			sum *= 2;
			sum += t % 2;
			t /= 2;
		}
		return sum;
	}

	public static final int LIMIT = 1_000_000;

}