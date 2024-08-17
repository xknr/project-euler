package xknr.euler.e000;

import xknr.euler.Solution;

public class E09 extends Solution
{
	public E09(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{
		return pyth_tree(INITIAL_U, INITIAL_V);
	}

	/**
	 * Search the tree of pyth. triplets. 
	 * 
	 * https://en.wikipedia.org/wiki/Tree_of_primitive_Pythagorean_triples#Alternative_methods_of_generating_the_tree
	 * 
	 * @param u Parameter 1
	 * @param v Parameter 2
	 * @return Product of the length of three edges the triplet found.
	 */
	private long pyth_tree(int u, int v)
	{				
		// Generate triplet from u, v.
		final int u2 = u * u, v2 = v * v;
		
		final int a = u * v;
		final int b = (u2 - v2) / 2;
		final int c = (u2 + v2) / 2;

		int perimeter = a + b + c;

		// Termination condition.
		// This tree's leaves always seem to have a 
		// larger perimeter than their parent.
		if (perimeter > TARGET)
		{
			return 0;
		}
		
		if (TARGET % perimeter == 0)
		{
			int k = TARGET / perimeter;
			return (a * k) * (b * k) * (c * k);
		}

		// Continue generating recursively.
		
		// First branch.
		long r = pyth_tree(2 * u - v, u); 
		
		if (r != 0)
		{
			return r;
		}
		
		// Second branch.
		r = pyth_tree(2 * u + v, u);
		
		if (r != 0)
		{
			return r;
		}
		
		// Third branch.
		return pyth_tree(u + 2 * v, v);
	}

	private static final int TARGET = 1000;		
	private static final int INITIAL_U = 3;
	private static final int INITIAL_V = 1;
}
