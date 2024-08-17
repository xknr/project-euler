package xknr.euler.e020;

import java.util.Arrays;

import xknr.euler.Solution;
import xknr.euler.util.Util;

public class E24 extends Solution
{	
	public E24(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{		
		// 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
		int[] arr = new int[10];
		Arrays.setAll(arr, i -> i);
		
		for(int i = 1; i < TARGET; i++) 
		{
			Util.nextPerm(arr, Util.comp);
		}

		return Util.read(arr, 0, arr.length);
	}
		
	public static final int TARGET = 1000_000;
}