package xknr.euler.e090;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Euler094Study3
{
	public void solve()
	{
		rec();
		//rec(3, 1, 0);
		
		System.out.println(count);
		
		
//		for(long m = 1; ; m += 2) {
//			for(long n = 1; n < m; n += 2) {
//				long a = m * n;
//				long b = (m * m - n * n) / 2;
//				long c = (m * m + n * n) / 2;
//				long g = Util.gcd(a, b);
//				if (g != 1)
//					System.out.format("%d %d %d\n", a, b, c);
//			}
//		}
		
		//return 0;		

//		int count = 0;
//		
//		for(long m = 1; ; m += 2) {
//			long peri2 = 2 * m * m; // same as 2*b + 2*c
//			if (peri2 > LIM) break;
//			//System.out.format("%d -> %d %s\n", m, peri2, peri2 <= LIM ? "" : "****");
//			count++;
//		}
//		
//		outer:
//		for(BigInteger M = BigInteger.ONE; ; M = M.add(TWO)) {
//			for(BigInteger N = BigInteger.ONE; N.compareTo(M) < 0; N = N.add(TWO)) {
//				BigInteger a = M.multiply(N);
//				BigInteger b = M.multiply(M).subtract(N.multiply(N)).divide(TWO);
//				BigInteger c = M.multiply(M).add(N.multiply(N)).divide(TWO);
//				System.out.format("%d %d   %d %d %d\n", M, N, a, b, c);
//				BigInteger peri1 = a.add(c).multiply(TWO);
//				if (peri1.compareTo(lim) > 0) {
//					if (N.equals(BigInteger.ONE)) {
//						break outer;
//					}
//					break;
//				}
//				count++;
//			}
//			//if (M.compareTo(BigInteger.valueOf(1000000)) > 0 && !found) break;
//		}
//		
//		//throw new RuntimeException();
//		System.out.println("finished");
//		return count;
	}
	
	private int count = 0;
	private BigInteger Count = BigInteger.ZERO;
		
	private void rec3(long u, long v, int depth) 
	{		
		long a = u * v;
		long b = (u*u - v*v) / 2;
		long c = (u*u + v*v) / 2;
			
		for(int i = 0; i < depth; i++)
		{
			System.out.print(" ");
		}
		System.out.format("%d %d\n", u, v);

		if (depth > 5) 
		{
			return;
		}

		depth++;
		
		rec3(2 * u - v, u, depth);
		rec3(2 * u + v, u, depth);
		//rec(u + 2 * v, v, depth);			
	}	
	
	private void rec() 
	{		
		List<BigInteger> stack = new ArrayList<BigInteger>();
		
		stack.add(BigInteger.valueOf(3L));
		stack.add(BigInteger.valueOf(1L));
		
		while(!stack.isEmpty()) 
		{
			//long v = stack.remove(stack.size() - 1);
			//long u = stack.remove(stack.size() - 1);
			
			BigInteger v = stack.remove(stack.size() - 1);
			BigInteger u = stack.remove(stack.size() - 1);
			
			BigInteger a = u.multiply(v);
			BigInteger b = (u.multiply(u).subtract(v.multiply(v))).divide(TWO);
			BigInteger c = (u.multiply(u).add(v.multiply(v))).divide(TWO);
			
			BigInteger peri1 = a.add(c).multiply(TWO);
			BigInteger peri2 = b.add(c).multiply(TWO);
			
			if (peri1.compareTo(lim) > 0 && peri2.compareTo(lim) > 0) 
			{
				continue;
			}
			
			Count = Count.add(lim.divide(peri1));
			Count = Count.add(lim.divide(peri2));
			
			int rem = count % 1000000; 
			
			boolean print = rem >= 0 && rem < 5;
			
			String hash = makeHash(a, b);
			
			boolean found = record.contains(hash);
			
			if (found) 
			{
				print = true;
			}
			
			record.add(hash);
			
			if (print) 
			{
				System.out.format("%8d, %8d     %d %d %d %d\n", u, v, a, b, c, Count);
				
				if (rem == 4) 
				{
					System.out.format("\n");
				}
			}
			count++;

			if (found) 
			{
				throw new RuntimeException();
			}

			stack.add(u.multiply(TWO).subtract(v)); stack.add(u);
			stack.add(u.multiply(TWO).add(v)); stack.add(u);
			stack.add(v.multiply(TWO).add(u)); stack.add(v);
		}
	}
	
	private String makeHash(BigInteger a, BigInteger b) 
	{
		if (a.compareTo(b) > 0) 
		{
			BigInteger t = a;
			a = b;
			b = t;			
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(a);
		sb.append(":");
		sb.append(b);
		
		return sb.toString();
	}

	private void rec2() 
	{		
		List<Long> stack = new ArrayList<Long>();
		
		stack.add(3L);
		stack.add(1L);
		
		while(!stack.isEmpty()) 
		{
			//long v = stack.remove(stack.size() - 1);
			//long u = stack.remove(stack.size() - 1);
			
			long u = stack.remove(0);
			long v = stack.remove(0);
			
			long a = u * v;
			long b = (u*u - v*v) / 2;
			long c = (u*u + v*v) / 2;
			
			long peri1 = a+a+c+c;
			long peri2 = b+b+c+c;
			
			if (peri1 > LIM && peri2 > LIM) 
			{
				continue;
			}
			
			count += LIM / peri1;
			count += LIM / peri2;
			
			//System.out.format("%d %d %d %d %d %d\n", u, v, a, b, c, count);

			stack.add(2 * u - v); stack.add(u);
			stack.add(2 * u + v); stack.add(u);
			stack.add(u + 2 * v); stack.add(v);			
		}
	}

	private Set<String> record = new HashSet<String>();

	private static final long LIM = 1000_000_000;
	private static final BigInteger TWO = BigInteger.valueOf(2);
	private static final BigInteger lim = BigInteger.valueOf(LIM);
}


