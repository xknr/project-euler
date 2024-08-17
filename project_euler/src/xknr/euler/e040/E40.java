package xknr.euler.e040;

import xknr.euler.Solution;

/*
 * Brute force.
 */
public class E40 extends Solution
{
	public E40(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{
		StringBuilder sb = new StringBuilder();
		
		int i = 1;
		while(sb.length() < TARGET)
		{
			sb.append(Integer.toString(i));
			i++;
		}
		
		String str = sb.toString();
		
		int product = 1;
		
		product *= str.charAt(1-1) - '0';
		product *= str.charAt(10-1) - '0';
		product *= str.charAt(100-1) - '0';
		product *= str.charAt(1000-1) - '0';
		product *= str.charAt(10000-1) - '0';
		product *= str.charAt(100000-1) - '0';
		product *= str.charAt(1000000-1) - '0';

//		System.out.println(str.charAt(1));
//		System.out.println(str.charAt(10));
//		System.out.println(str.charAt(100));
//		System.out.println(str.charAt(1000));
//		System.out.println(str.charAt(10000));
//		System.out.println(str.charAt(100000));
//		System.out.println(str.charAt(1000000));

		return product;
	}
	
	private static final int TARGET = 1000_000;
}
