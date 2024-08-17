package xknr.euler.e060;


import static xknr.euler.util.Util.B;
import static xknr.euler.util.Util.B0;
import static xknr.euler.util.Util.B1;

import java.math.BigInteger;

import xknr.euler.Solution;
import xknr.euler.util.BigFraction;
import xknr.euler.util.Util;

public class E65 extends Solution
{	
	public E65(boolean doPrint) {
		super(doPrint);
	}

	/**
	 * TODO Just process the numerator.
	 * This way calculation can be done in a forward manner.
	 */	
	public long solve()
	{
		// 1 2 3 4 5 6 7 8 9 10
		// 2 1 2 1 1 4 1 1 6 1 

		// Indexing starts with 1. The first value 2.
		// The terms in the form of 2k have an index of 3k.
		
		BigFraction f = new BigFraction(B1, B1);
		
		for(int i = 99; i >= 1; i--)
		{			
			BigInteger term;
			
			if (i == 1) 
			{
				term = B(2);
			}
			else if (i % 3 == 0) 
			{
				term = B(i * 2 / 3);
			} 
			else 
			{
				term = B1;
			}
			
			format("%2d %2d %s\n", i, term , f);
			
			f = f.reciprocal();
			
			f.addWholeNumber(term, true);
		}
		
		return Util.sumOfDigits( f.num() );
	}
}
