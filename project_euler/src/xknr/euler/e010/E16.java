package xknr.euler.e010;

import java.math.BigInteger;

import xknr.euler.Solution;
import xknr.euler.util.Util;

/**
 * Brute force.
 */
public class E16 extends Solution
{
	public E16(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{
		BigInteger x = Util.B(2).pow(1000);

		return sumDigits(x.toString());
	}

	public static long sumDigits(String s) 
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
}
