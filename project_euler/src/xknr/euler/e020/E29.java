package xknr.euler.e020;


import java.util.HashSet;
import java.util.Set;

import xknr.euler.Solution;

public class E29 extends Solution
{	
	public E29(boolean doPrint) {
		super(doPrint);
	}

	/**
	 * If B is not a power of A,
	 * then no powers of B can be equal to a power of A.
	 * @return
	 */
	public long solve()
	{
		boolean[] done = new boolean[LIM + 1];
		
		int count = 0;		
		
		int a;

		for(a = 2; a * a <= LIM; a++)
		{
			// If not already processed as a power of a smaller number, 
			if (!done[a])
			{
				println(a);
				
				// Keep track of unique powers.
				Set<Integer> set = new HashSet<Integer>();
			
				// Iterate over all powers of a <= LIM.			
				for(int power = a, p = 1; power <= LIM; power *= a, p++)
				{
					println(power,  a);
					done[power] = true;
					addPowers(set, p);
				}
				
				println(set);
				count += set.size();
			}
		}
		
		for(; a <= LIM; a++) 
		{
			if (!done[a])
			{
				// All 99 powers are unique.
				count += LIM - 2 + 1;
			}
		}
		
	
		return count;
	}

	private void addPowers(Set<Integer> set, int p) 
	{		
		// 4^50 -> 2^100 seen before as 2^100
		// 8^33 -> 2^99  seen before as 2^99
		// 4^51 -> 2^102 first time
		// 8^34 -> 2^102 seen before as 4^51
		
		for(int b = 2; b <= LIM; b++)
		{ 
			set.add(b * p);
		}
	}
	
	public static final int LIM = 100;
}
