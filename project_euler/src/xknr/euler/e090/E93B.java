package xknr.euler.e090;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import xknr.euler.Solution;
import xknr.euler.util.Util;

public class E93B extends Solution
{
	public E93B(boolean doPrint) {
		super(doPrint);
	}

	public static class Context 
	{
		int r;
		Set<Integer> found = new HashSet<Integer>();
	}
	
	public long solve()
	{
		// all unordered permutations of all ordered permutations...
		
		int ds[] = new int[4];
		
		int best_r = 0;
		int best_abcd = 0;
		
		int dsSorted[] = new int[4];
		
		Context[] contexts = new Context[10000];
		
		for(ds[0] = 1; ds[0] <= 9; ds[0]++)
		{
			for(ds[1] = 1; ds[1] <= 9; ds[1]++)
			{
				if (ds[0] == ds[1])
				{
					continue;
				}
				
				for(ds[2] = 1; ds[2] <= 9; ds[2]++)
				{
					if (ds[2] == ds[1] || ds[2] == ds[0])
					{
						continue;
					}
					
					for(ds[3] = 1; ds[3] <= 9; ds[3]++) 
					{
						if (ds[3] == ds[2] || ds[3] == ds[1] || ds[3] == ds[0])
						{
							continue;
						}
						
						System.arraycopy(ds, 0, dsSorted, 0, 4);
						Arrays.sort(dsSorted);
						int abcd = (int)Util.read(dsSorted, 0, 4);
						Context context = contexts[abcd];
						if (context == null) 
						{
							context = contexts[abcd] = new Context();
						}
						
						processDs(context, ds);
						
//						if (r > best_r) {
//							best_r = r;
//							println();
//							println("winner ");
//							format("%d %d %d %d --> %d\n", 
//									ds[0], ds[1], ds[2], ds[3], r);
//							println();
//							best_abcd = ds[0] * 1000 +  ds[1] * 100 + ds[2] * 10 + ds[3];
//						}
						
					}
				}
			}
		}		
		
		best_r = 0;
		best_abcd = 0;
		
		for(int i = 0; i < 10000; i++)
		{
			Context context = contexts[i];
			if (context == null)
			{
				continue;
			}
			
//			System.out.println(i + " " + context.r);
			
			if (context.r > best_r)
			{
				best_r = context.r;
				best_abcd = i; 
			}
		}
		
		return best_abcd;
	}

	private static int[] processDs(Context context, int[] ds) 
	{
		int[] p = {0, 1, 2, 3};

		//for(int[] p: perms)
		{
			double r0 = ds[p[0]];
			for(int o0 = 0; o0 < 6; o0++)
			{
				double r1 = E93A2.op(o0, r0, ds[p[1]]);
				for(int o1 = 0; o1 < 6; o1++)
				{
					double r2 = E93A2.op(o1, r1, ds[p[2]]);
					for(int o2 = 0; o2 < 6; o2++)
					{
						double r3 = E93A2.op(o2, r2, ds[p[3]]);
						double rounded = Math.round(r3);
						if (Math.abs(r3 - rounded) < 0.000001) 
						{
							int result = (int)(rounded + 0.1);
							context.found.add(result);
							
							if (result > context.r) 
							{
								while(context.found.contains(context.r + 1))
								{
									context.r++;
								}
							}
//							format("%d %d %d %d --> %d %d\n", 
//								ds[p[0]], ds[p[1]], ds[p[2]], ds[p[3]], result, r);
						}
						
					}
				}
			}
		}
		
		return p;
	}


}

