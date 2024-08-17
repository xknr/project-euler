package xknr.euler.e030;

import xknr.euler.Solution;

public class E38 extends Solution
{
	/*
	 * A five digit number will exceed 9 digits at the first append.
	 * So max seed digits can be 4. 
	 */	
	public static final int SEED_LIMIT = 10000;
	
	public E38(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{	
		int best = 0;
		
		for(int seed = 1; seed < SEED_LIMIT; seed++)
		{						
			StringBuilder sb = new StringBuilder();
			
			for(int n = 1; ; n++)
			{
				sb.append(seed * n);
				
				if (sb.length() > 9) 
					break;
				
				if (sb.length() == 9)
				{
					if (isPanDigital(sb.toString()))
					{
						println(seed, sb, n);
						int num = Integer.parseInt(sb.toString());
						best = Math.max(num, best);
					}
				}
			}
			
		}
		
		return best;
	}

	/**
	 * Is the number represented by the string, 1-9 pandigital?
	 * Bit mask based method.
	 * @param str
	 * @return
	 */
	public static boolean isPanDigital(String str)
	{
		assert str.length() == 9;
		
		int bits = 0;
		
		for(char c: str.toCharArray())
		{
			// Get next digit.
			int d = c - '0';
			assert d >= 0 && d <= 9;
			
			// 0 cannot exist.
			if (d == 0) 
				return false;
			
			// d'th bit
			int mask = 1 << (d - 1);
			
			// If bit is already set, then fail.
			if ((bits & mask) != 0)
				return false;
			
			// Set bit.
			bits |= mask;
		}
		
		return true;
	}
	
	/**
	 * Is the number represented by the string, 1-9 pandigital?
	 * Array based method.
	 * @param str
	 * @return
	 */
	public static boolean isPanDigitalArr(String str)
	{
		assert str.length() == 9;
		
		boolean [] digits = new boolean [10];
		for(char c: str.toCharArray())
		{
			int d = c - '0';
			assert d >= 0 && d <= 9;
			
			if (d == 0) 
				return false;
			
			if (digits[d]) 
				return false;
			
			digits[d] = true;
		}
		
		return true;
	}


}