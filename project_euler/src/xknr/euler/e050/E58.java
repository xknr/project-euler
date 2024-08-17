package xknr.euler.e050;

import xknr.euler.Solution;
import xknr.euler.miller.MillerRabin3;

public class E58 extends Solution 
{	
	public E58(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{			
		int incr = 1, edge = 1;		
		long count = 1, countPrime = 0;
		
		for(int x = 1; ;)
		{
			x += incr * 2; 
			
			if (MillerRabin3.isPrime(x)) 
			{
				countPrime++;
			}
			
			incr++;
			
			x += incr; 
			
			if (MillerRabin3.isPrime(x))
			{
				countPrime++;
			}
			
			x += incr;
			
			incr++;
			
			if (MillerRabin3.isPrime(x))
			{
				countPrime++;
			}
			
			
			edge += 2;
			
			count += 4;
			
			if (countPrime * 10 < count)
			{
				break;
			}
		}
		
		println(edge, count);

		return edge;
	}	
}

