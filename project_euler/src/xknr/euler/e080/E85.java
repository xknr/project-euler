package xknr.euler.e080;

import xknr.euler.Solution;

public class E85 extends Solution
{	
	public E85(boolean doPrint) {
		super(doPrint);
	}

	public int solve()
	{
		int best_dif = 2000000;
		int best_n = 0;
		int best_m = 0;
		
		for(int n = 1; n < 2000000; n++) 
		{			
			int N = n * (n + 1);
			
			double mm1 = (M * 4) / (double)N;
			
			if (mm1 < 0) 
			{
				break;
			}
			
			double m_estimate = Math.sqrt(mm1);
			
			if (m_estimate < n / 2) 
			{
				break;
			}
			
			int m = (int)(Math.floor(m_estimate) + 0.5);
			
			int v1 = N * m * (m + 1) / 4;
			int v2 = N * m * (m - 1) / 4;
			
			println(n, m_estimate, v1, v2);
			
			if (Math.abs(v1 - 2000000) < best_dif) 
			{
				best_dif = Math.abs(v1 - 2000000);
				best_n = n;
				best_m = m;
			}

			if (Math.abs(v2 - 2000000) < best_dif) 
			{
				best_dif = Math.abs(v2 - 2000000);
				best_n = n;
				best_m = m;
			}			
		}

		println(best_n, best_m);
		
		return best_n * best_m;
	}

	public static final int M = 2_000_000; 

}

