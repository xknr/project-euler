package xknr.euler.e010;

import xknr.euler.Solution;
import xknr.euler.util.Util;

public class E12 extends Solution
{
	public E12(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{
		/*
		 * Number of divisors = powers of every distinct prime factor + 1 multiplied.
		 * 
		 * 9800 = 2^3 * 5^2 * 7^2
		 * has 4 * 3 * 3 = 36 divisors.
		 * 
		 * A triangular number:
		 * T(n) = (n + 1) * n / 2
		 * 
		 * n and (n + 1) are coprimes.  
		 * There are no shared prime factors.
		 *  
		 * Simply multiply the number of divisors of the two terms. 
		 * 
		 * divisors(T(n)) = divisors(n / 2) * divisors(n + 1)
		 */
		
		// Divisor count from (n-1).
		int prev = 1;
		
		for(int n = 1; ; n++)
		{
			int term = n + 1;

			// If even, 
			if (term % 2 == 0)
			{
				// Then remove a 2.
				term /= 2;
			}
			
			int count = countDivisors(term);
			
			int combined = count * prev;
			
			// If found one,
			if (combined > TARGET) 
			{
				// Return T(n).
				return Util.triangular(n);
			}
			
			prev = count;
		}		
	}

	/**
	 * Counts the number of all divisors for a given number
	 * (including 1 and the number itself).
	 * 
	 * This method evaluates 2 and all odd numbers as
	 * prime factor candidates, by repeatedly trying to divide x.
	 * If a candidate can no longer divide x, the next candidate 
	 * is selected. 
	 * 
	 * At any moment, what remains of x has no divisors < candidate. 
	 * If candidate * d = x and d < candidate, thi means that x has 
	 * no remaining divisors other than itself. This means we can stop 
	 * when candidate > sqrt(x). 
	 * 
	 * @param x The number to count divisors of.
	 * @return Number of divisors calculated.
	 */
	public int countDivisors(int x)
	{
		// We will multiply prod with count whenever cand is updated.
		int prod = 1;
	
		// Current prime factor candidate.
		int cand = 2;
		
		// 1 + Number of times candidate divided x.
		int count = 1;
		
		while(x > 1) // While not depleted,
		{	
			int d = x / cand; // Try to divide.			
			
			if (d * cand == x) // If it divides, 
			{
				// Found a prime factor.
				count++; // Increment found counter.
				x = d;    // Update remaining.
			}
			else // If it couldn't divide,
			{				
				prod *= count; // Occurences of cand + 1.   
				count = 1;     // Reset the counter.
				
				// It still works when there were no occurences 
				// of p because count starts from 1.
				
				if (d < cand) // cand >= sqrt(current x), 
				{
					// No more prime factors are possible.
					// Current value of x is the last prime factor.
					cand = x;
				}
				else // cand < sqrt(current x)
				{
					// Try the next number in the sequence: 2, 3, 5, 7, 9...
					cand += (cand & 1) + 1; 
				}				
			}
		}
		
		// Evaluate the last candidate.
		prod *= count;
		
		return prod;
	}
	
	private static final int TARGET = 500;
}



