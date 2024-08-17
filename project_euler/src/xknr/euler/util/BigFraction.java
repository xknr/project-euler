package xknr.euler.util;

import java.math.BigInteger;

public class BigFraction
{
	public BigFraction(BigInteger num, BigInteger den) {
		this.num = num;
		this.den = den;
	}


	public BigFraction(long num, long den) {
		this.num = BigInteger.valueOf(num);
		this.den = BigInteger.valueOf(den);
	}
	
	public BigFraction addOne() {
		return new BigFraction(num.add(den), den);
	}

	public BigFraction addTwo() {
		return new BigFraction(num.add(den.multiply(TWO)), den);
	}
	
	public BigInteger num() {
		return num;
	}
	public BigInteger den() {
		return den;
	}


	public BigFraction reciprocal() {
		return new BigFraction(den, num);
	}
	
	private BigInteger num, den;
	
	private static final BigInteger TWO = BigInteger.valueOf(2);

	public void addWholeNumber(BigInteger wholeNumber, boolean doSimplify)
	{
		
		num = num.add( den.multiply(wholeNumber) );
		
		if (doSimplify) {
			simplify();
		}
		
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(num);
		sb.append("/");
		sb.append(den);
		sb.append("]");
		return sb.toString();
	}


	
	public BigFraction mul(BigFraction o)
	{
		return new BigFraction(this.num.multiply(o.num), this.den.multiply(o.den) );		
	}

	public BigFraction div(BigFraction o)
	{
		return mul(new BigFraction(o.den, o.num));		
	}

	public BigFraction add(BigFraction o)
	{
		BigInteger an = this.num;
		BigInteger ad = this.den;
		BigInteger bn = o.num;
		BigInteger bd = o.den;
		
		BigInteger g = ad.gcd(bd);
		
		bd = bd.divide(g);
		
		an = an.multiply(bd);
		bn = bn.multiply(ad.divide(g));
		
		BigInteger rn = an.add(bn);
		BigInteger rd = ad.multiply(bd);
		
		return new BigFraction(rn, rd);
		
	}

	/**
	 * Simplify terms in place. Returns itself.
	 * 
	 */
	public BigFraction simplify()
	{
		BigInteger g = num.gcd(den);
		num = num.divide(g);
		den = den.divide(g);
		return this;
	}
	
}

