package xknr.euler.e070;

import xknr.euler.Solution;

/**
 * 
 * https://projecteuler.net/problem=75
 * 
 * https://en.wikipedia.org/wiki/Pythagorean_triple
 * https://en.wikipedia.org/wiki/Formulas_for_generating_Pythagorean_triples
 * https://en.wikipedia.org/wiki/Tree_of_primitive_Pythagorean_triples
 *
 */


public class E75B extends Solution
{
	public E75B(boolean doPrint) {
		super(doPrint);
	}

	public int solve() 
	{
		counters = new int[LIMIT + 1]; 

		traverseTree(3, 4, 5);
		
		int result = count1s(counters);
		
		counters = null;
		
		return result;
	}

	private void traverseTree(int a, int b, int c)
	{
		int sum = a + b + c;
		
		//We're relying on every triplet's sum being 
		//greater than its predecessors.
		
		//no need to continue
		if (sum > LIMIT) 
		{
			return;
		}
		
		for(int f = 1; ; f++) 
		{
			int fsum = sum * f;
			if (fsum > LIMIT) break;
			counters[fsum]++;
		}
		
		format("%s %s %s %s\n", a, b, c, sum);

		//assert (Help.gcd(a, b) != 1); 
		
		//these terms are used more than once:
		final int a2 = a << 1;
		final int b2 = b << 1;
		final int c2 = c << 1;
		final int c3 = c2 + c;
		
		final int b2c2 = b2 + c2;
		final int bc2 = b + c2;
		final int b2c3 = b2 + c3;
		
		final int A1 = -a	+b2c2;
		final int A2 = +a	+b2c2;
		final int A3 = +a 	-b2	+ c2;
		final int B1 = -a2 	+bc2;
		final int B2 = +a2	+bc2;
		final int B3 = +a2 	-b	+ c2;
		final int C1 = -a2 	+b2c3;
		final int C2 = +a2	+b2c3;
		final int C3 = +a2 	-b2	+ c3;
		
		traverseTree(A1, B1, C1);
		traverseTree(A2, B2, C2);
		traverseTree(A3, B3, C3);
	}

	public static int count1s(int[] counters) 
	{
		int result = 0;
		
		for(int i = 0 ; i < counters.length; i++)
		{
			if (1 == counters[i])
			{
				result++;
			}
		}
		
		return result;
	}
	
	private static final int LIMIT = 1500 * 1000;
	private int[] counters; 
}
