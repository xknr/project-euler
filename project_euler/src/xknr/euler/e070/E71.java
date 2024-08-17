package xknr.euler.e070;


import xknr.euler.Solution;
import xknr.euler.util.Fraction;

public class E71 extends Solution
{	
	public E71(boolean doPrint) {
		super(doPrint);
	}

	public long solve() 
	{	
		Fraction best = findBest(LIMIT);
		
		println(best);
		
		best.reduce();

		println(best);
		
		return best.num();
	}

	private Fraction findBest(int limit) 
	{
		double best_ratio = 0; 
		int best_n = 0, best_d = 0;
		
		for(int d = 2; d <= limit; d++) 
		{			
			if (d % 10000 == 0) 
			{
				println("d =", d);
			}
			
			double nd = 3.0 * d / 7.0;
			
			int n =  (int)Math.floor(nd);
			
			if (7 * n / d == 3) 
			{
				n--;
			}
			
			double ratio = n / (double)d;
			
			if (ratio > best_ratio) 
			{
				best_ratio = ratio;
				best_n = n;
				best_d = d;
			}
		}
		
		return new Fraction(best_n, best_d);
	}
	
	private static final int LIMIT = 1000 * 1000;	
}
