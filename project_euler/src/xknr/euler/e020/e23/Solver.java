package xknr.euler.e020.e23;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntConsumer;

public abstract class Solver 
{	
	public Solver(int lim) 
	{
		this.lim = lim;
	}

	public int solve() 
	{
		List<Integer> abundants = new ArrayList<Integer>();
		
		collect( n -> abundants.add(n) );
		
		return sumConditional(abundants);
	}

	protected int sumConditional(List<Integer> abundants) 
	{
		int sum = 0;
		
		for(int n = 1; n <= lim; n++) 
		{
			if (!isSumOfTwo(abundants, n)) 
			{		
				sum += n;
			}
		}
		
		return sum;
	}

	protected boolean isSumOfTwo(List<Integer> abundants, int n) 
	{		
		for(int a: abundants) 
		{
			if (a >= n)
			{
				break;
			}
			
			int b = n - a;
			
			if (isAbundant(b)) 
			{
				return true;
			}
		}
		
		return false; 
	}
	
	protected abstract void collect(IntConsumer collector);
	protected abstract boolean isAbundant(int b);
	
	protected int lim;
}
