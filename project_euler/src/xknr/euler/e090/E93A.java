package xknr.euler.e090;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import xknr.euler.Solution;
import xknr.euler.util.Fraction;
import xknr.euler.util.Util;

public class E93A extends Solution
{
	public E93A(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{
		List<int[]> perms = genPerms();
		
		// all unordered permutations of all ordered permutations...
		
		int ds[] = new int[4];
		
		int best_r = 0;
		int best_abcd = 0;
		
		for(ds[0] =       1; ds[0] <= 9; ds[0]++)
		for(ds[1] = ds[0]+1; ds[1] <= 9; ds[1]++)
		for(ds[2] = ds[1]+1; ds[2] <= 9; ds[2]++)
		for(ds[3] = ds[2]+1; ds[3] <= 9; ds[3]++) 
		{
			int r = calcR(perms, ds);
			if (r > best_r) 
			{
				best_r = r;
				best_abcd = (int)Util.read(ds, 0, 4);

				printWinner(ds, r);				
			}				
		}

		return best_abcd;
	}

	protected void printWinner(int[] ds, int r) 
	{
		if (!doPrint())
		{
			return;
		}
		
		println();
		
		println("winner ");
		
		format("%d %d %d %d --> %d\n", 
			ds[0], ds[1], ds[2], ds[3], r);
		
		println();								
	}

	protected int calcR(List<int[]> perms, int[] ds)
	{
		Set<Integer> found = new HashSet<Integer>();
		
		int r = 0;
		
		for(int[] p: perms)
		{
			Fraction r0 = new Fraction(ds[p[0]], 1);
			for(int o0 = 0; o0 < 6; o0++)
			{
				Fraction r1 = op(o0, r0, new Fraction(ds[p[1]], 1));
				for(int o1 = 0; o1 < 6; o1++)
				{
					Fraction r2 = op(o1, r1, new Fraction(ds[p[2]], 1));
					for(int o2 = 0; o2 < 6; o2++)
					{
						Fraction r3 = op(o2, r2, new Fraction(ds[p[3]], 1));
						r3.reduce(); // probably no need... assert this.
						
						if (r3.num() > 0 && r3.den() == 1)
						{
							int result = (int)r3.num();
							found.add(result);
							
							if (result > r) 
							{
								while(found.contains(r + 1))
								{
									r++;
								}
							}
							
							format("%d %d %d %d --> %d %d\n", 
								ds[p[0]], ds[p[1]], ds[p[2]], ds[p[3]], r3.num(), r);
						}
						
					}
				}
			}
		}
		return r;
	}

	/**
	 * Generate all unordered permutations for 0123.
	 * @return
	 */
	public static List<int[]> genPerms()
	{
		List<int[]> perms = new ArrayList<int[]>();
		
		for(int a = 0; a < 4; a++)
		{
			for(int b = 0; b < 4; b++)
			{
				if (b == a) 
				{
					continue;
				}
				
				for(int c = 0; c < 4; c++) 
				{
					if (c == a || c == b)
					{
						continue;
					}
					
					for(int d = 0; d < 4; d++)
					{
						if (d == a || d == b || d == c)
						{
							continue;
						}
						
						int[] perm = new int[]{a, b, c, d};
						perms.add(perm);
					}
				}
			}
		}
		return perms;
	}
	
	private static Fraction op(int op, Fraction a, Fraction b)
	{
		switch(op) 
		{
			case 0: return a.add(b);
			case 1: return a.sub(b);
			case 2: return b.sub(a);
			case 3: return a.mul(b);
			// Prevent division by zero, those operations should be ignored.
			case 4: if (b.num() == 0) return new Fraction(0, 1); else return a.div(b);
			case 5: if (a.num() == 0) return new Fraction(0, 1); else return b.div(a);
			default: throw new AssertionError();
		}
	}
		
}

