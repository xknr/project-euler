package xknr.euler.e030;

import xknr.euler.Solution;
import xknr.euler.util.Util;

public class E36B extends Solution
{
	public E36B(boolean doPrint) {
		super(doPrint);
	}
	
	/**
	 * Alternate version. 
	 * Generates all palindromes < LIMIT
	 * then checks them for being a palindrome base 2.
	 * 
	 * @return
	 */
	public long solve()
	{		
		int sum = 0;
		
		int mask = 10;
		for(int a = 1; a < 1000; a++)
		{			
			if (a >= mask)
				mask *= 10;
			
			// i will take the values {1, 10}
			// 
			// i=1 will include all digits of a:
			// a = 78 -> pal = 7887
			//
			// i=10 will ignore a's last digit:
			// a = 78 -> pal = 787
			
			for(int i = 1; i <= 10; i *= 10)
			{
				int pal = addReverse(a, mask / i, a / i);
				if (E36.isPalindromicBase2(pal))
				{
					println(a, pal);
					sum += pal;
				}
			}
		}
		
		return sum;
	
	}
	public static int addReverse(int start, int mask, int x)
	{
		for(; mask >= 10; mask /= 10, x /= 10) 
			start = start * 10 + x % 10;
		return start;
	}

	public static final int LIMIT = 1_000_000;

}