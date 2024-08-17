package xknr.euler.e010;

import xknr.euler.Solution;

/**
 * Jumps from beginning of one month to the beginning of next. 
 * No need to keep track of current day of month.
 */

public class E19B extends Solution
{
	public E19B(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{			
		reset();

		int counter = 0;

		while(year < 2001) 
		{
			if (year >= 1901 && dayOfWeek == 6) 
			{
				// Count these days only.
				counter++;
			}
			incrementMonth();
		}
		
		return counter;
	}
	
	private void incrementMonth()
	{
		dayOfWeek = (dayOfWeek + monthLen) % 7;
		
		month++;
		if (month >= 12) 
		{
			month = 0;
			year++;
		}

		updateMonthLength();
	}

	private void updateMonthLength()
	{
		monthLen = E19.MONTH_LENGTHS[month];	
		
		if (month == 1 && E19.isLeap(year)) 
		{
			monthLen++;
		}
	}
	
	private void reset()
	{
		year = 1900;
		month = 0;
		dayOfWeek = 0;
		
		updateMonthLength();
	}

	private int year, month, dayOfWeek, monthLen;
}

