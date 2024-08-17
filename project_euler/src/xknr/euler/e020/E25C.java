package xknr.euler.e020;

import static xknr.euler.e020.E25B.*;

import xknr.euler.Solution;

/**
 * Analytical solution.
 * 
 * 1000 ~= log10(first) + n * log10(G)
 * 
 * Or, find the first n which is > 1000. 
 * 
 * @author kk
 */
public class E25C extends Solution
{
	public E25C(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{		
		double est = Math.log(FIRST) * rLog10;
		return (int)Math.round((TARGET - est - 1) / incr + FIRST_ORD);
		
		// Round or ceil?
	}

}