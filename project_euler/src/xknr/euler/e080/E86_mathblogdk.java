package xknr.euler.e080;

import xknr.euler.Solution;
import xknr.euler.util.Maartinus;

public class E86_mathblogdk extends Solution 
{
	public E86_mathblogdk(boolean doPrint) {
		super(doPrint);
	}

	public int solve()
	{		
		int l = 2;		 
		for(int count = 0; count < TARGET; ) 
		{
		    l++;
		    for(int wh = 3; wh <= 2 * l; wh++) 
		    {
		    	final int sq = wh * wh + l * l;
		    	if (Maartinus.isSquare(sq)) 
		    	{
		            count += (wh <= l) ? wh / 2 : 1 + (l - (wh+1)/2);
		        }
		    }
		}		
		
		return l;		
	}

	private static final int TARGET = 1000000;
}

