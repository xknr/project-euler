package xknr.euler.e020;

import java.math.BigInteger;

import xknr.euler.Solution;
import xknr.euler.util.Util;

/**
 * Brute force.
 * Divide by 10 when possible.
 */
public class E20 extends Solution
{
	public E20(boolean doPrint) {
		super(doPrint);
	}
	
	public long solve()
	{		
		BigInteger prod = Util.B1;
		
		// For all numbers [2, 100]
		for(int i = 2; i <= 100; i++)
		{
			// Multiply with that number.
			prod = prod.multiply(Util.B(i));
			
			// Get rid of 0s at the end.
			while(divides(prod, B10))
			{
				prod = prod.divide(B10);
			}
		}

		return digitSum(prod.toString());		
	}

	/**
	 * Does a divide b.
	 * @param a Dividend.
	 * @param b Divisor.
	 * @return Does a divide b.
	 */
	public static boolean divides(BigInteger a, BigInteger b) 
	{
		return a.remainder(b).equals(Util.B0);
	}

	public static long digitSum(String s) 
	{
		int sum = 0;
		
		for(char c: s.toCharArray()) 
		{
			int d = c - '0';
			assert d >= 0 && d <= 9;
			
			sum += d;
		}
		
		return sum;
	}

	private static final BigInteger B10 = Util.B(10);	
}

