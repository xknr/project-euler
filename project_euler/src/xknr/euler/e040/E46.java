package xknr.euler.e040;

import java.util.List;

import xknr.euler.Solution;
import xknr.euler.prime.Primes3;
import xknr.euler.util.Util;

public class E46 extends Solution
{

	public E46(boolean doPrint) {
		super(doPrint);
	}

	private List<Integer> primesList;
	
	// Keeps track of which numbers could be written as prime + 2 * x ^ 2:
	// Prime numbers will be marked so that we can skip.  
	private boolean[] writable = new boolean[LIMIT]; 	
	
	private int last_limit = 1;
	private int currPrimeInd = 0;
	private int currX = 1;

	private static final int LIMIT = 20000; 

	public long solve()
	{	
		primesList = Util.generatePrimes(LIMIT + 1);
		
		writable[1] = true;		
		markAllPrimes();

		int missing = 0;
		
		while(missing == 0) 
		{
			int nextPrime = primesList.get(currPrimeInd + 1);
			int nextTerm = term(currX + 1);

			// Select smaller of the two next components:
			int limit = Math.min(nextPrime, nextTerm);
			
			// Mark all composites that can be generated with the selected item.
			if (nextPrime < nextTerm) {
				markAllTerms(currX, nextPrime);
				currPrimeInd++;
			} else {
				markAllPrimes(nextTerm, currPrimeInd);
				currX++;
			}

			format("%d %d - %d\n", nextPrime, nextTerm, limit);
			
			// Have we missed any composite odd number in this interval?
			missing = checkMissing(limit);
		}

		writable = null;
		return missing;
	}

	private void markAllPrimes(int term, int pIndStop) {
		for(int pi = 0; pi <= pIndStop; pi++) {
			mark(term, primesList.get(pi));
		}			
	}

	private void markAllTerms(int xStop, int p) {
		for(int x = 1; x <= xStop; x++) {
			mark(term(x), p);
		}
	}

	private void markAllPrimes() 
	{
		for(int p: primesList) 
		{
			writable[p] = true;
		}
	}

	private Integer term(int x)
	{
		return 2 * x * x;
	}

	private int checkMissing(int limit)
	{
		for(int j = last_limit; j < limit; j += 2) 
		{
			if (!writable[j]) {
				return j;
			}
		}
		
		last_limit = nearestSmallerOdd(limit);
		return 0;
	}

	public static int nearestSmallerOdd(int x) {
		// return (x / 2) * 2 - 1;
		return (x & 0xfffffffe) - 1; 		
	}

	private void mark(int term, int prime)
	{
		writable[term + prime] = true;
	}

}

