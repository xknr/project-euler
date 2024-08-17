package xknr.euler.e100;

import static xknr.euler.util.Util.B;
import static xknr.euler.util.Util.B0;
import static xknr.euler.util.Util.B1;
import static xknr.euler.util.Util.B2;

import java.math.BigInteger;

import xknr.euler.Solution;

public class E100 extends Solution
{
	public E100(boolean doPrint) {
		super(doPrint);
	}

	/**
	 * https://oeis.org/A046090
	 * Consider all Pythagorean triples (X,X+1,Z) ordered by increasing Z; 
	 * The sequence gives X+1 values.
	 * 
	 * https://en.wikipedia.org/wiki/Tree_of_primitive_Pythagorean_triples#Alternative_methods_of_generating_the_tree
	 * 
	 * This is mentioned in comments in projecteuler forum for this question:
	 * https://oeis.org/A029549
	 */	
	public long solve() 
	{	
		// Initial values for (u, v).
		long u = 3, v = 1;

		// Tree of pythagorean triples, but only first branch is evaluated.
		while(true) 
		{
			// Calculate edges of triangle.
			// (u, v) -> (a, b, c)
			long pa = u * v;
			long pb = (u * u - v * v) / 2;
			
			// We don't need the hypotenuse. 
			// long pc = (u*u + v*v) / 2;
			
			long c = Math.max(pa, pb);
			
			format("%d\n", c);

			if (c > LIM)
			{
				return calcResult(c);
			}
			
			// Descend into the first branch:
			// (u, v) -> (u2, v2)
			final long u2 = 2 * u + v;
			v = u;
			u = u2;
		}
	}

	public long calcResult(long c) 
	{
		// C*C - C = FC
		// B*B - B = FB
		// FB = FC / 2
		
		BigInteger C = B(c);
		BigInteger FC = f(C);
		BigInteger FB = FC.divide(B2);
		
		// estimate for b = c / sqrt(2)
		BigInteger B = C.multiply(NUM).divide(DEN);
		
		// Solve for B: B*B - B = FB
		while(true)
		{			
			int comp = f(B).compareTo(FB);
			
			assert comp >= 0;
			
			if (comp == 0)
			{
				break;
			}
			
			B = B.subtract(B1);
		}

		format("%d %d %d %d\n", C, FC, FB, B.multiply(B).subtract(B));
		
		return B.longValueExact();
	}
	
	public static BigInteger f(BigInteger n) 
	{
		return n.multiply(n).subtract(n);
	}

	private static final long LIM = 1_000_000_000_000L;
	
	// 1 / sqrt(2) expanded by 10^8
	private static final long NUM_ = 100_000_000_000L;
	private static final long DEN_ = 141_421_356_237L;
	private static final BigInteger NUM = B(NUM_);
	private static final BigInteger DEN = B(DEN_);
}
