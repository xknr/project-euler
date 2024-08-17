package xknr.euler.e020;


import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import xknr.euler.Solution;
import xknr.euler.util.Util;

/*
 * Alternative solution based on prime number signatures.
 * 
 * Fundamental theorem of arithmetic states that every integer 
 * greater than 1 can be represented uniquely as a product of 
 * prime numbers.
 * 
 * Such a complex solution is not really necessary for this problem,
 * as only a power of a number can generate a similar signature.
 * 
 */
public class E29B extends Solution
{
	public E29B(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{		
		Set<String> set = new HashSet<String>();
		
		// Iterate over all possible a, b values.
		for(int a = 2; a <= LIM; a++)
		{
			Map<Long, Integer> primeFactors = 
				Util.countDPF((long)a);
			
			Map<Long, Integer> sorted = 
				new TreeMap<Long, Integer>(primeFactors);
			
			for(int b = 2; b <= LIM; b++) 
			{
				String hash = genHash(sorted, b);
				set.add(hash);
			}
		}
		
		return set.size();		
	}

	/**
	 * Generate a hash string of this set of powers. 
	 * @param pairs Sorted pairs of (prime, power)
	 * @param b number to multiply each power with.
	 * @return
	 */
	public String genHash(Map<Long, Integer> pairs, int b)
	{
		StringBuilder sb = new StringBuilder();
		
		boolean first = true;
		
		for(Entry<Long, Integer> pair: pairs.entrySet())
		{					
			if (!first)
			{
				sb.append("x");
			}
			
			sb.append(pair.getKey());
			sb.append("^");
			sb.append(pair.getValue() * b);
			
			first = false;
		}
		
		return sb.toString();
	}
	
	public static final int LIM = 100;
}