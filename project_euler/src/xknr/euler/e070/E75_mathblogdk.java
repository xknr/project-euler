package xknr.euler.e070;

import xknr.euler.Solution;
import xknr.euler.util.Util;

public class E75_mathblogdk extends Solution
{	
	public E75_mathblogdk(boolean doPrint) {
		super(doPrint);
	}

	public int solve()
	{
		int limit = 1500000;
		int[] triangles = new int[limit+1];
		 
		int result =0;
		int mlimit = (int)Math.sqrt(limit / 2);
		
		for (long m = 2; m < mlimit; m++)
		{
		    for (long n = 1; n < m; n++)
		    {
		        if (((n + m) % 2) == 1 && Util.gcd(n, m) == 1)
		        {
		            long a = m * m + n * n;
		            long b = m * m - n * n;
		            long c = 2 * m * n;
		            long p = a + b + c;
		            
		            while(p <= limit)
		            {
		                triangles[(int)p]++;
		                if (triangles[(int)p] == 1) result++;
		                if (triangles[(int)p] == 2) result--;
		                p += a + b + c;
		            }
		        }
		    }
		}
		return result;
	}
	

}
