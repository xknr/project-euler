package xknr.euler.e090;

import static xknr.euler.util.Util.B1;
import static xknr.euler.util.Util.B2;

import java.math.BigInteger;

public class E97B extends E97
{
	public E97B(boolean doPrint) {
		super(doPrint);
	}

	/**
	 * Calculate 2^e mod MOD
	 * 
	 * Use e's bits and the sequence 
	 * 2^1, 2^2, 2^4, 2^8,...
	 * 
	 */
	public BigInteger pow2(int e) 
	{
		BigInteger p = B2, r = B1;
		
		for(; e > 0; e /= 2) 
		{
			if (e % 2 == 1) 
			{
				r = r.multiply(p).mod(MOD);
			}
			
			p = p.multiply(p).mod(MOD);
		}
		
		return r;
	}
}
