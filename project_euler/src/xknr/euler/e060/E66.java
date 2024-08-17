package xknr.euler.e060;


import static xknr.euler.util.Util.B;
import static xknr.euler.util.Util.B0;
import static xknr.euler.util.Util.B1;

import java.math.BigInteger;

import xknr.euler.Solution;

/**
 * 
 * https://en.wikipedia.org/wiki/Diophantine_equation
 * https://en.wikipedia.org/wiki/Pell%27s_equation
 * https://en.wikipedia.org/wiki/Chakravala_method
 * https://en.wikipedia.org/wiki/Brahmagupta%27s_identity
 * https://en.wikipedia.org/wiki/Modular_multiplicative_inverse
 * https://en.wikipedia.org/wiki/Extended_Euclidean_algorithm
 * 
 */

public class E66 extends Solution
{
	public E66(boolean doPrint) {
		super(doPrint);
	}

	public static long[] extendedEuclid(long a, long b)
	{
		long rprev = a;
		long sprev = 1;
		long tprev = 0;
		
		long r = b;
		long s = 0;
		long t = 1;
		
		while(r != 0)
		{
			long q = rprev / r;
			
			//print(String.format("%d %d %d %d", r, s, t, q));
			
			long rnext = rprev - q * r;
			long snext = sprev - q * s;
			long tnext = tprev - q * t;
			
			sprev = s;
			tprev = t;
			rprev = r;
			
			r = rnext;
			s = snext;
			t = tnext;
		}
		
		return new long[] { rprev, sprev, tprev };		
	}

	public static BigInteger[] extendedEuclid(BigInteger a, BigInteger b)
	{
		BigInteger rprev = a;
		BigInteger sprev = B1;
		BigInteger tprev = B0;
		
		BigInteger r = b;
		BigInteger s = B0;
		BigInteger t = B1;
		
		while(r.compareTo(B0) != 0)
		{
			BigInteger q = rprev.divide(r);
			
			//print(String.format("%d %d %d %d", r, s, t, q));
			
			BigInteger rnext = rprev.subtract(q.multiply(r));
			BigInteger snext = sprev.subtract(q.multiply(s));
			BigInteger tnext = tprev.subtract(q.multiply(t));
			
			sprev = s;
			tprev = t;
			rprev = r;
			
			r = rnext;
			s = snext;
			t = tnext;
		}
		
		return new BigInteger[]{rprev, sprev, tprev};
	}

