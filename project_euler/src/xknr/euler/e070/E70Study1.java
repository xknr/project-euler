package xknr.euler.e070;

import java.util.Iterator;
import java.util.TreeSet;

import xknr.euler.Solution;
import xknr.euler.util.Util;

public class E70Study1 extends Solution
{
	public E70Study1(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{
		//println(2 * 3 * 5 * 7 * 11 * 13 * 17 * 19);
		
		double best_ratio = 1e20d;
		int best_i = 0;
		int best_phi = 0;
		
		for(int i = 2; i < 1000*1000*10; i++) 
		//for(int i = 8319823; i < 8319823+1; i++)
		{
			final int x = i;
			long[] p = unique_prime_factors(x);			
			int[] sum = new int[1];				
			for(int k = 1 ; k <= p.length; k++) {
				int[] sign = {k % 2 == 1 ? 1 : -1};
				permutations_ordered(0, p.length, new int[k], 0, new PermTask() {
					@Override public void on(int[] perm) {						
						int mul = 1;
						for(int pe: perm) mul *= p[pe];
						sum[0] += sign[0] * (x / mul); 
					}
				});
			}
			
			int phi = x - sum[0];
			
//			double ratio = i / (double)phi;
//			if (x % 100000 == 0)
//			println(x + " " + p + " --> " + phi + " " + ratio);
			
			if (Util.arePermutations(x, phi) ) {
				double ratio = i / (double)phi;
				if (ratio < best_ratio) {
					format("%d [%d] %d %f\n", x, p.length, phi, ratio);
					best_ratio = ratio;
					best_i = i;
					best_phi = phi;
				}
			}
			
		}
		
		println(best_i + " " + best_phi + " " + best_ratio);

		return best_i;
		//8319823 8313928 1.0007090511248113
		//8319823
	}

	public static long[] unique_prime_factors(long x)
	{
		TreeSet<Long> set = new TreeSet<Long>();
		
		long c = x;
		long d = 2;
		while(c > 1) {
			if (c % d == 0) {
				c /= d;
				set.add(d);
			} else {
				double r = c / d;
				if (d > r) {
					d = c;
				} else {
					d = d == 2 ? 3 : d + 2;
				}
			}
		}
		
		// convert to array
		long [] result = new long[set.size()];
		int index = 0;
		for(Iterator<Long> it = set.iterator(); it.hasNext(); index++) {
			result[index] = it.next();
		}
		
		return result;
	}	
		
	public static void permutations_ordered(int startOffset, 
			int end, int[] perm, int depth, PermTask task)
	{
		if (depth < perm.length) {
			for(int i = startOffset; i < end; i++) {
				perm[depth] = i;				
				permutations_ordered(i + 1, end, perm, depth + 1, task);
			}
		} else {
			task.on(perm);
		}
	}

	public static interface PermTask {
		void on(int[] perm);
	}
	
}



