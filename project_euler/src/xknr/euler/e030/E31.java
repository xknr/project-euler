package xknr.euler.e030;

import xknr.euler.Solution;

public class E31 extends Solution
{	
	public E31(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{
		return numberOfCombinations(200, 0);
	}

	/**
	 * In how many ways can "amount" be formed using coins less than "highestIndex"
	 * @param amount The exact amount to form.
	 * @param highestIndex Only coin values with higher or equal index can be used
	 * @return
	 */
	public static int numberOfCombinations(int amount, int highestIndex)
	{
		assert amount >= 0;

		// This will be checked by higher levels of recursion when an exact amount reached.
		if (amount == 0) 
		{
			return 1;
		}

		// There is only one way to form a sum of 1.  
		if (amount == 1)
		{
			return 1;			
		}

		// Cannot use a larger coin than target amount:
		while(coins[highestIndex] > amount)
		{
			highestIndex++;
		}
		
		// There is only one way to form a sum using coins of size 1:
		if (highestIndex == coins.length - 1)
		{
			return 1;
		}
		
		int result = 0;
		for(int i = highestIndex; i < coins.length; i++) 
		{
			// Use a coin, decrease the amount, 
			// add to sum the number of ways to form the amount left. 
			result += numberOfCombinations(amount - coins[i], i);
		}

		return result;
	}
	
	private static final int [] coins = {200, 100, 50, 20, 10, 5, 2, 1};
}


