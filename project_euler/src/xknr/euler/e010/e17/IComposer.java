package xknr.euler.e010.e17;

public abstract class IComposer 
{
	public abstract void append(String s); 
	public abstract void reset();

	public IComposer compose(int n)
	{		
		assert n >= 1 && n <= 1000;
		
		reset();
		
		// n = 1000 has a direct answer.
		if (n == 1000)
		{ 
			append(Names.THOUSAND);
			return this;
		}
		
		final int hundreds = n / 100, rest = n % 100;		
		
		if (hundreds > 0) 
		{
			append(Names.ONES[hundreds]);
			append(Names.HUNDRED);
		}
		
		if (rest > 0) 
		{
			if (hundreds > 0) 
			{
				append(Names.AND);
			}
			
			if (rest > 10 && rest < 20) 
			{
				append(Names.TEENS[rest - 10]);
			} 
			else
			{
				final int tens = rest / 10, ones = rest % 10;
				
				if (tens > 0) 
				{
					append(Names.TENS[tens]);
				}
				
				if (ones > 0)
				{
					append(Names.ONES[ones]);
				}
			}
		}
		
		return this;
	}		
}