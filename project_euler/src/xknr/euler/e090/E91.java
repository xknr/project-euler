package xknr.euler.e090;

import xknr.euler.Solution;

public class E91 extends Solution
{		
	public E91(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{		
		int count = 0;
		
		for(int by = 0; by <= 50; by++)
		{
			for(int bx = 0; bx <= 50; bx++) 
			{
				for(int ay = 0; ay <= 50; ay++) 
				{
					for(int ax = 0; ax <= 50; ax++) 
					{
						count += countIf(ax, ay, bx, by);
					}
				}
			}
		}
		
		return count / 2;
	}

	public static int countIf(int ax, int ay, int bx, int by) 
	{
		if (ax == bx && ay == by) return 0; 
		if (ax == 0 && ay == 0) return 0;
		if (bx == 0 && by == 0) return 0;
		if (ax == 0 && bx == 0) return 0;
		if (ay == 0 && by == 0) return 0;
		if (ax == 0 && by == 0) return 1;
		if (ay == 0 && bx == 0) return 1;
		
		final int dx = bx - ax;
		final int dy = by - ay;
		
		if (ax * dx == -ay * dy) return 1;
		if (-bx * dx == by * dy) return 1;
		
		return 0;
	}
}

