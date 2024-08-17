package xknr.euler.e060;

import java.util.List;

import xknr.euler.Solution;
import xknr.euler.util.Util;

public class E69 extends Solution
{
	public E69(boolean doPrint) {
		super(doPrint);
	}

	/**
	 * 
	 * We want the number which produces the lowest value for Euler's totient
	 * function. The terms in Euler's totient function are in the form of:
	 * (p-1)/p. Each term is less than 1 and contributes to reduce the result.
	 * The lower p is, the lower the contribution is.
	 * 
	 * For example, if we look at numbers with exactly 3 distinct primes, the
	 * ones with distinct prime factors {2,3,5} will yield the lowest value for
	 * Euler's totient function.
	 * 
	 * All other numbers with 3 distinct prime factors like {2,3,7} or {3,5,7}
	 * will yield a higher value.
	 * 
	 * {2,3,5,7} will yield the lowest value amongst numbers with 4 distinct
	 * prime factors. And it's result is even lower than {2,3,5}.
	 * 
	 * Therefore all we have to do is to multiply 2x3x5x7x... as long as the
	 * product is lower than the limit.
	 * 
	 */
	public long solve()
	{
		List<Integer> primes = Util.generatePrimes(PRIME_LIMIT);		

		int i = 0;
		
		int product = 1;
		
		while (product * primes.get(i) < LIMIT)
		{
			product *= primes.get(i);
			i++;
		}

		return product;
	}
	
	private static final int LIMIT = 1000_000;
	private static final int PRIME_LIMIT = 1000;
}

