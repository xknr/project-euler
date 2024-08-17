package xknr.euler.e040;

import xknr.euler.Solution;

public class E40B extends Solution
{

	public E40B(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{
		int prod = 1, cursor = 0;
		
		// Next larger power of 10 after cursor.
		int cursor10 = 1;
		
		for(int i = 1; ; i++)
		{
			int len = String.valueOf(i).length();
			
			int cursorNext = cursor + len;
			
			if (cursor10 - 1 < cursorNext) 
			{				
				int target = cursor10 - 1;

				println(i, cursor, target, cursorNext, len);
				
				int digitFound = String.valueOf(i).charAt(target - cursor) - '0';
				
				assert digitFound >= 0 && digitFound <= 9;
			
				// println(t, digitFound);
				
				prod *= digitFound;
				
				if (cursor10 >= TARGET)
				{
					return prod;
				}				
			}
			
			cursor = cursorNext;
			
			// Update cursor10.
			for(; cursor >= cursor10; cursor10 *= 10) { }
		}
	}

	private static final int TARGET = 1000_000;
}