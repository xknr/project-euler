package xknr.euler.miller;

public class MillerRabin2
{
	
	public static boolean isPrime2(long n)
	{
		
		// if (n >= 3_215_031_751L) throw new RuntimeException();

		// using a lower limit because long*long causes overflow 
		// sqrt(Long.MAX_VALUE) is slightly higher than this limit
		// sqrt(Long.MAX_VALUE) ~= sqrt(4^32 * 2^32) ~= 3,037,000,499
		if (n >= 3_037_000_000L) throw new RuntimeException();
		
		// System.out.println(3_037_000_000L * 3_037_000_000L);

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
		while( (m & 0x01L) == 0L ) {
			m >>>= 1L;
			k++;
		}		
		
		if (!try_a2(2L, m, n, k)) return false;
		if (!try_a2(3L, m, n, k)) return false;
		if (!try_a2(5L, m, n, k)) return false;
		return try_a2(7L, m, n, k);
	}

	public static boolean try_a2(long a, long m, long n, long k)
	{
		// b0 = a^m % n
		long b = modularExp2(a, m, n);		

		long n1 = n - 1L;
		if (b == 1L || b == n1) {
			return true;
		}
		k--;

		while(k > 0L) {
			b = (b * b) % n;
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
				r = (r * b) % m;
			}
			e >>>= 1L;
			b = (b * b) % m;
		}
		return r;
	}

}
