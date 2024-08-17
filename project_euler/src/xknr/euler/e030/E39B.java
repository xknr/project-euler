package xknr.euler.e030;

import xknr.euler.Solution;

public class E39B extends Solution
{
	public E39B(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{
		perimeters = new int[LIMIT + 1];
		
		pyth_tree(3, 1);
		
		return highestSeen();
	}

	private int highestSeen() 
	{
		int bestInd = 0;
		for(int i = 0; i < perimeters.length; i++)
		{
			if (perimeters[i] > perimeters[bestInd])
			{
				bestInd = i;
			}
		}
		return bestInd;
	}	
	
	/**
	 * https://en.wikipedia.org/wiki/Tree_of_primitive_Pythagorean_triples#Alternative_methods_of_generating_the_tree
	 */
	private void pyth_tree(int u, int v)
	{				
		// Generate triplet from u, v.
		final int u2 = u * u, v2 = v * v;
		int a = u * v;
		int b = (u2 - v2) / 2;
		int c = (u2 + v2) / 2;

		int perimeter = a + b + c;
		
		// Termination condition.
		// This tree's leaves always seem to have a larger perimeter than their parent.
		if (perimeter > LIMIT)
		{
			return;
		}
		
		println(a, b, c, perimeter);
		
		// Mark all multiples
		for(int p = perimeter; p <= LIMIT; p += perimeter) 
		{
			perimeters[p]++;
		}
		
		// Continue generating recursively:
		pyth_tree(2 * u - v, u); 
		pyth_tree(2 * u + v, u); 
		pyth_tree(u + 2 * v, v);
	}

	public static final int LIMIT = 1000;
	private int[] perimeters;
}