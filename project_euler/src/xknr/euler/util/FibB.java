package xknr.euler.util;

import java.math.BigInteger;
import java.util.NoSuchElementException;

public class FibB implements ISeq<BigInteger> 
{
	@Override
	public BigInteger curr() 
	{
		if (prev.equals(Util.B(-1)))
		{
			// adv has not been called yet.
			throw new NoSuchElementException();
		}
		
		return prev;
	}

	@Override
	public boolean has() 
	{
		return true;
	}

	@Override
	public BigInteger adv() 
	{
		if (prev.equals(Util.B(-1)))
		{
			prev = Util.B0;
			curr = Util.B1;
		} else {
			// (prev, curr) <- (curr, curr + prev)		
			BigInteger next = curr.add(prev);
			prev = curr;
			curr = next;
		}
		return prev;
	}

	private BigInteger prev = Util.B(-1), curr = Util.B0;
}
