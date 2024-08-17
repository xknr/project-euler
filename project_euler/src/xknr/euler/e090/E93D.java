package xknr.euler.e090;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import xknr.euler.Solution;

public class E93D extends Solution
{
	public E93D(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{		
		List<int[]> orderings = generateOrderings();
		List<Integer> permutations = new PermGenerator().calculate();

		Context context = new Context();
		for(int permutation: permutations) 
		{
			context.evaluate(orderings, permutation);		
		}

		return context.best_abcd;
	}	
	
	/**
	 * Iterates through all 4 element ordered permutations of 0-9. 
	 */
	static class PermGenerator 
	{
		private List<Integer> permutations;		
		
		public List<Integer> calculate()
		{
			permutations = new ArrayList<Integer>();
			recursive(0, 0, 0);
			return permutations;
		}

		private void recursive(int dsIndex, int dsStart, int sum)
		{
			assert dsIndex <= 4;
			
			if (dsIndex == 4) 
			{
				permutations.add(sum);
				return;
			} 
			
			sum *= 10;	
			
			for(int d = dsStart; d < 10; d++) 
			{
				recursive(dsIndex + 1, d + 1, sum + d);
			}			
		}
	}		
		
	static class Context
	{		
		void evaluate(List<int[]> orderings, int abcd)
		{
			breakDown(abcd, digits);
			
			Context2 context2 = new Context2();
			
			context2.reset();			
			
			for(int[] ordering: orderings) 
			{
				context2.sums[0] = digits[ordering[0]];
				context2.perm = ordering;
				context2.recursive(digits, 0);
			}
			
			if (context2.highestConsecutive > best_r) 
			{
				best_r = context2.highestConsecutive;
				best_abcd = abcd; 
			}
		}

		private int [] digits = new int[4];
		private int best_r = 0;
		private int best_abcd;		
	}	
	
	static class Context2
	{		
		void reset() 
		{
			found.clear();
			highestConsecutive = 0;
		}
		
		void recursive(int[] digits, int depth)
		{
			assert depth <= MAX_OP_DEPTH;
			
			if (depth >= MAX_OP_DEPTH)
			{
				double rounded = Math.round(sums[MAX_OP_DEPTH]);
				if (Math.abs(sums[MAX_OP_DEPTH] - rounded) < EPSILON)
				{
					int result = (int)(rounded + 0.1);
					
					found.add(result);
					
					if (result > highestConsecutive)
					{
						while(found.contains(highestConsecutive + 1)) 
						{
							highestConsecutive++;
						}
					}
				}
				return;
			}
				
			for(int opIndex = 0; opIndex < NUM_OPS; opIndex++)
			{
				if (!executeOp(digits, depth, opIndex)) 
				{
					return;
				}
				recursive(digits, depth + 1);
			}
			
		}

		private boolean executeOp(int[] digits, int depth, int opIndex)
		{
			double a = sums[depth];
			double b = digits[perm[depth + 1]];

			// Cancel on division by zero:
			if (opIndex == 4 && Math.abs(b) < EPSILON) return false; 
			if (opIndex == 5 && Math.abs(a) < EPSILON) return false;
			
			sums[depth + 1] = E93A2.op(opIndex, a, b);
			
			return true;
		}
		
		private int[] perm;
		private double[] sums = new double[4];
		private int highestConsecutive;
		private Set<Integer> found = new HashSet<Integer>();		
	}


	

	// Generate all unordered permutations for {0,1,2,3}
	public static List<int[]> generateOrderings()
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
						
						int[] p = new int[]{a, b, c, d};
						
						perms.add(p);
					}
				}
			}
		}
		return perms;
	}

	public static void breakDown(int abcd, int[] digits)
	{
		digits[0] = abcd / 1000; 
		abcd %= 1000;
		
		digits[1] = abcd / 100; 
		abcd %= 100;
		
		digits[2] = abcd / 10;
		digits[3] = abcd % 10;
	}

	private static final int NUM_OPS = 6;
	private static final int MAX_OP_DEPTH = 3;

	private static final double EPSILON = 0.00001;
	
}

