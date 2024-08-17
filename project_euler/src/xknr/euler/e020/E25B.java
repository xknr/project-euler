package xknr.euler.e020;

import xknr.euler.Solution;

public class E25B extends Solution
{
	public E25B(boolean doPrint) {
		super(doPrint);
	}

	/**
	 * Multiply by golden ratio to find successive Fibonacci numbers.
	 * Instead of multiplying we can increment a logarithm by a fixed amount.
	 * Log 10 can be used to find the number of digits. 
	 * Start from 11th Fibonacci number to reduce error.
	 * @return
	 */
	public long solve()
	{		
		int ord = FIRST_ORD, first = FIRST;
		
		// log first base 10
		double est = log10(first);

		// Approximately how many times we have to iterate.
		println((TARGET - est - 1) / incr + FIRST_ORD);

		while(true)
		{
			// Convert log 10 estimate to number of digits.
			int numDigits = (int)Math.floor(est) + 1;

			println(ord, est, incr, numDigits);
			
			// We hit target.
			if (numDigits == TARGET)
			{
				return ord;
			}
			
			est += incr;
			ord++;
		}
		
	}

	private static final double G = 0.5 + Math.sqrt(5) * 0.5; 
	public static final double rLog10 = 1 / Math.log(10); 

	public static double log10(double x) {
		return Math.log(x) * rLog10;
	}

	// log golden ratio base ten
	public static final double incr = log10(G);

	// These initial values were proven to work until 
	// the ~10000th fibo number. 
	public static final int FIRST_ORD = 11;
	public static final int FIRST = 89;
	
	public static final int TARGET = 1000;
}