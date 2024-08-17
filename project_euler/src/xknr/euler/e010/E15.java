package xknr.euler.e010;

import xknr.euler.Solution;
import xknr.euler.util.Util;

/** 
 * Number of distinct paths reaching a point:
 * 1 1 1
 * 1 2 3
 * 1 3 6
 * 
 * For each cell, number of paths reacing the cell is 
 * the sum of the number for the above and left cells.
 * This is equivalent to Pascal's triangle.
 * 
 *     1 
 *    1 1
 *   1 2 1
 *  1 3 3 1
 * 1 4 6 4 1
 * 
 * Our square grid is a diamond shaped section of the Pascal's 
 * triangle.
 * 
 * c'th term of the n'th row is found by combinatorial formula (n c)
 * 
 * which is 
 * 
 *     n!
 * -----------
 *  (n-c)!  c!
 * 
 * 
 * n! / (n-c)! = n * (n-1) * ... * (c+1)
 * 
 * i.e.
 * 
 * 40! / 20! = 40 x 39 x ... x 21
 * 
 */
public class E15 extends Solution
{
	public E15(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{
		// Numerator and denominator of the running fraction. 
		// It will be used as accumulators for the factorial terms.
		
		// Initialize fraction to 1/1.
		long num = 1, den = 1;
		
		for(int i = 1; i <= 20; i++)
		{
			// [21, 40] 
			int termNum = i + 20;
			
			// [1, 20]
			int termDen = i; 
						
			// Multiply with the new term.
			num *= termNum;  
			den *= termDen;	
			
			// Reduce the current fraction.
			long g = Util.gcd(num, den);
			num /= g;
			den /= g;
		}
		
		return num;
	}
}
