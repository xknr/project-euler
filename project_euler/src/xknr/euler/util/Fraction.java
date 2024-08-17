package xknr.euler.util;

import xknr.euler.util.Util;

public class Fraction
{

	public Fraction(long num, long den) {
		if (den == 0)
			throw new IllegalArgumentException("denominator cannot be set to 0");
		this.num = num;
		this.den = den;
	}
	
	public Fraction setNum(long num) {
		this.num = num;
		return this;
	}
	
	public Fraction setDen(long den) {
		if (den == 0)
			throw new IllegalArgumentException("denominator cannot be set to 0");
		this.den = den;
		return this;
	}
	
	public Fraction set(long num, long den) {
		if (den == 0)
			throw new IllegalArgumentException("denominator cannot be set to 0");
		this.num = num;
		this.den = den;
		return this;
	}
	
//		public Fraction addOne() {
//			return new Fraction(num + (den), den);
//		}
	
//		public Fraction addTwo() {
//			return new Fraction(num+(den*(2)), den);
//		}
	
	public long num() {
		return num;
	}
	
	public long den() {
		return den;
	}


	public Fraction reciprocal() {
		return new Fraction(den, num);
	}
	
	private long num, den;
	
//		public void addWholeNumber(int wholeNumber, boolean doSimplify)
//		{
//			
//			num = num + den * wholeNumber;
//			
//			if (doSimplify) {
//				simplify();
//			}
//			
//		}
	

	public void reduce()
	{
		long g = Util.gcd(num, den);
		num = num / (g);
		den = den / (g);
	}

	public Fraction add(Fraction b, int sign) {
		
		assert sign == 1 || sign == -1;
		
		long g = Util.gcd(den, b.den);		
		long newNum = (num * b.den / g) + sign * (b.num * den / g);
		long newDen = (den / g) * b.den;		
		Fraction result = new Fraction(newNum, newDen);
		result.reduce();
		return result;		
	}

	public Fraction add(Fraction b) {		
		return add(b, 1);
	}

	public Fraction sub(Fraction b) {		
		return add(b, -1);
	}

	/**
	 * Result will be reduced only if inputs 
	 * (a and b) are reduced.
	 * 
	 * Example:
	 * a = 63/70 (can be reduced by 7)
	 * b = 25/6  (cannot be reduced)
	 * 
	 * The result will be 105/28, 
	 * which was reduced by 3 and 5 but not 7.
	 * 
	 */
	public static Fraction mul(Fraction a, Fraction b, boolean invertB)
	{		
		long an = a.num;
		long ad = a.den;
		long bn, bd;
		
		if (invertB) {
			bn = b.den;
			bd = b.num;
		} else {
			bn = b.num;
			bd = b.den;
		}
		
		long g1 = Util.gcd(an, bd);
		long g2 = Util.gcd(ad, bn);
		long newNum = (an/g1)*(bn/g2);
		long newDen = (ad/g2)*(bd/g1);
		return new Fraction(newNum, newDen);		
	}

	public Fraction mul(Fraction b) {
		return mul(this, b, false);
	}

	public Fraction div(Fraction b) {
		return mul(this, b, true);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fraction other = (Fraction) obj;

		long an = this.num, ad = this.den;
		long bn = other.num, bd = other.den;

		// 0 is special case when equal
		if (an == 0 && bn == 0)
			return true;
		
		// If only one of numerators is 0, then not equal.
		if (an == 0 || bn == 0)
			return false;

		if (Math.abs(an) < Integer.MAX_VALUE && 
			Math.abs(ad) < Integer.MAX_VALUE && 
			Math.abs(bn) < Integer.MAX_VALUE && 
			Math.abs(bd) < Integer.MAX_VALUE) {
			// No overflow possible.
			return ad * bn == bd * an;				
		}
		
		// Reduce fraction A
		long g = Util.gcd(an, ad);
		an /= g;
		ad /= g;

		// Both num and den of B must be a multiple with same factor.
		long f = bd / ad;
		return f * ad == bd && f * an == bn;
	}

	@Override
	public String toString() {
		return String.format("[%d/%d]", num, den);
//			StringBuilder sb = new StringBuilder();
//			sb.append("[");
//			sb.append(num);
//			sb.append("/");
//			sb.append(den);
//			sb.append("]");
//			return sb.toString();
	}

}