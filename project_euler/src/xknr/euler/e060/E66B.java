package xknr.euler.e060;

import static xknr.euler.util.Util.B;
import static xknr.euler.util.Util.B0;
import static xknr.euler.util.Util.B1;

import java.math.BigInteger;

import xknr.euler.Solution;


/*
 * http://www.mathblog.dk/project-euler-66-diophantine-equation/
 * https://en.wikipedia.org/wiki/Diophantine_equation
 * https://en.wikipedia.org/wiki/Pell%27s_equation
 * https://en.wikipedia.org/wiki/Pell%27s_equation#Fundamental_solution_via_continued_fractions
 */
public class E66B extends Solution
{	
	public E66B(boolean doPrint) {
		super(doPrint);
	}

	public int solve()
	{		
		int best_n = 0;
		
		BigInteger best_x = B0;
		
		for(int n = 1; n <= 1000; n++)
		{
			BigInteger x = findX(n);
			
			if (x == null)
			{
				continue;
			}
			
			//println(String.format("%d -> %d", n, x));
			if (x.compareTo(best_x) > 0) 
			{
				best_x = x;
				best_n = n;
			}
		}
	
		return best_n;
	}	
	
	public BigInteger findX(int n_)
	{
		// quick & dirty integer square root
		double fsqrt = Math.sqrt(n_);
		int isqrt = (int)(Math.floor(fsqrt) + 0.1);
		int isqrt_ceil = (int)(Math.ceil(fsqrt) + 0.1);
		
		if (isqrt == isqrt_ceil) 
		{
			return null;
		}
		
		BigInteger n = B(n_);
		
		BigInteger a0 = B(isqrt);
		BigInteger Np = B1;
		BigInteger Dp = B0;
		
		BigInteger m = B0;
		BigInteger d = B1;
		BigInteger a = B(isqrt);
		BigInteger N = a;
		BigInteger D = B1;
		
		while(true) 
		{
			//println(String.format("%d %d %d %d %d", m, d, a, N, D));
			
			// if (N*N - D*D*n == 1)
			if (N.multiply(N).subtract(D.multiply(D).multiply(n)).equals(B1))
			{
				return N;
			}
			
			// m = d * a - m;
			
			m = d.multiply(a).subtract(m);
			// d = (n - m * m) / d;
			d = n.subtract(m.multiply(m)).divide(d);
			
			// a = (isqrt + m) / d;
			a = a0.add(m).divide(d);
			
			// int Nn = a * N + Np;
			BigInteger Nn = a.multiply(N).add(Np);
			
			// int Dn = a * D + Dp;
			BigInteger Dn = a.multiply(D).add(Dp);

			Np = N;
			N = Nn;

			Dp = D;
			D = Dn;
		}			
	}	
}


