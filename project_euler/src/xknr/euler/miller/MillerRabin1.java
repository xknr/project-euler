package xknr.euler.miller;

public class MillerRabin1
{

	public static boolean isPrime(int n)
	{
		
		if (n >= 25_326_001) throw new RuntimeException();
		
		assert(n >= 0);
		if (n < 7) {
			if (n <= 1) return false;	// 0 and 1
			if (n <= 3) return true; 	// 2 and 3
			if (n == 5) return true;
			return false;				// 4 and 6
		}

		if (n % 2 == 0) return false;
		if (n % 3 == 0) return false;
		if (n % 5 == 0) return false;

		// n-1 = 2^k * m
		
		int k = 0;
		int m = n - 1;
		while( (m & 0x01) == 0 ) {
			m >>>= 1;
			k++;
		}		

//		System.out.println(n + " " + m + " " + k);
		
		// for n = 561 expect k = 4 and m = 35
		
		if (!try_a(2, m, n, k)) return false;
		if (!try_a(3, m, n, k)) return false;
		return try_a(5, m, n, k);
	}

	public static boolean try_a(int a, int m, int n, int k)
	{
				
		// b0 = a^m % n
		int b = modularExp(a, m, n);		

		int n1 = n - 1;
		if (b == 1 || b == n1) return true;
		k--;


		while(k > 0) {
			long b2 = (long)b * b;
			b = (int)(b2 % n);
			if (b == 1) return false; else if (b == n1) return true;
			k--;
		}
		
		return false;
	}

	
	public static int modularExp(int b, int e, int m)
	{
		int r = 1;
		while(e > 0) {
			if ((e & 1) != 0) {
				long rb = (long)r * b;
				r = (int)(rb % m);
			}
			e >>>= 1;			
			long b2 = ((long)b) * b;
			b = (int)(b2 % m);
		}
		return r;
	}

	
}
