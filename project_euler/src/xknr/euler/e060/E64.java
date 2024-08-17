package xknr.euler.e060;

import java.util.HashMap;
import java.util.Map;

import xknr.euler.Solution;
import xknr.euler.util.Util;

public class E64 extends Solution
{	
	public E64(boolean doPrint) {
		super(doPrint);
	}

	/**
	 * TODO Use a POJ instead of a String for hashing.
	 */
	public long solve()
	{
		int count = 0;
		
		for(int n = 1; n <= 10000; n++)
		{
			int len = periodLength(n);
			
			if (len % 2 == 1) 
			{
				count++;
			}
		}

		return count;
	}

	public int periodLength(int n) 
	{		
		format("f(%d)", n);
		
		double fsqrt = Math.sqrt(n);
		
		int r = (int)(Math.floor(fsqrt) + 0.1);
		
		int rupper = (int)(Math.ceil(fsqrt) + 0.1);
		
		if (r == rupper) 
		{
			return 0;
		}

		// Each progressive term is denoted by a, e / (sqrt(n) - f)

		int a = r;
		int e = 1;
		int f = a;
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		for(int i = 0; ; i++) 
		{			
			String key = "" + a + ":" + e + ":" + f;
			
			Integer found = map.get(key);
			
			if (found != null) 
			{
				int len = i - found;
				println("found " + len);
				return len;
			}

			format(" %d, [%d : s - %d]\n", a, e, f);

			map.put(key, i);
			
			int g = n - f * f;
			int gcd = (int)Util.gcd(e, g);
			
			if (gcd != e)
			{
				throw new AssertionError();
			}
			
			g /= e;
			
			a = (f + r) / g;
			
			int h = a * g - f;
			
			e = g;
			f = h;			
		}
	}
}
