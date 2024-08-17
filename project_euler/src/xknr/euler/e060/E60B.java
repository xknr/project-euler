package xknr.euler.e060;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import xknr.euler.Solution;
import xknr.euler.miller.MillerRabin3;

public class E60B extends Solution
{	
	public E60B(boolean doPrint) {
		super(doPrint);
	}

	// Matches.
	private SSet[] ms = new SSet[LIMIT];
	
	// Primes.
	private List<Integer> ps = new ArrayList<Integer>();
	
	static class SSet 
	{
		int max;
		Set<Integer> set = new HashSet<Integer>();
	}
	
	public long solve()
	{
		ps.clear();

		// last digits cannot be 2 or 5 
		ps.add(3);
		for(int i = 7; i < LIMIT; i++) 
		{
			if (!isPrime(i)) 
			{
				continue;
			}
			ps.add(i);
		}
		
		for(int i = 0; i < ps.size(); i++) 
		{
			ms[i] = new SSet(); 
		}
				
		int best_sum = Integer.MAX_VALUE;
		
		for(int i0 = 0; i0 < ps.size(); i0++)
		{
			int p0 = ps.get(i0);
			
			if (p0 * 5 >= best_sum) 
			{
				break;
			}
			
			for(int i1 = i0 + 1; i1 < ps.size(); i1++) 
			{
				int p1 = ps.get(i1);
				
				if (p0 + p1 * 4 >= best_sum) 
				{
					break;
				}
				
				if (!pairs(i0, i1))
				{
					continue;
				}
				
				for(int i2 = i1 + 1; i2 < ps.size(); i2++) 
				{
					int p2 = ps.get(i2);
					
					if (p0 + p1 + p2 * 3 >= best_sum)
					{
						break;
					}
					
					if (!pairs(i0, i2) || !pairs(i1, i2))
					{
						continue;
					}
					
					for(int i3 = i2 + 1; i3 < ps.size(); i3++) 
					{
						int p3 = ps.get(i3);
						
						if (p0 + p1 + p2 + p3 * 2 >= best_sum)
						{
							break;
						}
						
						if (!pairs(i0, i3) || !pairs(i1, i3) || !pairs(i2, i3))
						{
							continue;
						}
						
						for(int i4 = i3 + 1; i4 < ps.size(); i4++) 
						{
							int p4 = ps.get(i4);
							
							if (p0 + p1 + p2 + p3 + p4 >= best_sum) 
							{
								break;
							}
							
							if (!pairs(i0, i4) || !pairs(i1, i4) || !pairs(i2, i4) || !pairs(i3, i4))
							{
								continue;
							}
							
							format("%d %d %d %d %d\n", p0, p1, p2, p3, p4);
							
							int sum = p0 + p1 + p2 + p3 + p4;
							
							best_sum = Math.min(best_sum, sum);
						}
					}
				}
			}
		}
		
		return best_sum;
	}

	private static boolean isPrime(long x) {
		//return Prime2.isPrime(BigInteger.valueOf(x));
		return MillerRabin3.isPrime(x);
	}

	private boolean pairs(int a, int b) 
	{
		if (a > b) throw new RuntimeException();
		
		SSet sset = ms[a];
		
		int pa = ps.get(a);
		
		if (sset.max < b)
		{
			sset.max = Math.max(a, sset.max);
			
			for(int i = sset.max + 1; i <= b; i++) 
			{
				if (calcPair(pa, ps.get(i))) 
				{					
					sset.set.add(i);
				}
			}
			sset.max = b;
		}
		
		return sset.set.contains(b);
	}

	public static long merge(int a, int b) 
	{
		long result = a;
		int t = b;
	
		while(t > 0) 
		{
			result *= 10;
			t /= 10;
		}
		
		result += b;
		
		return result;
	}

	public boolean calcPair(int x, int y)
	{
		long xy = merge(x, y);
		long yx = merge(y, x);
		
		if (!isPrime(xy)) return false;
		if (!isPrime(yx)) return false;
		
		return true;
	}
	
	private static final int LIMIT = 30000;
}

