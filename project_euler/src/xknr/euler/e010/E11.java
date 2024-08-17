package xknr.euler.e010;

import static java.lang.Math.max;

import xknr.euler.Solution;

/**
 * Brute force solution.
 */
public class E11 extends Solution
{
	public E11(boolean doPrint) {
		super(doPrint);
	}

	public long solve(int frame) 
	{		
		best = 0;

		test(checkDir(0, 1, frame));		
		test(checkDir(1, 0, frame));		
		test(checkDir(1, 1, frame));
		test(checkDir(1, -1, frame));
		
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
	private int checkDir(int rf, int cf, int frame)
	{		
		Bounds bounds = determineBounds(rf, cf, frame, SZ, SZ);
	
		return checkBounds(bounds, frame, rf, cf);
	}

	private int checkBounds(Bounds bounds, int frame, int dy, int dx) 
	{
		int best = 0;
		
		for(int y = bounds.y0; y < bounds.y1; y++) 
		{
			for(int x = bounds.x0; x < bounds.x1; x++) 
			{
				int found = product(y, x, dy, dx, frame);
				best = max(best, found);
			}
		}
		
		return best;
	}

	public static Bounds determineBounds(int rf, int cf, int frame, int szy, int szx)
	{
		assert rf >= 0 && rf <= 1;
		assert cf >= -1 && cf <= 1;

		Bounds bounds = new Bounds(0, 0, szx, szy);
		
		if (rf == 1)
		{
			bounds.y1 -= frame - 1;
		}
		
		if (cf > 0) 
		{
			bounds.x1 -= frame - 1;		
		}
		
		if (cf == -1)
		{
			bounds.x0 = frame - 1;
		}
		
		return bounds;
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
	private int product(int y, int x, int dy, int dx, int numElements) 
	{
		int prod = 1;	
		
		for(int i = 0; i < numElements; i++)
		{ 
			prod *= E11Read.readItem(E11Data.LINES, y, x);
			
			x += dx;
			y += dy;
		}
		
		return prod;		
	}
	
	public static class Bounds 
	{
		public int x0, y0, x1, y1;
		
		public Bounds(int x0, int y0, int x1, int y1) 
		{
			this.x0 = x0; 
			this.y0 = y0; 
			this.x1 = x1; 
			this.y1 = y1; 
		}
	}

	private static final int SZ = 20;
	private int best;
}
