package xknr.euler.e050;

import java.math.BigInteger;

import xknr.euler.Solution;
import xknr.euler.util.Util;

public class E55 extends Solution
{	
	public E55(boolean doPrint) {
		super(doPrint);
	}

	/**
	 * Try each integer [1, 10000)
	 * @return Number of lychrel numbers found. 
	 */
	public long solve()
	{		
		int count = 0;
		
		for(int x = 1; x < 10000; x++) 
		{
			if (isLychrel(x)) 
			{
				count++;
			}
		}
		
		return count;		
	}
	
	/**
	 * Tries to find a palindrome while appling the transform to generate 
	 * lychrel numbers repeatedly.
	 * 
	 * When a palindrome is found before 50 iterations the number is 
	 * proven not to be a lychrel number. Else it is assumed to be a 
	 * lychrel number.  
	 * 
	 * @param x The initial number.
	 * @return
	 */
	public static boolean isLychrel(int x)
	{		
		BigInteger c = Util.B(x);
		
		for(int count = 0; count < ITERATIONS; count++)
		{
			c = lychrelTransform(c);
			if (Util.isPalindromic(c)) 
				return false;
		}
		
		return true;
	}

	public static BigInteger lychrelTransform(BigInteger c)
	{
		return c.add(Util.reverse(c));
	}

	// The exact wording is something like this: 
	// "...become a palindrome in less than fifty iterations"
	private static final int ITERATIONS = 50 - 1;
}
