package xknr.euler.e060;

import java.util.ArrayList;
import java.util.List;

import xknr.euler.Solution;
import xknr.euler.util.Util;


public class E68 extends Solution
{
	public E68(boolean doPrint) {
		super(doPrint);
	}

	/*
	 * 
	 *      0
	 *       \
	 *        1   3
	 *      /  \ /
	 *    8     2
	 *  /  \    / 
	 * 9    6--4---5
	 *       \
	 *        7
	 * 
	 */

	public long solve() 
	{		
		int[] es = {9,8,7,6,5,4,3,2,1,10};

		List<Integer> unused = new ArrayList<Integer>();
		
		for(int e: es) 
		{
			unused.add(e);
		}

		String s = rec(unused, 0);
		
		return Long.parseLong(s);
	}

	private String rec(List<Integer> unused, int depth)
	{		
		if (MAX_DEPTH == depth) 
		{
			String s = combToStr();
			
			if (s.length() == 16) 
			{
				printFound(s);
				return s;
			}
			
			return null;
		}
		
		for(int i = 0; i < unused.size(); i++)
		{
			cc[depth] = unused.get(i);

			// First element must be the "numerically least external node".
			if (depth == 3 && cc[3] < cc[0]) continue;
			if (depth == 5 && cc[5] < cc[0]) continue;
			if (depth == 7 && cc[7] < cc[0]) continue;
			if (depth == 9 && cc[9] < cc[0]) continue;
			
			if (depth == 4) {				 
				if ( sum3(0) != sum3(2) ) continue;
			} else if (depth == 6) {				 
				if ( sum3(0) != sum3(4) ) continue;
			} else if (depth == 8) {				 
				if ( sum3(0) != sum3(6) ) continue;
			} else if (depth == 9) {				 
				if ( sum3(0) != cc[9] + cc[8] + cc[1] ) continue;
			}
			
			String result = rec(Util.removeOneElem(unused, i), depth + 1);
			
			if (result != null) 
				return result;
		}
	
		return null;
	}

	private void printFound(String s)
	{
		if (!doPrint()) 
		{
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(String.format("%17s ", s));
		
		sb.append(String.format("%3d ", s.length()));			
		
		for(int i = 0; i < cc.length; i++) 
		{
			sb.append(" ");
			sb.append(cc[i]);
		}
		
		println(sb);
	}

	private String combToStr()
	{
		StringBuilder sb = new StringBuilder();
		
		for(int index: toStrIndices)
		{
			sb.append(cc[index]);
		}
		
		String s = sb.toString();
		
		return s;
	}

	private int sum3(int beginOffset)
	{
		int result = cc[beginOffset++];
		result += cc[beginOffset++];
		result += cc[beginOffset];
		return result;
	}

	private static final int MAX_DEPTH = 10;

	/*
	 * Current combination.
	 */
	private int[] cc = new int[MAX_DEPTH];

	private static final int toStrIndices[] = {0,1,2,3,2,4,5,4,6,7,6,8,9,8,1};	
}

