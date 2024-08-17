package xknr.euler.e050;

import java.util.*;

import xknr.euler.Solution;

public class E52 extends Solution
{	
	public E52(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{
		for(int x = 1; ; x++) 
		{
			if (found(x)) 
			{
				return x;
			}
		}
	}

	/**
	 * Do all 6 multiples of a given number have same digits? 
	 * @param x The given number
	 * @return
	 */
	private boolean found(int x)
	{
		char[] first = null;
		
		for(int i = 0; i < NUM_MULTPILES; i++) 
		{
			int multiple = x * (i + 1);
			
			char[] cs = sortedDigits(multiple);
			
			if (first == null) 
			{
				first = cs;
			}
			else if (!same(first, cs)) 
			{
				return false;
			}
		}
		
		return true;
	}

	/**
	 * Integer -> String -> characters -> sort
	 * @param x
	 * @return
	 */
	public static char[] sortedDigits(int x) 
	{
		String s = Integer.toString(x);
		char[] cs = s.toCharArray();
		Arrays.sort(cs);
		return cs;
	}

	/**
	 * Are two char arrays same?
	 * @param a First array.
	 * @param b Second array.
	 * @return
	 */
	public static boolean same(char[] a, char[] b)
	{
		if (a.length != b.length)
		{
			return false;
		}
		
		for(int i = 0; i < a.length; i++) 
		{
			if (a[i] != b[i]) 
			{
				return false;
			}
		}
		
		return true;
	}

	private static final int NUM_MULTPILES = 6;
}

