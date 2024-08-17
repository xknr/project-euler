package xknr.euler.e030;

import xknr.euler.Solution;

public class E39 extends Solution
{
	public static final int LIMIT = 1000;

	public E39(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{		
		int [] perimeters = new int[LIMIT + 1];
		
		// For all (a, b) pairs b > a:
		for(int a = 1; a < LIMIT; a++)
		{
			for(int b = a + 1; b < LIMIT; b++)
			{
				int c2 = a * a + b * b;
				
				if (a + b > LIMIT)
				{
					break;
				}
				
				int c = (int)(Math.floor(Math.sqrt(c2)));
				
				if (c < b)
				{
					// No need to search further with this a.
					break;
				}

				if (c * c != c2)
				{
					continue;
				}
				
				int perimeter = a + b + c;

				if (perimeter > LIMIT)
				{
					continue;
				}
				
				perimeters[perimeter]++;
				
				println(a, b, c, perimeter);
			}
		}
		
		int best_index = 0;
		
		for(int i = 0; i < perimeters.length; i++)
		{
			if (perimeters[i] > perimeters[best_index])
			{
				best_index = i;
			}
		}
		
		return best_index;
	}


}