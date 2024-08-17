package xknr.euler.e020.e23;

import java.util.function.IntConsumer;

import xknr.euler.util.Util;

public class SolverA extends Solver
{		
	public SolverA(int lim) 
	{
		super(lim);
		
		isAbundant = new boolean[lim + 1];
	}
	
	@Override
	protected void collect(IntConsumer collector)
	{
		for(int n = 1; n <= lim; n++)
		{
			boolean isAb = calcIsAbundant(n);
			
			isAbundant[n] = isAb;
			
			if (isAb) 
			{
				collector.accept(n);
			}
		}
		
	}

	@Override
	protected boolean isAbundant(int b) {
		return isAbundant[b];
	}

	public static boolean calcIsAbundant(int n)
	{
		// Calculate proper divisor sum.
		long s = Util.sumOfDivisors(n) - n;
		
		// If proper divisor sum is greater than n. 
		return s > n; 
	}

	protected boolean[] isAbundant;
}