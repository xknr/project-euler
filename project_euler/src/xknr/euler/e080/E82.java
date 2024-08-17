package xknr.euler.e080;

import java.nio.file.Paths;

import xknr.euler.Constants;
import xknr.euler.Solution;

public class E82 extends Solution
{
	public E82(boolean doPrint) {
		super(doPrint);
	}
	
	public long solve() 
	{
		int[][] m = E81.readInput(INPUT_FILENAME);
		
		for(int i = 1; i < W; i++)
		{			
			for(int j = 0; j < W; j++) 
			{
				int b = m[j][i-1];
				
				if (j > 0) 
				{
					b = Math.min(b, m[j-1][i]);
				}
				
				for(int s = 0, k = j+1; k < W; k++) 
				{
					s += m[k][i];
					b = Math.min(b, m[k][i-1] + s);
				}
				
				m[j][i] += b;
			}
			
		}
		
		int b = Integer.MAX_VALUE;
		
		for(int j = 0; j < W; j++) 
		{
			b = Math.min(b, m[j][W-1]);
		}
		
		return b;
	}

	private static final int W = 80;
	private static final String INPUT_FILENAME = 
		Paths.get(Constants.DATA_DIR, "p082_matrix.txt").toString();
}

