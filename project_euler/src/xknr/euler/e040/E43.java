package xknr.euler.e040;

import xknr.euler.Solution;

public class E43 extends Solution
{	
	public E43(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{		 
		coll = new Coll(NUM_TOKENS);
		
		for(int i = 0; i < NUM_TOKENS; i++)
		{
			coll.add(i);
		}
		
		perm(0);
		
		return sumAll;
	}
		
	public static boolean divisibilityRules(int pos, int[] cu)
	{
		// First position cannot be zero.
		if (pos == 0 && cu[0] == 0) 
			return false;
		
		// Last digit has to be divisible by 2.
		if (pos == 3 && !divides(cu[3], 2)) 
			return false;
		
		if (pos == 4 && !divides(read(cu, 2, 3), 3)) 
			return false;
		
		// Last digit has to be divisible by 5.
		if (pos == 5 && !divides(cu[5], 5))
			return false;
		
		if (pos == 6 && !divides(read(cu, 4, 3), 7)) 
			return false;
		
		if (pos == 7 && !divides(read(cu, 5, 3), 11)) 
			return false;
		
		if (pos == 8 && !divides(read(cu, 6, 3), 13)) 
			return false;
		
		if (pos == 9 && !divides(read(cu, 7, 3), 17)) 
			return false;
		
		return true;
	}


	/**
	 * Read a number of digits from an array of digits, and interpret 
	 * the digits as a decimal integer.
	 *   
	 * @param arr
	 * @return
	 */
	public static long read(int[] arr, int pos, int numDigits) 
	{
		long num = 0;
		
		final int stop = pos + numDigits;
		for(int i = pos ; i < stop; i++) 
		{
			// Shift current sum to left.
			num *= 10;
			
			// Add current digit.
			num += arr[i];
		}
		
		return num;
	}

	public static boolean divides(long dividend, long divisor)
	{
		return dividend % divisor == 0;
	}

	public static void exch(int[] arr, int a, int b)
	{
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;		
	}	
	
	private void perm(int pos) 
	{		
		if (pos == 10) // All positions have been filled. 
		{			
			// Add this combination.
			sumAll += read(placed, 0, placed.length);
		}
		else
		{			
			// Number of unused elements can be calculated:
			// final int unusedSize = NUM_TOKENS - pos; 
			
			// Try to place each unused token.
			for(int i = 0; i < coll.sz; i++) 
			{
				// Place this unused token.
				placed[pos] = coll.elementAt(i);
				
				if (divisibilityRules(pos, placed))
				{
					// If we have come so far, we can remove this token 
					// from unused and continue searching in the next position.
					coll.removeAt(i);
					
					// Search next pos.
					perm(pos + 1);
					
					coll.restore(i);
				}
			}
		}
		
	}
	
	/**
	 * Array like class 
	 */
	public static class Coll 
	{		
		public Coll(int capacity)
		{
			arr = new int[capacity];
		}
		
		/**
		 * Add an element.
		 * @param elem Element to add.
		 */
		public void add(int elem)
		{
			arr[sz++] = elem;
		}
		
		/**
		 * Remove the element at given pos by 
		 * exchanging with last element.
		 * @param ind 
		 */
		public void removeAt(int ind) 
		{
			exch(arr, ind, --sz);
		}
		
		/**
		 * ind must be the same with last call to removeAt
		 * @param ind
		 */
		public void restore(int ind) 
		{
			// Undo remove.
			exch(arr, ind, sz++);				
		}
		
		public int elementAt(int ind)
		{
			return arr[ind];
		}
		
		private int arr[];
		private int sz;
	}	
	
	private long sumAll = 0;
	private int [] placed = new int[NUM_PLACES];

	private Coll coll;

	private static final int NUM_PLACES = 10;
	private static final int NUM_TOKENS = 10;
}
