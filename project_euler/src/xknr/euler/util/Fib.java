package xknr.euler.util;

import java.util.NoSuchElementException;

/*
 * https://oeis.org/A000045
 * 
 * F(0) = 0 and F(1) = 1.
 * 
 * Starts from F(0)
 * 
 */
public class Fib implements ISeq<Long> 
{
	@Override
	public Long curr() 
	{
		if (prev == ILLEGAL_VALUE)
		{
			// adv has not been called yet.
			throw new NoSuchElementException();
		}
		return prev;
	}

	@Override
	public boolean has() {
		return curr != ILLEGAL_VALUE;
	}
	
	@Override
	public Long adv() 
	{
		if (curr == ILLEGAL_VALUE)
		{
			// Final call the advance would overflow.
			throw new NoSuchElementException();
		}
		
		if (prev == ILLEGAL_VALUE) 
		{
			prev = 0;
			curr = 1;
		}
		else if (curr > Long.MAX_VALUE - prev)
		{
			prev = curr;
			curr = ILLEGAL_VALUE;
		} 
		else
		{
			long next = curr + prev;			
			prev = curr;
			curr = next;
		}
		
		return prev;
	}

	private long prev = ILLEGAL_VALUE;
	private long curr = 0;
	
	private static final long ILLEGAL_VALUE = -1;

}
