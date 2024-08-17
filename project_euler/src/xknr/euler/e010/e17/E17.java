package xknr.euler.e010.e17;

import xknr.euler.Solution;

public class E17 extends Solution
{
	public E17(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{	
		int sum = 0;

		for(int n = 1; n <= LIM; n++) 	
		{
			sum += compose(n).length();
		}
		
		return sum;
	}

	public String compose(int n)
	{		
		assert n >= 1 && n <= LIM;
				
		if (n == 1000) 
		{
			return Names.THOUSAND;
		}
				
		int hundreds = n / 100, rest = n % 100;
		
		StringBuilder sb = new StringBuilder();
		
		if (hundreds > 0)
		{
			sb.append(Names.ONES[hundreds] + Names.HUNDRED);
		}
		
		if (rest > 0) 
		{
			if (hundreds > 0) 
			{
				sb.append(Names.AND);
			}
			
			if (rest > 10 && rest < 20) 
			{
				sb.append(Names.TEENS[rest - 10]);				
			}
			else 
			{
				int tens = rest / 10, ones = rest % 10;
				
				if (tens > 0) 
				{
					sb.append(Names.TENS[tens]);					
				}
				if (ones > 0)
				{
					sb.append(Names.ONES[ones]);
				}
			}
		}
		
		return sb.toString();		
	}
	
	public static final int LIM = 1000;
}

