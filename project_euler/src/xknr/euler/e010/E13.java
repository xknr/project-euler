package xknr.euler.e010;

import java.math.BigInteger;

import xknr.euler.Solution;
import xknr.euler.util.Util;

public class E13 extends Solution
{
	public E13(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{
		String[] data = E13Data.DATA;

		BigInteger sum = Util.B0;
		
		for(String line: data) 
		{
			BigInteger term = new BigInteger(line);
			sum = sum.add(term);
		}
		
		return Long.parseLong(prefix(sum, PREFIX_LEN));
	}

	public static String prefix(BigInteger sum, int n) 
	{
		return sum.toString().substring(0, n);
	}

	private static final int PREFIX_LEN = 10;
}


