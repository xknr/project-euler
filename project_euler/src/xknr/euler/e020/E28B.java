package xknr.euler.e020;

import xknr.euler.Solution;

public class E28B extends Solution
{	
	public E28B(boolean doPrint) 
	{
		super(doPrint);
	}	

	/**
	 * Generate the red numbers in increasing order.
	 * Alternate method.
	 * @return
	 */
	public long solve()
	{		
		// Rotated the order of operations,
		// changed starting values to skip the first element.
		// No final operation needed.

		long sum = 1;
		long x = 2;
		int stepSize = 1;
			
		for(int i = 0; i < numCircles; i++)
		{
			x += stepSize; sum += x;		println(x);
			stepSize++;
			
			x += stepSize; sum += x; 		println(x);
			x += stepSize; sum += x;		println(x);
			stepSize++;
			
			x += stepSize; sum += x - 1; 	println(x - 1);
		}
				
		return sum;		
	}	
		
	// Parameter given in the question:
	private int edge = 1001;
	
	// There are half as many circles.
	private int numCircles = edge / 2;
}

