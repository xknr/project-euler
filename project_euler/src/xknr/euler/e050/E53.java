package xknr.euler.e050;


import java.math.BigInteger;

import xknr.euler.Solution;
import xknr.euler.util.Util;

public class E53 extends Solution
{	
	public E53(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{
		int lim = 100;
		
		BigInteger[] fac = calcFactorials(lim);
		
		int count = 0;

		for(int n = 1; n <= lim; n++) 
		{
			for(int r = 0; r <= n; r++) 
			{
				BigInteger c = fac[n].divide(fac[r]).divide(fac[n-r]);
				if (c.compareTo(LIMIT) > 0) 
				{
					println(n, r, c);
					count++;
				}
			}
		}
		
		return count;
	}

	public static BigInteger[] calcFactorials(int lim) 
	{
		BigInteger fac[] = new BigInteger[lim + 1];
		BigInteger f = Util.B1;
		
		for(int i = 1; i <= lim; i++) 
		{
			f = f.multiply(BigInteger.valueOf(i));
			fac[i] = f;
		}
		
		fac[0] = BigInteger.ONE;
		
		return fac;
	}

	private static final BigInteger LIMIT = Util.B(1000_000);
}

