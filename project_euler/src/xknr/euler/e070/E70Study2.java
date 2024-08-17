package xknr.euler.e070;

import xknr.euler.Solution;
import xknr.euler.util.Util;

public class E70Study2 extends Solution 
{
	public E70Study2(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{		
		int limit = 10 * 1000 * 1000;
		//int limit = 1000;
		
		double best_ratio = 1e20d;
		int best_n = 0;
		int best_phi = 0;
		
		for(int n = 2; n < limit; n++) 
		{			
			int c = n;
			int p = 2;
			
			//List<Integer> pf = new ArrayList<Integer>();
			
			boolean new_prime = false; 
			double rat = 1;
			
			while(c > 1)
			{
				if (0 == (c % p))
				{
					c /= p;
					if (!new_prime) {
						//pf.add(d);
						rat *= 1 - 1.0d / p;
					}
					new_prime = true;
				}
				else
				{
					double div = c / (float)p;
					if (p > div) {
						p = c;
					} else {
						p = p == 2 ? 3 : p + 1;
					}
					new_prime = false;					
				}
			}
			int phi = (int)Math.round(n * rat);
			
			if (Util.arePermutations(n, phi))
			{
				double r = 1 / rat;
				if (r < best_ratio)
				{
					best_ratio = r;
					best_n = n;
					best_phi = phi;
					println(n + " " + phi);
				}
			}
		}
		
		return best_n;
	}

}



