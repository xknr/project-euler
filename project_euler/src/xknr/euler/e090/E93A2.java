package xknr.euler.e090;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Uses doubles.
 * @author kk
 *
 */
public class E93A2 extends E93A
{

	public E93A2(boolean doPrint) {
		super(doPrint);
	}

	protected int calcR(List<int[]> perms, int[] ds) 
	{
		int r = 0;
		
		Set<Integer> found = new HashSet<Integer>();
		
		for(int[] p: perms)
		{
			double r0 = (ds[p[0]]);
			for(int o0 = 0; o0 < 6; o0++)
			{
				double r1 = op(o0, r0, (ds[p[1]]));
				for(int o1 = 0; o1 < 6; o1++)
				{
					double r2 = op(o1, r1, (ds[p[2]]));
					for(int o2 = 0; o2 < 6; o2++)
					{
						double r3 = op(o2, r2, (ds[p[3]]));
						double rounded = Math.round(r3);
						
						if (Math.abs(r3 - rounded) < 0.000001) 
						{
							int result = (int)(rounded + 0.1);
							
							found.add(result);
							
							if (result > r) 
							{
								while(found.contains(r + 1))
								{
									r++;
								}
							}
							
							format("%d %d %d %d --> %d %d\n", 
								ds[p[0]], ds[p[1]], ds[p[2]], ds[p[3]], 
								result, r);
						}
						
					}
				}
			}
		}
		return r;
	}

	public static double op(int op, double a, double b)
	{
		// TODO Division by zero is not handled.
		switch(op) {
			case 0: return a + b;
			case 1: return a - b;
			case 2: return b - a;
			case 3: return a * b;
			case 4: return a / b;
			case 5: return b / a;
			default: throw new AssertionError();
		}
	}

}

