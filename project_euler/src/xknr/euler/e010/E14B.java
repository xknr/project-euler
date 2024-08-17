package xknr.euler.e010;

import xknr.euler.Solution;
import xknr.euler.e010.E14.CollatzLen;

/**
 * Tries to find as many as possible sequence lengths by 
 * constructing a tree that contains all collatz sequences.
 * 
 * The root node is 1, child nodes are numbers for which 
 * the next number is the parent.
 * 
 * 1 - 2 - 4 - 8 - 16 - 32 - 64
 *                  |        |
 *                  |        21
 *                  |
 *                  5 - 10 - 20
 *                       |
 *                       3
 *         
 */
public class E14B extends Solution
{	
	public E14B(boolean doPrint) {
		super(doPrint);
	}

	public long solve() 
	{
		return new Solver().solve(new CollatzLen());
	}

	public static class Solver
	{		
		public long solve(CollatzLen col)
		{
			int[] cachedLengths = new Pre().search();
			
			col.setCache(cachedLengths);
			
			// Initialize best found.
			int bestLen = 0, bestN = 0;
			
			for(int x = 1; x < LIM; x++) 
			{				
				// This line is different from E014:
				// Try to find len for x in cache:
				int len = cachedLengths[x];
								
				if (len == 0) // If not found:
				{
					// Calculate.
					len = col.collatzLen(x);
				}
				
				// Update best.
				if (len > bestLen) 
				{
					bestLen = len;
					bestN = x;
				}
			}

			return bestN;
		}
	}


	public static class Pre 
	{		
		private static final int SEARCH_LIM = 1000_100;
		private static final int CACHE_SIZE = 1000_200;
				
		private int[] cachedLen = new int[CACHE_SIZE];
		private Pair stack[] = new Pair[64];
		
		public static final class Pair
		{
			public int x, len;
			
			public void set(int x, int len) 
			{
				this.x = x;
				this.len = len;
			}
		}
		
		Pre()
		{
			for(int i = 0; i < stack.length; i++)
			{
				stack[i] = new Pair();
			}
		}

		private int stackIndex;
		
		public int[] search()
		{			
			stackIndex = 0;

			// Push root element of the tree.
			push(1, 1);
					
			// While stack is not empty.
			while(stackIndex > 0) 
			{
				// Pop the top element.
				Pair top = pop();
				
				int x = top.x, xLen = top.len;
				
				// If already cached, continue
				if (cachedLen[x] != 0)
				{
					continue;
				}

				// Record in cache.
				cachedLen[x] = xLen;
				
				// First branch.
				int p1 = (x - 1) / 3;
				
				// If (x-1) was divisible by 3 and p1 is odd.
				if (p1 * 3 == x - 1 && (p1 & 1) == 1)
				{
					if (p1 < SEARCH_LIM)
					{					
						push(p1, xLen + 1);
					}
				}
				
				// Second branch.
				int p2 = x << 1;
				if (p2 < SEARCH_LIM)
				{
					push(p2, xLen + 1);
				}
			}
			
			return cachedLen;
		}
		
		void push(int x, int len)
		{
			stack[stackIndex++].set(x, len);
		}
		
		Pair pop()
		{
			return stack[--stackIndex];			
		}		
	}
	
	public static final int LIM = 1000_000;
}

