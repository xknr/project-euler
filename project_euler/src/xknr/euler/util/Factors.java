package xknr.euler.util;

import java.util.NoSuchElementException;

public class Factors<C extends Number> implements ISeq<Long> 
{
	public Factors(long x, ISeq<C> candidates) 
	{
		this.x = x;
		this.cand = 0;		
		this.candidates = candidates;
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
			fetchNewCandidate();
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
					fetchNewCandidate();
				}				
			}
		}		

		return cand;
	}
	
	private void fetchNewCandidate() 
	{
		if (!candidates.has())
		{
			throw new NoSuchElementException();
		}
		cand = candidates.adv().longValue();
	}

	private long x;
	private long cand;
	private ISeq<C> candidates;
	
}
