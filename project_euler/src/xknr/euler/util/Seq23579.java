package xknr.euler.util;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Generates the sequence 2,3,5,7,9,...
 */
public class Seq23579 implements ISeq<Long> 
{
	public Seq23579() 
	{
		this.curr = 0;
	}

	@Override
	public boolean has() 
	{
		return true;
	}

	@Override
	public Long adv() 
	{
		if (curr > Long.MAX_VALUE - 2)
		{
			throw new NoSuchElementException();
		}
		
		if (curr == 0)
		{
			curr = 2;
		}
		else if (curr == 2) 
		{
			curr = 3;
		}
		else 
		{
			curr += 2;
		}
		
		return curr;
	}

	@Override
	public Long curr() 
	{
		if (curr == 0)
		{
			throw new NoSuchElementException();
		}
		
		return curr;
	}

	private long curr;
}
