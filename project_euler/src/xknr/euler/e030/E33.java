package xknr.euler.e030;

import xknr.euler.Solution;
import xknr.euler.util.Util;

public class E33 extends Solution
{
	public E33(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{
		Frac result = new Frac().set(1, 1);
		Frac simpl = new Frac().set(1, 1);
		Frac f = new Frac();
		
		for(f.n = 11; f.n < 100; f.n++)
		{
			int n0 = (int)f.n % 10;
			if (n0 == 0) 
				continue;
			
			int n1 = (int)f.n / 10;
			
			for(f.d = f.n + 1; f.d < 100; f.d++)
			{
				int d0 = (int)f.d % 10;
				if (d0 == 0) 
					continue;
				
				int d1 = (int)f.d / 10;
				
				// Try to remove matching digits:
				     if (n1 == d0) simpl.set(n0, d1);
				else if (n1 == d1) simpl.set(n0, d0);
				else if (n0 == d0) simpl.set(n1, d1);
				else if (n0 == d1) simpl.set(n1, d0);
				else continue;
				
				if (f.isEqualTo(simpl))
				{
					result.multWith(simpl);
					//System.out.format("%s = %s\n", frac, simpl);
				}
			}
		}
		
		result.reduce();

		return result.d;
	}
	
	public static class Frac
	{
		public long n, d;
		
		public Frac() {
			n = 0;
			d = 1;
		}
		
		public Frac set(int n, int d) {
			this.n = n;
			this.d = d;
			return this;
		}
		
		public Frac reduce() {
			long g = Util.gcd(n, d);
			n /= g;
			d /= g; 
			return this;
		}
		
		public Frac multWith(Frac b) {
			this.n *= b.n;
			this.d *= b.d;
			return this;
		}

		public boolean isEqualTo(Frac b) {
			return n * b.d == d * b.n;
		}
		

	}
}