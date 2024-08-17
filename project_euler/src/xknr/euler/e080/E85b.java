package xknr.euler.e080;

import xknr.euler.Solution;

public class E85b extends Solution
{
	public E85b(boolean doPrint) {
		super(doPrint);
	}

	public int solve()
	{
		int best_dif = M;
		int best_area = 0;
		
		for(int n = 1; n <= 2000; n++)
		{
			for(int m = 1; m <= 2000; m++) 
			{
				int num_comb = n * (n + 1) * m * (m + 1) / 4;
				
				int dif = Math.abs(M - num_comb);
				
				if (dif < best_dif) 
				{
					best_dif = dif;
					best_area = m * n;
				}
			}
		}

		return best_area;
	}
	
	public static final int M = 2_000_000; 	
}

