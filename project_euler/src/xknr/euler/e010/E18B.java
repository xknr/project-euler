package xknr.euler.e010;

import xknr.euler.Solution;

public class E18B extends Solution
{
	public E18B(boolean doPrint) {
		super(doPrint);
	}

	public int solve()
	{
		return solve(E18Data.data);
	}
	
	public int solve(String[] lines)
	{
		int scores[][] = new int[lines.length][];
		
		for(int r = 0; r < scores.length; r++) 
		{
			scores[r] = new int[r + 1];
			
			for(int i = 0; i < r + 1; i++)
			{
				int s = E18.read(lines, r, i);
				
				if (r > 0) 
				{
					int best = 0;
					
					if (i < r)
					{
						best = Math.max(best, scores[r-1][i]);
					}
					
					if (i > 0)
					{
						best = Math.max(best, scores[r-1][i - 1]);						
					}
					
					s += best;
				}
				
				scores[r][i] = s;				
				
				println(s + " ");
			}
			
			println();
		}
		
		int best = 0;
		
		for(int s : scores[scores.length - 1]) 
		{
			best = Math.max(best, s);
		}		
		
		return best;
	}
}