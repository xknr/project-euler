package xknr.euler.e010;

import xknr.euler.Solution;

/**
 * Brute force.
 */
public class E19 extends Solution
{	
	public E19(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{			
		int counter = 0;
		
		int year = 1900;
		int month = 0;
		int dayOfMonth = 0;
		int dayOfWeek = 0;		
		
		while(year < 2001)
		{			
			if (year >= 1901)
			{
				if (dayOfMonth == 0 && dayOfWeek == 6) 
				{
					counter++;
				}
			}
			
			dayOfMonth++;
			int len = MONTH_LENGTHS[month];			
			if (month == 1 && isLeap(year)) 
			{
				len++;
			}
			
			if (dayOfMonth >= len) 
			{
				dayOfMonth = 0;
				month++;
				if (month >= 12) 
				{
					month = 0;
					year++;
				}
			}	
			
			dayOfWeek++;
			dayOfWeek = dayOfWeek % 7;
		}
		
		return counter;
	}
	
	public static boolean isLeap(int year) 
	{
		if (year % 400 == 0) return true;
		if (year % 100 == 0) return false;
		if (year % 4 == 0) return true;
		return false;
	}
	
	public static final int MONTH_LENGTHS[] = {
		31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31, 	
	};	
}

