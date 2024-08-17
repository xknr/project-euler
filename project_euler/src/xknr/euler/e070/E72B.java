package xknr.euler.e070;

import xknr.euler.Solution;

public class E72B extends Solution
{
	public E72B(boolean doPrint) {
		super(doPrint);
	}

	public long solve(int N)
	{
		int[] s = new int[N + 1];

		for (int i = 0; i < s.length; i++) 
		{
			s[i] = i;
		}

		long sum = 0;

		for (int i = 2; i <= N; i++) 
		{
			if (s[i] == i)
			{
				for (int j = i; j <= N; j += i) 
				{
					s[j] = s[j] / i * (i - 1);
				}
			}
			
			sum += s[i];
		}

		return sum;
	}
}
