package xknr.euler.miller;

import java.math.BigInteger;

public class MillerRabinBig
{
	
	private static final BigInteger B11 = BigInteger.valueOf(11);
	private static final BigInteger B7 = BigInteger.valueOf(7);
	private static final BigInteger B5 = BigInteger.valueOf(5);
	private static final BigInteger B3 = BigInteger.valueOf(3);
	private static final BigInteger B2 = BigInteger.valueOf(2);
	private static final BigInteger B1 = BigInteger.ONE;
	private static final BigInteger B0 = BigInteger.ZERO;

	public static boolean isPrime(BigInteger n)
	{
		return isPrime(n, true);		
	}
	
	public static boolean isPrime(BigInteger n, boolean useLongForSmall)
	{
		if (useLongForSmall) {
			if (n.compareTo(BigInteger.valueOf(3_037_000_000L)) < 0) {
				return MillerRabin2.isPrime2(n.longValueExact());
			}
		}
		
		assert(n.signum() > 0);
		
		if (n.compareTo(B11) < 0) {
			if (n.compareTo(B1) <= 0) return false;	// 0 and 1
			if (n.compareTo(B3) <= 0) return true; 	// 2 and 3
			if (n.equals(B5)) return true;
			if (n.equals(B7)) return true;
			return false;				// 4, 6, 8, 9 and 10
		}

		if (n.remainder(B2).equals(B0)) return false;
		if (n.remainder(B3).equals(B0)) return false;
		if (n.remainder(B5).equals(B0)) return false;
		if (n.remainder(B7).equals(B0)) return false;

		// n-1 = 2^k * m
		long k = 0L;
		BigInteger m = n.subtract(B1);
		while((m.intValue() & 1) == 0) {
			m = m.divide(B2);
			k++;
		}		

		if (!try_a2(B2, m, n, k)) return false;
		if (!try_a2(B3, m, n, k)) return false;
		if (!try_a2(B5, m, n, k)) return false;
		return try_a2(B7, m, n, k);
	}


	public static boolean try_a2(BigInteger a, BigInteger m, BigInteger n, long k)
	{
		// b = a^m % n
		BigInteger b = modularExp2(a, m, n);		
		if (b.equals(B1)) return true;
		
		BigInteger n1 = n.subtract(B1);
		if (b.equals(n1)) return true;
		
		k--;

		while(k > 0L) {
			b = b.multiply(b).remainder(n);
			if (b.equals(B1)) {
				return false; 
			} else if (b.equals(n1)) {
				return true;
			}
			k--;
		}
		
		return false;
	}
	
	public static BigInteger modularExp2(BigInteger b, BigInteger e, BigInteger m)
	{
		BigInteger r = B1;
		while(e.compareTo(B0) > 0) {
			if ((e.intValue() & 1) != 0) {
				r = r.multiply(b).remainder(m);
			}
			e = e.divide(B2);
			b = b.multiply(b).remainder(m);
		}
		return r;
	}

}
