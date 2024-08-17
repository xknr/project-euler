package xknr.euler.util;

import java.util.NoSuchElementException;


public class FactorsFixed implements ISeq<Long> 
{
	private long x;
	private long cand;
	
	public FactorsFixed(long x) 
	{
		this.x = x;
		this.cand = 0;		
	}
	
	@Override
	public boolean has() 
	{
		return x > 1;
	}

	@Override
	public Long curr() 
	{
		if (cand == 0)
		{
			throw new NoSuchElementException();
		}
		return cand;
	}

	@Override
	public Long adv() 
	{
		if (x <= 1) 
		{
			throw new NoSuchElementException();
		}
		
		if (cand == 0)
		{
			cand = 2;
		}
		
		while(x > 1) // While not depleted,
		{	
			long d = x / cand; // Try to divide.			
			
			if (d * cand == x) // If it divides, 
			{
				// Found a prime factor.
				x = d;    // Update remaining.
				break;
			}
			else // If it couldn't divide,
			{				
				if (d < cand) // cand >= sqrt(current x), 
				{
					// No more prime factors are possible.
					// Current value of x is the last prime factor.
					cand = x;
					x = 1;
					break;
				}
				else // cand < sqrt(current x)
				{
					// Try the next number in the sequence: 2, 3, 5, 7, 9...
					cand += (cand & 1) + 1; 
				}				
			}
		}		

		return cand;
	}
	
	
}
