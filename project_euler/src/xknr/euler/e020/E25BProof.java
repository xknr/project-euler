package xknr.euler.e020;

import java.math.BigInteger;

public class E25BProof
{

	public long test()
	{
		int limit = 10000;
		
		int[] lengths = getLengths(limit);

		test(limit, lengths, 89, 11);
		
		return 0;
	}

	void test(int limit, int[] lengths, int first, int firstOrd) 
	{
		// Calculate digit count of all fibo numbers below limit
		// using adding log10 golden ratio method.
		
		int ord = firstOrd;
		double est = E25B.log10(first);
		for(; ord < limit; ord++) {
			assert (int)Math.floor(est) + 1 == lengths[ord];
			est += E25B.incr;
		}
	}

	/**
	 * Get a record of all fibonacci numbers below target ordinal.
	 * @param limit Target ordinal.
	 * @return An array of recorded lengths (0th element is not to be used)
	 */
	public static int[] getLengths(int limit)
	{
		BigInteger prev = B(0), curr = B(1);
		
		int[] lengths = new int[limit];
		
		for(int o = 1; o < limit; o++)
		{
			// 12th fibo number must be 144.
			assert o != 12 || curr.intValueExact() == 144;
			
			lengths[o] = curr.toString().length();
			
			curr = curr.add(prev);
			prev = curr.subtract(prev);
		}
		return lengths;
	}

	public static BigInteger B(long x) {
		return BigInteger.valueOf(x);
	}
	
	

}