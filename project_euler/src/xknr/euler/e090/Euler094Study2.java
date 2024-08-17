package xknr.euler.e090;

import xknr.euler.util.LSQRT;
import xknr.euler.util.Maartinus;
import xknr.euler.util.Util;

public class Euler094Study2
{	
	public static long study2()
	{
		long sum = 0;
		
		for(long m = 1; m < 11000; m+=1) 
		{
			for(long n = 1; n < m; n+=1) 
			{
				if (m % 2 == 1 && n % 2 == 1) 
				{
					continue;
				}
				
				if (Util.gcd(m, n) != 1) 
				{
					continue;
				}
				
				long a = m * m - n * n;
				long b = 2 * m * n;
				long c = m * m + n * n;
				
				if (2*a+1 == c || 2*a-1 == c || 2*b+1 == c || 2*b-1 == c) 
				{
					System.out.format("%d %d    %d %d %d\n", m, n, a, b, c);
				}
			}
		}

		System.out.println();
		System.out.println();
		System.out.println();
		
		for(long d = 1; ; d++) 
		{
			long peri = 0;
			
			for(int i = -1; i <= 1; i += 2) 
			{
				long c = d + i;
				
				if (c < 1)
				{
					continue;
				}
				
				peri = d + c + c;
				
				if (peri > LIM) 
				{
					continue;
				}
				
				long h2s = 4 * c * c - d * d;
				
				if (h2s < 1) 
				{
					continue;
				}
				
				if (!Maartinus.isSquare(h2s)) 
				{
					continue;
				}
				
				long h2 = LSQRT.lsqrtB(h2s);				
				
				long area8 = h2 * d;
				
				if (area8 % 16 == 0) 
				{
					sum += peri;
					long b = h2 / 2;
					long a = LSQRT.lsqrtB(c*c - b*b);
					long g = Util.gcd(a, b);
					System.out.format("%d %d %d %d\n", a, b, c, g);
				}
			}
			
			if (peri > LIM) 
			{
				break;
			}
		}
		
		System.out.println("sum = " + sum);
		return sum;		
	}
	
	private static final long LIM = 1000_000_000;
}
