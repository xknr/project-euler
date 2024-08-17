package xknr.euler.e050;

import java.math.BigInteger;

import xknr.euler.Solution;
import xknr.euler.util.Util;

public class E56 extends Solution
{
	public E56(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{		
		int best = 0;
		
		for(int a = 1; a < 100; a++) 
		{			
			BigInteger A = Util.B(a), P = A;

			for(int b = 2; b < 100; b++) 
			{
				P = P.multiply(A);
				best = Math.max(best, Util.sumOfDigits(P));
			}
		}
		
		return best;
	}	
}