	public long chakravala(long n)
	{
		println("");
		println("=================================================");
		println("n = " + n);
		println("");
		
		double fs = Math.sqrt(n);
		long s = (long)(Math.floor(fs));
		long s_upper = (long)(Math.ceil(fs));
		
		if (s == s_upper) 
		{
			return 0;
		}
		
		long a = s_upper; 
		long b = 1;
		
		long k = a * a - b * n;

		println("fs = " + fs);

//		long a = 41;
//		long b = 5;
//		long k = 6;
						
		while(true)
		{			
			println("");
			println("-------------------------------");
			println("");
			
			long ak = Math.abs(k);
			
			//if (ak == 1 || ak == 2 || ak == 4) break;
			
			if (k == 1) 
			{
				break;
			}
		
			println(String.format("a = %d, b = %d, k = %d", a, b, k));

			if (a <= 0) throw new AssertionError();
			if (b <= 0) throw new AssertionError();
			
			// Find m where a + bm is divisible by k 
			// and |m^2 - n| is minimum
			// This means: (b * m) % k = -a 			
			
			// First solve m for: (b * m) % k = gcd(b, k)
			// Which probably means: (b * m) % k = 1
			
			long[] ex = extendedEuclid(k, b);
			long gcd = ex[0];
			long m1 = ex[2];
			println(gcd + " " + m1);
			
			// Let us be notified us if this case occurs... 
			// I don't think this is likely to happen.
			//if (gcd != 1) throw new AssertionError(); 
			
			// -a must be divisible by gcd(k, b) for a solution to exist.
			// As far as I understood, we should expect this to be the case.
			if (-a % gcd != 0) throw new AssertionError(); 
			
			// (b * m1 * -a / gcd) % k = -a 
			
			m1 = m1 * ( (-a / gcd) % k );
			m1 = (m1 + ak) % k; // use a positive number for now
			println("m1 = " + m1);
			
			// All m where m % (k / gcd(b, k)) = m1 can be used.
			// Choose m which yields the lowest |m^2 - n|.
			// Note: chosen m should not cause a + bm = 0.
			
			long ss[] = new long[4];
			
			println("s = " + s);
			ss[0]  = s / (k/gcd) * (k/gcd) + m1;
			ss[1] = ss[0] < fs ? ss[0] + ak : ss[0] - ak;
//			println(ss[0] + " " + ss[1]);
			
			ss[2]  = -s / (k/gcd) * (k/gcd) + m1 - ak;
			ss[3] = ss[2] > -fs ? ss[2] - ak : ss[2] + ak;
			
//			println(ss[2] + " " + ss[3]);
	
			long best_m = 0;
			double best_m_dist = Double.MAX_VALUE;
			for(int j = 0; j < 
					// ss.length
					2
					; j++)
			{
				double dist = Math.abs(ss[j]*ss[j] - n);
				
				println(String.format("evaluate %d: %d -> %f", ss[j], ss[j]*ss[j], dist));
				
				if (dist < best_m_dist) 
				{				
					if (a + b * ss[j] != 0) 
					{
						best_m_dist = dist;
						best_m = ss[j];
					}
				}
			}
			
			//if (i == 1) best_m = 5;
			
			println("best_m  = " + best_m);
			
			long m = best_m;
			
			long a_next = (a * m + n * b) / ak;
			long b_next = (a + b * m) / ak;
			long k_next = (m * m - n) / k;
			
			a = a_next;
			b = b_next;
			k = k_next;
			
			//println(String.format("%d %d %d", a_next, b_next, k_next));
		}
		
		format("a = %d, b = %d, k = %d", a, b, k);
		
		return 0;		
	}	
	
