package xknr.euler.e070;

import xknr.euler.Solution;
import xknr.euler.util.Util;


public class E70b extends Solution 
{	
	public E70b(boolean doPrint) {
		super(doPrint);
	}
	
	public static class Tot {
		public int num, den, left;
	}

	private Tot[] tot;

	public long solve()
	{
		println("solve start");
		
		allocArray();
		
		println("alloc finished");
		
		initArray();
		
		println("init finished");

		fillArray();		
		
		println("sieve finished");
		
		int best_i = findBest();
		
		releaseArray();

		return best_i;
	}

	private int findBest()
	{
		int best_num = 0;
		int best_den = 1;
		int best_i = 0;
		
		for(int i = 2; i < sieveSize; i++)
		{			
			int left = tot[i].left;
			if (left == i) 
				continue; // i is prime 
			
			int num = tot[i].num;
			int den = tot[i].den;

			if (left != 1) 
			{
				num *= left - 1;
				den *= left;
			}
			
			if (num * 10 < den * 9) 
			{
				continue;
			}
			
			int tot = num * (i / den);
			
			if (Util.isPermutationOf(tot, i)) 
			{
				println(i, tot);
				
				if (compare(best_num, best_den, num, den))
				{
					best_num = num;
					best_den = den;
					best_i = i;
				}
			}
		}
		
		println("best", best_i, best_num, best_den);
		
		return best_i;
	}

	public static boolean compare(int num1, int den1, int num2, int den2) 
	{
		return (long)den1 * num2 > (long)num1 * den2;
	}

	private void fillArray() {
		for(int p = 2; p * p <= sieveSize;)
		{
			int p1 = p - 1;
			for(int i = p * p; i < sieveSize; i += p)
			{
				tot[i].num *= p1;
				tot[i].den *= p;
				
				while(tot[i].left % p == 0)
				{ 
					tot[i].left /= p;
				}
			}
			
			do {
				p += 1 + (p & 1); // 2,3,5,7,9,...
			} while(p * p <= sieveSize && tot[p].den != 1);
		}
	}



	private void allocArray()
	{
		tot = new Tot[sieveSize];
		
		for(int i = 0; i < tot.length; i++)
		{
			tot[i] = new Tot();
		}
	}
	
	private void releaseArray() 
	{
		tot = null;
	}

	private void initArray() 
	{
		// initialize with 1/1
		for(int i = 0; i < sieveSize; i++)
		{
			tot[i].num = 1;
			tot[i].den = 1;
			tot[i].left = i;
		}
	}

	private static final int sieveSize = 10_000_000;
}
