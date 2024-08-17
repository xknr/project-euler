package xknr.euler.e040;

import java.util.Arrays;
import java.util.List;

import xknr.euler.Solution;
import xknr.euler.prime.Primes3;

public class E49 extends Solution
{
	public E49(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{			
		sieve = Primes3.makeSieve(MAX_PRIME);
		primes = Primes3.collect(sieve, MAX_PRIME, null);

		for(int i = 0; ; i++)
		{
			int pi = primes.get(i);
			
			if (pi < 1000) continue; // Must be 4-digit.
			if (pi == 1487) continue; // Given in question.
			
			for(int j = i + 1; j < primes.size(); j++) 
			{
				int pj = primes.get(j);
				
				if (sameDigits(pi, pj)) 
				{
					int pk = pj + (pj - pi); // 3rd prime in the group.
					if (pk < LIM && sameDigits(pi, pk) && Primes3.isPrime(sieve, pk))
					{
						sieve = null;
						primes = null;
						return contruct(pi, pj, pk);
					}
				}
			}
		}	
	}

	public static long contruct(int pi, int pj, int pk)
	{
		return pi * 10000L * 10000L + pj * 10000 + pk;
	}

	public static boolean sameDigits(int a, int b)
	{
		assert a >= 1000 && a < 10000;
		assert b >= 1000 && b < 10000;
		
		int[] count = new int[10];		

		// Increment counter for each digit of a
		for(; a > 0; a /= 10)
		{
			count[a % 10]++;
		}

		// Decrement counter for each digit of a
		for(; b > 0; b /= 10)
		{
			count[b % 10]--;
		}
		
		// Check all counters 0
		for(int c: count)
		{
			if (c != 0)
			{
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean sameDigitsStr(int a, int b)
	{
		assert a >= 1000 && a < 10000;
		assert b >= 1000 && b < 10000;
		
		char[] ca = Integer.toString(a).toCharArray();
		char[] cb = Integer.toString(b).toCharArray();
	
		Arrays.sort(ca);
		Arrays.sort(cb);
		
		for(int i = 0; i < 4; i++) 
		{
			if (ca[i] != cb[i]) 
			{
				return false;
			}
		}
		
		return true;
	}

	public static final int LIM = 10000;
	public static final int MAX_PRIME = LIM - 1;
	
	private long[] sieve;
	private List<Integer> primes;
}

