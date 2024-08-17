package xknr.euler.e050;

import java.util.*;

import xknr.euler.Solution;
import xknr.euler.miller.MillerRabin3;
import xknr.euler.prime.Primes3;
import xknr.euler.util.Util;

public class E51 extends Solution
{
	public E51(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{				
		for(int i = 0; i < primes.size(); i++) 
		{
			int p = primes.get(i);
			
			// Digit 1 is used like a wildcard.
			List<Integer> ones = digitLocations(p, 1);
			
			// items is how many of these 1s will be used like a wildcard.
			for(int items = 1; items <= ones.size(); items++)
			{
				int [] selected = new int[items];

				List<List<Integer>> perms = new ArrayList<List<Integer>>();
				
				permutation(ones, 0, selected, 0, perms);
				
				for(List<Integer> perm: perms) 
				{
					// How many primes do we get by replacing digits?
					int count = 0;
					
					int firstDigit = 0;
					if (perm.get(0) == 0) 
					{
						firstDigit = 1;
					}
					
					for(int d = firstDigit; d < 10; d++) 
					{
						int r = replace(p, perm, d);
						if (isPrime(r)) 
						{
							count++;
						}
					}
					
					if (count >= 8) 
					{
						return replace(p, perm, firstDigit);
					}
				}
			}
		}
		
		return 0;
	}

	/**
	 * Replace digits in a given integer at positions in given list with the given digit. 
	 * @param p Digits of this number will be changed
	 * @param positions List of positions to replace.
	 * @param d The new digit to replace with.
	 * @return The resulting integer after digit changes.
	 */
	private int replace(int p, List<Integer> positions, int d) 
	{
		final char newChar = (char)(d + '0');
		
		char[] cs = Integer.toString(p).toCharArray();
		
		for(int pos: positions) 
		{
			cs[pos] = newChar;
		}
		
		return Integer.parseInt(new String(cs));
	}

	/**
	 * Collect ordered permutations in outList.
	 */
	private void permutation(
		List<Integer> stock, 
		int firstUsableStock, 
		int[] selected, 
		int depth, 
		List<List<Integer>> outList) 
	{		
		if (depth < selected.length) {
			for(int i = firstUsableStock; i < stock.size(); i++)
			{
				selected[depth] = stock.get(i);
				permutation(stock, i + 1, selected, depth + 1, outList);
			}
		} 
		else 
		{
			outList.add(Util.asList(selected));
		}		
	}

	/**
	 * List of all indices in p where given digit is found.
	 * Except last digit.
	 * @param p The number to search in.
	 * @param d The digit to search.
	 * @return List of all such indices.
	 */
	private List<Integer> digitLocations(int p, int d)
	{		
		List<Integer> li = new ArrayList<Integer>();
		
		char[] chars = Integer.toString(p).toCharArray();
		
		int limit = chars.length - 1; // No need to check the last digit
		for(int i = 0; i < limit; i++) 
		{
			char c = chars[i];
			if (c == d + '0') 
			{
				li.add(i);
			} 
		}
		
		return li;
	}

	private boolean isPrime(int x)
	{		
		// If greater than our cache (sieve) size, 
		// then invoke primality test.
		
		if (x >= LIMIT) 
		{
			return MillerRabin3.isPrime(x);
		}
		
		return Primes3.isPrime(sieve, x);
	}	
	
	private static final int LIMIT = 1000_000;
	
	public static final int MAX_PRIME = LIMIT - 1;

	private long[] sieve = Primes3.makeSieve(MAX_PRIME);
	private List<Integer> primes = Primes3.collect(sieve, MAX_PRIME, null);
}

