package xknr.euler.miller;

import java.math.BigInteger;

public class MillerRabin3
{
	
	public static boolean isPrime(long n)
	{
		assert(n >= 0L);
		
		if (n < 11L) {
			if (n <= 1L) return false;	// 0 and 1
			if (n <= 3L) return true; 	// 2 and 3
			if (n == 5L) return true;
			if (n == 7L) return true;
			return false;				// 4, 6, 8, 9 and 10
		}

		if (n % 2L == 0L) return false;
		if (n % 3L == 0L) return false;
		if (n % 5L == 0L) return false;
		if (n % 7L == 0L) return false;
		
		// n-1 = 2^k * m
		
		long k = 0L;
		long m = n - 1L;
		while( (m & 1L) == 0L ) {
			m >>>= 1L;
			k++;
		}	
		
		return tryA(2L, m, n, k) 
			&& tryA(3L, m, n, k)
			&& tryA(5L, m, n, k)
			&& tryA(7L, m, n, k);
	
				
//		if (!tryA(2L, m, n, k)) return false;
//		if (!tryA(3L, m, n, k)) return false;
//		if (!tryA(5L, m, n, k)) return false;
//		return tryA(7L, m, n, k);
	}

	public static boolean tryA(long a, long m, long n, long k)
	{
		// b0 = a^m % n
		long b = modularExp2(a, m, n);		

		long n1 = n - 1L;
		if (b == 1L || b == n1) {
			return true;
		}
		k--;

		while(k > 0L) {
			b = mulMod(b, b, n);
			if (b == 1L) {
				return false; 
			} else if (b == n1) {
				return true;
			}
			k--;
		}
		
		return false;
	}
	
	public static long modularExp2(long b, long e, long m)
	{
		long r = 1L;
		while(e > 0L) {
			if ((e & 1L) != 0L) {
				r = mulMod(r, b, m);
			}
			e >>>= 1L;
			b = mulMod(b, b, m);
		}
		return r;
	}

	private static long mulMod(long r, long b, long m)
	{		
		r %= m;
		b %= m;
		
		if (Long.MAX_VALUE / r >= b) {
			return (r * b) % m;
		}
		BigInteger R = BigInteger.valueOf(r);
		BigInteger B = BigInteger.valueOf(b);
		BigInteger M = BigInteger.valueOf(m);
		return R.multiply(B).remainder(M).longValueExact();
	}

}
