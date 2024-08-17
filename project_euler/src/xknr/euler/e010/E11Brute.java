package xknr.euler.e010;

import static java.lang.Math.max;

import xknr.euler.Solution;

/**
 * Brute force solution.
 */
public class E11Brute extends Solution
{
	public E11Brute(boolean doPrint) {
		super(doPrint);
	}

	public long solve(int frame)
	{		
		best = 0;
		
		test(checkDirBrute(frame, 0, 1, SZ, SZ));		
		test(checkDirBrute(frame, 1, 0, SZ, SZ));		
		test(checkDirBrute(frame, 1, 1, SZ, SZ));
		test(checkDirBrute(frame, 1, -1, SZ, SZ));
		
		return best;
	}
	
	private void test(int cand) 
	{
		best = Math.max(best, cand);		
	}

	/**
	 * Check all starting locations for a specific direction. 
	 * 
	 * @param r0 Start row.
	 * @param c0 Start col.
	 * @param r1 End row - exclusive.
	 * @param c1 End col - exclusive.
	 * @param rf Direction r component.
	 * @param cf Direction c component.
	 */
	public static int checkDirBrute(int frame, int dy, int dx, int szx, int szy) 
	{
		int best = 0;

		for(int y = 0; y < szy; y++)
		{
			for(int x = 0; x < szx; x++) 
			{
				int found = productBrute(y, x, dy, dx, frame, szx, szy);
				best = max(best, found);
			}
		}
		
		return best;
	}

	/**
	 *
	 * Multiply frame element in given direction
	 * 
	 * @param y Start row.
	 * @param x Start col.
	 * @param dy Direction r component.
	 * @param dx Direction c component.
	 * @param numElements Number of elements.
	 * @return
	 */
	public static int productBrute(int y, int x, 
		int dy, int dx, 
		int numElements, 
		int szx, int szy) 
	{
		int prod = 1;	
		
		for(int i = 0; i < numElements; i++)
		{ 
			if (x < 0 || x >= szx) return 0;
			if (y < 0 || y >= szy) return 0;
			prod *= E11Read.readItem(E11Data.LINES, y, x);
			x += dx;
			y += dy;
		}
		
		return prod;		
	}

	public static final int SZ = 20;
	public static final int FRAME = 4;

	private int best;
}
