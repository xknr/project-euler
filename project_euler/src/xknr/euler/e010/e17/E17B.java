package xknr.euler.e010.e17;

import xknr.euler.Solution;

public class E17B extends Solution
{
	public E17B(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{	
		int sum = 0;

		for(int n = 1; n <= E17.LIM; n++)
		{
			Composer comp = new Composer();
			comp.compose(n);
			sum += comp.len();
			//sum += comp.str().length();
		}
		
		return sum;
	}

	
}

