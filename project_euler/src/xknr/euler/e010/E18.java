package xknr.euler.e010;

import xknr.euler.Solution;

/**
 * If we designate the best route to a location (r, c) as b(r, c), 
 * 
 * b(r, c) = max( b(r-1, c-1), b(r-1, c) ) + value(r, c)
 */
public class E18 extends Solution
{	
	public E18(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{		
		String[] lines = E18Data.data;
		int numRows = E18Data.NUM_ROWS;
		
		// First buffer is composed of a single element, read from (0, 0).
		int prev[] = { read(lines, 0, 0) };
		
		for(int row = 1; row < numRows; row++) 
		{
			int numCols = row + 1;
			int curr[] = new int[numCols];
			calculateRow(lines, prev, curr, row);
			prev = curr;
		}
		
		return getBestInRow(prev, numRows - 1);
	}

	public static void calculateRow(String[] data, int[] prev, int[] curr, int row)
	{
		int numCols = row + 1;
		
		// For each position in row:
		for(int col = 0; col < numCols; col++)
		{			
			// Get left bottom and bottom elements if they exist.
			int le = (col > 0) ? prev[col - 1] : 0;
			int ri = (col < row) ? prev[col] : 0;
			
			// Select best of two bottom elements, add current cell's cost. 
			curr[col] = Math.max(le, ri) + read(data, row, col);
		}
	}

	public static int getBestInRow(int[] curr, final int row)
	{
		int best = 0;
		
		for(int col = 0; col < row + 1; col++) 
		{
			best = Math.max(curr[col], best);
		}
		
		return best;
	}
	
	public static int read(String[] lines, int row, int col)
	{
		String line = lines[row];
		int index = col * 3;
		
		int d1 = line.charAt(index++) - '0';
		assert d1 >= 0 && d1 <= 9;

		int d0 = line.charAt(index) - '0';
		assert d0 >= 0 && d0 <= 9;
		
		return d1 * 10 + d0;
	}
}

