package xknr.euler.util;

import java.util.List;
import java.util.NoSuchElementException;


public class FactorsPList implements ISeq<Long> 
{
	public FactorsPList(long x, List<Integer> primes) 
	{
		this.x = x;
		this.cand = 0;		
		this.primes = primes;
		this.primeInd = -1;
	}
	
	@Override
	public boolean has() 
	{
		return x > 1;
	}

	@Override
	public Long curr() 
	{
		if (primeInd == -1)
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
		
		if (primeInd == -1)
		{
			cand = primes.get(++primeInd);
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
					//cand += (cand & 1) + 1;
					cand = primes.get(++primeInd);
				}				
			}
		}		

		return cand;
	}
	
	private long x;
	private long cand;
	private int primeInd;
	
	private List<Integer> primes;
	
}
