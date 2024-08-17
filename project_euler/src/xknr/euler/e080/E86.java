package xknr.euler.e080;

import xknr.euler.Solution;
import xknr.euler.util.Maartinus;

public class E86 extends Solution
{
	/*
	 * https://projecteuler.net/problem=86
	 * http://www.mathblog.dk/project-euler-86-shortest-path-cuboid/
	 */
	public E86(boolean doPrint) {
		super(doPrint);
	}

	public int solve()
	{		
		int counter = 1975;
		
		for(int k = 99; ; k++) 
		{
			counter = count(counter, k);
			
			if (counter > 1_000_000) 
			{
				return k + 1;
			}
			
			println(counter);			
		}	
	}
	
	private static int count(int counter, int prev)
	{		
		{
			int x = prev + 1;
			for(int y = x; y <= prev + 1; y++) 
			{
				for(int z = y; z <= prev + 1; z++) 
				{
					counter += count1(x, y, z);
				}			
			}
		}
		
		{
			int y = prev + 1;
			for(int x = 1; x <= prev; x++) 
			{
				for(int z = y; z <= prev + 1; z++) 
				{
					counter += count1(x, y, z);
				}			
			}
		}
		
		{
			int z = prev + 1;
			for(int x = 1; x <= prev; x++) 
			{
				for(int y = x; y <= prev; y++) 
				{
					counter += count1(x, y, z);
				}			
			}
		}
		
		return counter;		
	}

	public static int count1(int x, int y, int z) 
	{
		int xy = x + y;
		int xz = x + z;
		int yz = y + z;
		
		int sz = xy*xy + z*z;
		int sy = xz*xz + y*y;
		int sx = yz*yz + x*x; 
		
		int a, b;
		
		if (sz <= sy && sz <= sx) 
		{
			a = xy;
			b = z;
		}
		else if (sy <= sx && sy <= sz) 
		{
			a = xz;
			b = y;
		}
		else 
		{
			a = yz;
			b = x;
		}
		
		int cc = a * a + b * b;
		
		if (Maartinus.isSquare(cc)) 
		{ 
			//double c = Math.sqrt(cc);
			//System.out.format("(%d,%d,%d)  %d, %d -> %f %d\n", x, y, z, a, b, c, cc);
			return 1;
		}
		
		return 0;
	}

	public int countRaw(int lim)
	{		
		int counter = 0;
		
		for (int x = 1; x <= lim; x++)
		{
			for (int y = x; y <= lim; y++)
			{
				for (int z = y; z <= lim; z++)
				{
					int xy = x + y;
					int xz = x + z;
					int yz = y + z;
					
					int sz = xy*xy + z*z;
					int sy = xz*xz + y*y;
					int sx = yz*yz + x*x; 
					
					int a, b;
					
					if (sz <= sy && sz <= sx) 
					{
						a = xy;
						b = z;
					}
					else if (sy <= sx && sy <= sz) 
					{
						a = xz;
						b = y;
					}
					else 
					{
						a = yz;
						b = x;
					}
					
					int cc = a*a + b*b;
					
					if (Maartinus.isSquare(cc)) 
					{ 
						//double c = Math.sqrt(cc);
						//System.out.format("(%d,%d,%d)  %d, %d -> %f %d\n", x, y, z, a, b, c, cc);
						counter++;
					}					
				}
			}
		}
		
		println(counter);
		
		return counter;				
	}
}




