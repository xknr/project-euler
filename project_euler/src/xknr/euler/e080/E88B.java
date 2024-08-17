package xknr.euler.e080;

import java.util.HashSet;
import java.util.Set;

import xknr.euler.Solution;

public class E88B extends Solution
{
	public E88B(boolean doPrint) {
		super(doPrint);
	}

	private static final int LIMIT = 12000;
	private int indices[] = new int[14];
	private int nvalues[] = new int[LIMIT + 1];
	
	public long solve()
	{	
		iterateOverTerm(0, 0, 1);
				
		Set<Integer> set = new HashSet<Integer>();
		
		for(int n: nvalues) 
		{
			set.add(n);
		}
		
		int result = 0;
		
		for(int n: set) 
		{
			result += n;
		}

		println("result = ", result);

		return result;
	}

	private void iterateOverTerm(int depth, int sum, int pro)
	{
		if (depth > 14) 
		{
			return;
		}
		
		if (depth > 1) 
		{			
			int k = pro - sum + depth;
			
			if (k < nvalues.length)
			{
				if (nvalues[k] == 0 || pro < nvalues[k]) 
				{
					printState(depth, sum, pro, k);
					nvalues[k] = pro;
				}
			}			
		}

		if (depth < 14)
		{
			for(indices[depth] = depth == 0 ? 2 : indices[depth - 1]; 
				indices[depth] <= LIMIT * 2; 
				indices[depth]++)
			{				
				int ind = indices[depth];
				
				int pro2 = pro * ind;
				
				if (pro2 > LIMIT * 2) 
				{
					break;
				}
				
				int sum2 = sum + ind;
				
				if (sum2 > pro2) 
				{
					break;
				}
				
				iterateOverTerm(depth + 1, sum2, pro2);
			}			
		}		
	}

	private void printState(int depth, int sum, int pro, int k) 
	{
		if (!doPrint())
		{
			return;
		}
	
		System.out.print(indices[0]);
		
		for(int i = 1; i < depth; i++) 
		{
			System.out.print(" x " + indices[i]);
		}
		
		System.out.println("--->  " + sum + ", "+  pro + " ---> " + k);
	}
}

