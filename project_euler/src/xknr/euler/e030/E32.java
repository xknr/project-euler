package xknr.euler.e030;

import java.util.HashSet;
import java.util.Set;

import xknr.euler.Solution;
import xknr.euler.e020.E24;
import xknr.euler.util.Util;

public class E32 extends Solution
{
	public E32(boolean doPrint) {
		super(doPrint);
	}

	/**
	 * Only possible interpretations are
	 * 
	 * a x bbbb = cccc
	 * 
	 * and
	 * 
	 * aa x bbb = cccc
	 * 
	 */		
	public long solve()
	{		
		int[] comb = new int[9];

		fill(comb, 1); // Fill with [1..9]

		Set<Integer> found = new HashSet<Integer>();		

		// Iterate over every permutation:
		do {
			interpret(comb, found);
		} while(Util.nextPerm(comb, Util.comp));

		return sum(found);
	}
	
	public static void interpret(int[] comb, Set<Integer> found)
	{		
		// If an interpretation fails then 0 will be added to set,
		// which won't change the result.
		
		found.add(interpret414(comb));
		found.add(interpret423(comb));
	}

	public static int interpret423(int[] comb)
	{
		// Try to interpret as aa x bbb = cccc
		int c = (int)Util.read(comb, 0, 4);
		int a = (int)Util.read(comb, 4, 2);
		int b = (int)Util.read(comb, 6, 3);
		
		if (a * b == c) 
		{
			return c;
		}
		
		return 0;
	}

	public static int interpret414(int[] comb)
	{
		// Try to interpret as a x bbbb = cccc
		int c = (int)Util.read(comb, 0, 4);
		int a = (int)Util.read(comb, 4, 1);
		int b = (int)Util.read(comb, 5, 4);

		if (a * b == c)
		{
			return c;
		}
		
		return 0; 
	}

	/**
	 * Sum of all elements in a collection/iterable. 
	 * @param coll
	 * @return
	 */
	public static long sum(Iterable<Integer> coll)
	{
		int sum = 0;

		for(int x: coll) 
		{
			sum += x;
		}
		
		return sum;		
	}

	/**
	 * Fill with consequent values
	 * @param comb
	 * @param i
	 */
	public static void fill(int[] arr, int start)
	{
		for(int i = 0; i < arr.length; i++) 
		{
			arr[i] = i + start;
		}
	}

}
