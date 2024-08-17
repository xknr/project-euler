package xknr.euler.e080;

import java.util.HashSet;
import java.util.Set;

import xknr.euler.Solution;
import xknr.euler.e070.E72;
import xknr.euler.util.Util;



public class E86Study extends Solution
{
	public E86Study(boolean doPrint) {
		super(doPrint);
	}

	public void study()
	{		
		set.clear();
				
		// a given n cannot generate a cube with all edges smaller than 2n - 1
		// so there is no need to check the values of n which are greater than limit/2+1   
		
		for(int n = 2; n <= LIMIT / 2 + 1; n++)
		{
			boolean found = false;
			
			for(int m = 1; m < n; m++) 
			{				
				if (Util.gcd(n, m) != 1 || (n+m) % 2 == 0)
					continue;
		
				int A = n*n - m*m;
				int B = 2 * n * m;
				int C = n*n + m*m;
				
				for(int i = 1; ; i++) {

					int a = A * i;
					int b = B * i;
					int c = C * i;
				
					int min = Math.min(a, b);
					int max = Math.max(a, b);
					if (min > LIMIT || max > LIMIT * 2) break;
					
					format("%d / %d  ->   %3d+%3d=%3d\n", m, n, a, b, c);
					
					if (b <= LIMIT) 
					{
						int z = b;
						for(int x = 1; ; x++) {
							int y = a - x;
							if (y < x) break;							
							if (generateCube(x, y, z, a, b)) found = true;
						}
						//found = true;
					}
					
					if (a <= LIMIT) 
					{
						int z = a;
						for(int x = 1; ; x++) 
						{
							int y = b - x;
							if (y < x) break;							
							if (generateCube(x, y, z, a, b)) found = true;
						}
						//found = true;
					}						
				}

			}
		
			format("%d %s\n", n, found ? " found " : " NONE ------ ");
		}
		
		println(set.size());		
	}

	private boolean generateCube(int x, int y, int z, int A, int B)
	{		
		// Sort 3 values.
		
		if (x > y)
		{
			int t = x;
			x = y;
			y = t;
		}

		if (y > z) 
		{
			int t = y;
			y = z;
			z = t;
		}
		
		if (x > y) 
		{
			int t = x;
			x = y;
			y = t;
		}
		
		int xy = x + y;
		int xz = x + z;
		int yz = y + z;
		
		int sz = xy * xy + z * z;
		int sy = xz * xz + y * y;
		int sx = yz * yz + x * x; 
		
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
		
		// if the length of the path derived from pythagorean triplet is the shortest path
		if ( (a == A && b == B) || (a == B && b == A)) 
		{			
			if (x <= LIMIT && y <= LIMIT && z <= LIMIT)
			{
				Cube cube = new Cube(x, y, z);

				assert !set.contains(cube);
				set.add(cube);
				format("%d %d %d\n", x, y, z);
			}
				
			return true;
		}
		
		return false;		
	}
	
	public static class Cube 
	{		
		public int x, y, z;
		
		public Cube(int x, int y, int z) 
		{		
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
		@Override
		public boolean equals(Object obj)
		{
			Cube o = (Cube)obj;
			return x == o.x && y == o.y && z == o.z;		
		}
		
		/**
		 * How to generate a hash code from three longs
		 * http://stackoverflow.com/a/5730232
		 */
		@Override
	    public int hashCode()
	    {
	        int result = (int) (x ^ (x >>> 32));
	        result = 31 * result + (int) (y ^ (y >>> 32));
	        result = 31 * result + (int) (z ^ (z >>> 32));
	        return result;
	    }		
	}
		
	private final static int LIMIT = 100;
	private Set<Cube> set = new HashSet<Cube>();		
}
