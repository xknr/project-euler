package xknr.euler.e020;

import java.math.BigInteger;

import xknr.euler.Solution;
import xknr.euler.util.Util;

public class E25 extends Solution
{
	public E25(boolean doPrint) {
		super(doPrint);
	}
	
	public long solve()
	{
		int index = 1;

		BigInteger prev = Util.B0, curr = Util.B1;

		while(true)
		{
			int length = curr.toString().length(); 
			
			println(curr, length);
			
			if (LIMIT == length)
			{
				return index;
			}
			
			// (prev, curr) <- (curr, curr + prev)
			BigInteger next = prev.add(curr);
			prev = curr;
			curr = next;
			
			index++;
		}		
	}
	
	private static final int LIMIT = 1000;

}