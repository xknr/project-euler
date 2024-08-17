package xknr.euler.e020.e23;

import xknr.euler.Solution;

public class E23B extends Solution
{	
	public E23B(boolean doPrint) {
		super(doPrint);
	}
	
	public long solve()
	{
		return new SolverB(E23A.LIM).solve();
	}

}

