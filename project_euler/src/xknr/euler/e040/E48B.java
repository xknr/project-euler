package xknr.euler.e040;

import java.math.BigInteger;

import xknr.euler.Solution;

public class E48B extends Solution
{
	public E48B(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{		
		long sum = 0;
		
		for(long x = 1; x <= 1000; x++) 
		{			
			long result = powerMod(x, x, MOD, BASE);

			sum = (sum + result) % MOD;
		}

		return sum;
	}
	
	/**
	 * calculate x ^ y mod m
	 * using a step method 
	 * @param x 
	 * @param y
	 * @param m mod
	 * @param base Needed for calculating large powers with modular arithmetic. 
	 * @return
	 */
	private long powerMod(long x, long y, long m, long base) 
	{
		// Use binary representation of one number 
		// and 1,2,4,8...th powers of the other.
		long powX = x, result = 1;
		
		for(long d = y; d > 0; d /= 2) 
		{
			if (d % 2 != 0) 
			{
				result = mulModLarge(result, powX, m, base);
			}
			
			powX = mulModLarge(powX, powX, m, base);
		}
		
		return result;
	}

	private long mulModLarge(long a, long b, long mod, long base)
	{		
		/*
		 * assume MOD = 1000
		 * 
		 * 978
		 * 624  x
		 * ------
		 * 
		 * 4 * 978   mod 1000 = 912
		 * 20 * 978  mod 1000 = 560
		 * 600 * 978 mod 1000 = 800
		 * 
		 * instead:
		 * 
		 * 4 * 978 mod 1000 = 912, 912 * 1 = 912
		 * 2 * 978 mod 100  = 56 , 56 * 10 = 560
		 * 6 * 978 mod 10   = 8  , 8 * 100 = 800
		 * 
		 * decreasing powers of 10 are n, starts with mod
		 * increasing powers of 10 are m, starts with 1
		 * 
		 * instead of dividing by 10 at each step, we will divide by base,
		 * which should be a larger factor of MOD.
		 */
		
		//System.out.format("%d x %d mod %d (%d)\n", a, b, mod, mulModLarge1(a, b, mod));
		
		long result = 0;
		long m = 1, n = mod;
		
		for(long b_ = b; b_ > 0; b_ /= base)
		{
			// Next digit of b in base base
			long digit = b_ % base; 	
			
			long term = (a * digit) % n;

			// mod can be omitted
			term = (term * m) % mod;
			
			//System.out.format("%d x %d mod %d x %d\n", a, digit, n, m);

			// mod can be omitted
			result = (result + term) % mod;
			
			m *= base;
			n /= base;
		}
		
//		System.out.format("result = %d\n", result);
		return result;
	}
	
	public static long mulModBig(long a, long b, long mod) 
	{
		BigInteger B = BigInteger.valueOf(b);
		BigInteger A = BigInteger.valueOf(a);
		BigInteger MOD = BigInteger.valueOf(mod);			
		return A.multiply(B).mod(MOD).longValueExact();				
	}

	private static final long MOD = 10_000_000_000L;
	
	/*
	 * Long.MAX_VALUE / MASK = 922337203
	 * base can be any factor of mask less than this
	 */
	private static final long BASE = 100_000_000; 

}

