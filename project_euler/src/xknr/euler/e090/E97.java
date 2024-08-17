package xknr.euler.e090;

import static xknr.euler.util.Util.B1;
import static xknr.euler.util.Util.B2;

import java.math.BigInteger;

import xknr.euler.Solution;

public class E97 extends Solution
{
	public E97(boolean doPrint) {
		super(doPrint);
	}
	
	public long solve()
	{		
		return pow2(EXP).multiply(BASE).add(B1).mod(MOD).longValueExact();		
	}

	/**
	 * Calculate 2^e mod MOD
	 */
	public BigInteger pow2(int exp) 
	{		
		BigInteger p = B1;
		
		for(int i = 0; i < exp; i++) 
		{
			// r *= 2
			p = p.multiply(B2).mod(MOD);
		}
		
		return p;		
	}

	protected static final BigInteger BASE = BigInteger.valueOf(28433);
	protected static final int EXP = 7830457;
	protected static final BigInteger MOD = BigInteger.valueOf(10_000_000_000L);
}
