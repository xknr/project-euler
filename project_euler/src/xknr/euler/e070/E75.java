package xknr.euler.e070;

import xknr.euler.Solution;
import xknr.euler.util.Util;

public class E75 extends Solution
{
	public E75(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{
		int[] hitCount = new int[(int) LIMIT + 1];

		for (long m = 2; m <= LIMIT; m++) 
		{
			boolean found = false;
			boolean m_even = m % 2 == 0;
			
			for (long n = 1; n < m; n++)
			{
				boolean n_even = n % 2 == 0;
				
				if (m_even || n_even)
				{
					if (Util.gcd(n, m) == 1)
					{
						long sum = 2 * m * (m + n);
					
						if (sum > LIMIT) 
						{
							break;
						}
						
						found = true;
						int isum = (int) sum;
						
						for (int j = isum; j <= LIMIT; j += isum) 
						{
							hitCount[j]++;
						}
					}
				}
			}
			
			if (!found) 
			{
				break;
			}
		}

		int count = 0;
		for (int x : hitCount) 
		{
			if (1 == x) 
			{
				count++;
			}
		}

		return count;
	}

	private static final long LIMIT = 1_500_000;
}
