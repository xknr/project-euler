package xknr.euler.e020;

import xknr.euler.Solution;

public class E28 extends Solution
{	
	public E28(boolean doPrint) 
	{
		super(doPrint);
	}	

	/**
	 * Generate the red numbers in increasing order.
	 * @return
	 */
	public long solve()
	{
		// We will use a few variables.
		long sum = 0;
		long x = 1;
		int stepSize = 1;
		
		// Apply the pattern repeatedly.
		for(int i = 0; i < numCircles; i++)
		{
			x += stepSize; sum += x - 1; 	println(x - 1);
			x += stepSize; sum += x;		println(x);
			stepSize++;
			
			x += stepSize; sum += x; 		println(x);
			x += stepSize; sum += x;		println(x);
			stepSize++;
		}
		
		// A final step is needed.
		x += stepSize; sum += x - 1; 		println(x - 1);
				
		return sum;		
	}

	// Parameter given in the question:
	private int edge = 1001;
	
	// There are half as many circles.
	private int numCircles = edge / 2;
}

