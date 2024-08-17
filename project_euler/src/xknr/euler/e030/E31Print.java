package xknr.euler.e030;

public class E31Print
{

	public long solve()
	{
		count = 0;
		int[] counts = new int[coins.length];
		return numberOfCombinations2(200, 0, counts);
	}
	private static final int [] coins = {200, 100, 50, 20, 10, 5, 2, 1};
	
	private static int count = 0;
	
	// Printing version.
	public static int numberOfCombinations2(int amount, int highestIndex, int[] str)
	{
		assert amount >= 0;

		if (amount == 0) 
		{
			System.out.println(
//					count + ": " + 
					toStr(str));
			count++;
			return 1;
		}

		// There is only one way to form a sum of 1.  
		if (amount == 1)
		{
			str[coins.length - 1]++;
			System.out.println(
//						count + ": " + 
						toStr(str));
			str[coins.length - 1]--;
			count++;
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
			str[coins.length - 1]+=amount;
			System.out.println(
					//count + ": " + 
					toStr(str));
			str[coins.length - 1]-=amount;
			count++;
			return 1;
		}
		
		int result = 0;
		for(int i = highestIndex; i < coins.length; i++)
		{
			// Use a coin, decrease the amount, 
			// add to sum the number of ways to form the amount left. 
			str[i]++;
			result += numberOfCombinations2(amount - coins[i], i, str);
			str[i]--;
		}

		return result;
	}

	public static String toStr(int[] str)
	{
		StringBuilder sb = new StringBuilder();
		int printCount = 0;
		
		for(int i = 0; i < str.length; i++) 
		{
			if (str[i] == 0)
			{
				continue;
			}
			
			if (printCount > 0)
			{
				sb.append(" + ");
			}
			
			if (str[i] > 1) 
			{
				sb.append(str[i]);
				sb.append("x");
			}
			
			sb.append(coins[i]);
			printCount++;
		}
		
		return sb.toString();
	} 

}

