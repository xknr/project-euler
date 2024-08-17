package xknr.euler.e090;

import xknr.euler.Solution;

public class E94B extends Solution
{
	
	public E94B(boolean doPrint) {
		super(doPrint);
	}
	
	public long solve()
	{		
		long sum = 0;
		
		long prevN = 0, n = 1;

		long m[] = new long[2];
		Triplet triplet[] = new Triplet[2]; 
		long perimeter[] = new long[2];

		while(true) 
		{
			// Generate two m values
			m[0] = n * 2 - prevN;
			m[1] = n * 4 - prevN;
			
			triplet[0] = generateTriplet(m[0], n);
			triplet[1] = generateTriplet(m[1], n);

			perimeter[0] = (triplet[0].a + triplet[0].c) * 2;
			perimeter[1] = (triplet[1].b + triplet[1].c) * 2;
			
			if (perimeter[0] > LIM && perimeter[1] > LIM)
			{
				break;
			}
			
			for(int i = 0; i < 2; i++) 
			{
				if (perimeter[i] <= LIM) 
				{
					sum += perimeter[i];
					
					format("%d %d     %d %d %d   %d\n", 
						m[i], n, 
						triplet[i].a, triplet[i].b, 
						triplet[i].c, perimeter[i]); 
				}
			}
			
			prevN = n;
			
			// Choose the greater of two m's as the next n:
			n = m[1];
		}
		
		return sum;
	}
	
	private Triplet generateTriplet(long m, long n) 
	{
		final long a = m * m - n * n;
		final long b = 2 * m * n;
		final long c = m * m + n * n;
		return new Triplet(a, b, c);
	}
	private static final long LIM = 1000_000_000;
	
	public static class Triplet 
	{
		long a, b, c;
		public Triplet(long a, long b, long c) 
		{
			this.a = a;
			this.b = b;
			this.c = c;
		}
	}
	
}