	public BigInteger chakravala2(long _n, int [] out_num_iterations)
	{
		println("=================================================");
		println("n = " + _n);
		println("");
				
		double fs = Math.sqrt(_n);
		long _s = (long)(Math.floor(fs) + 0.1);
		long s_upper = (long)(Math.ceil(fs) + 0.1);
		
		if (_s == s_upper)
		{
			return B0;
		}
		
		BigInteger n = B(_n);
		BigInteger s = B(_s);
		BigInteger a = B(s_upper); 
		BigInteger b = B1;		
		
		BigInteger k = a.multiply(a).subtract(b.multiply(n));

		println("fs = " + fs);

//		long a = 41;
//		long b = 5;
//		long k = 6;		
				
		int num_iterations;
		for(num_iterations = 0; ; num_iterations++)
		{			
			println("");
			println("-------------------------------");
			println("iteration = " + num_iterations);
			println("");
			
			BigInteger ak = k.abs();
			//if (ak == 1 || ak == 2 || ak == 4) break;
			if (k.compareTo(B1) == 0) break;
		
			println(String.format("a = %d, b = %d, k = %d", a, b, k));

			if (a.compareTo(B0) <= 0) throw new AssertionError();
			if (b.compareTo(B0) <= 0) throw new AssertionError();
			
			// Find m where a + bm is divisible by k 
			// and |m^2 - n| is minimum
			// This means: (b * m) % k = -a 
			
			
			// First solve m for: (b * m) % k = gcd(b, k)
			// Which probably means: (b * m) % k = 1
			
			BigInteger[] ex = extendedEuclid(k, b);
			BigInteger gcd = ex[0];
			BigInteger m1 = ex[2];
			println(gcd + " " + m1);
			
			// Let us be notified us if this case occurs... 
			// I don't think this is likely to happen.
			//if (gcd != 1) throw new AssertionError(); 
			
			// -a must be divisible by gcd(k, b) for a solution to exist.
			// As far as I understood, we should expect this to be the case.
			if (a.negate().mod(gcd.abs()).compareTo(B0) != 0) throw new AssertionError(); 
			
			// (b * m1 * -a / gcd) % k = -a 
			
			//m1 = m1 * ( (-a / gcd) % k );
			m1 = m1.multiply(a.negate().divide(gcd).mod(ak));
					
			// use a positive number for now
			//m1 = (m1 + ak) % k; 
			m1 = m1.add(ak).mod(ak);
					
			println("m1 = " + m1);
			
			// All m where m % (k / gcd(b, k)) = m1 can be used.
			// Choose m which yields the lowest |m^2 - n|.
			// Note: chosen m should not cause a + bm = 0.
			

			BigInteger ss[] = new BigInteger[2];
			
			println("s = " + s);
			//ss[0]  = s / (k/gcd) * (k/gcd) + m1;
			BigInteger k_div_gcd = k.divide(gcd); 
			ss[0] = s.divide(k_div_gcd).multiply(k_div_gcd).add(m1);
			//ss[1] = ss[0] < fs ? ss[0] + ak : ss[0] - ak;
			ss[1] = ss[0].longValueExact() < fs  ? ss[0].add(ak) : ss[0].subtract(ak);

			// Skipping negative values... Caused problems somewhere down the line...
//			ss[2]  = -s / (k/gcd) * (k/gcd) + m1 - ak;
//			ss[3] = ss[2] > -fs ? ss[2] - ak : ss[2] + ak;
	
			BigInteger best_m = B0;
			double best_m_dist = Double.MAX_VALUE;
			for(int j = 0; j < ss.length; j++) 
			{
				double dist = Math.abs(ss[j].longValueExact()*ss[j].longValueExact() - _n);
				
				println(String.format("evaluate %d: %d -> %f", 
					ss[j], 
					ss[j].longValueExact() * ss[j].longValueExact(), 
					dist)
				);
				
				if (dist < best_m_dist) 
				{				
					if (a.add( b.multiply(ss[j])).compareTo(B0) != 0) 
					{
						best_m_dist = dist;
						best_m = ss[j];
					}
				}
			}
			
			println("best_m  = " + best_m);

			BigInteger m = best_m;
			
			//long a_next = (a * m + n * b) / ak;
			BigInteger a_next = a.multiply(m).add(n.multiply(b)).divide(ak);
			
			//long b_next = (a + b * m) / ak;
			BigInteger b_next = a.add(b.multiply(m)).divide(ak);
			
			//long k_next = (m * m - n) / k;
			BigInteger k_next = m.multiply(m).subtract(n).divide(k);
			
			a = a_next;
			b = b_next;
			k = k_next;
			
			//println(String.format("%d %d %d", a_next, b_next, k_next));
		}
		
		println(String.format("a = %d, b = %d, k = %d", a, b, k));
		
		out_num_iterations[0] = num_iterations; 
		return a;		
	}
	
	public long solve()
	{
		// println(extendedEuclid(24, 16));
		
		int best_n = 0;
		BigInteger best_x = B0;
		
		for(int n = 0; n <= 1000; n++) 
		{
			int[] num_iterations = new int[1];
			BigInteger x = chakravala2(n, num_iterations);
			format("%d\t %d\t %d\n", n, num_iterations[0], x);
			if (x.compareTo(B0) == 0)
			{
				continue;
			}
			
			if (x.compareTo(B0) < 0) throw new AssertionError();
			
			if (x.compareTo(best_x) > 0) 
			{
				best_x = x;
				best_n = n;
			}			
		}
	
		println(best_x + " " +  best_n);
				
//		// Brahmagupta's identity:
//		// Compose (a, b, k) with itself: 
//		long a_next = (a*a + n*b*b) / Math.abs(k);
//		long b_next = (2*a*b)  / Math.abs(k);
//		long k_next = 1;
//		println(String.format("a = %d, b = %d, k = %d", a_next, b_next, k_next));

		return best_n;
	}

}

