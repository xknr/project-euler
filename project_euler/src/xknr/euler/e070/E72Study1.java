package xknr.euler.e070;

import java.util.HashSet;

import xknr.euler.Solution;
import xknr.euler.util.Util;

public class E72Study1 extends Solution 
{
	public E72Study1(boolean doPrint) {
		super(doPrint);
	}

	public long solve(int N)
	{
		HashSet<Integer>[] sets = allocSets(N);
		
		long counter = Util.triangular(N - 1); 
		
		for(int y = 1; y <= N; y++) 
		{			
			println("y=" + y);
			
			for(int x = y + 1; x <= N; x++)
			{
				if (!sets[y].contains(x)) 
				{
					println("got "+ y + "/" + x + " ");
					
					for(int a = 2; ; a++) 
					{
						int xa = x * a;
						if (xa > N) 
							break;
						int ya = y * a;
						sets[ya].add(xa);
						println("marking " + ya + "/" + xa + " "+ N);
						counter--;
					}
				}
			}
		}
		
		println(counter);
		
		return counter;
	}

	private static HashSet<Integer>[] allocSets(int N) 
	{
		@SuppressWarnings("unchecked")
		HashSet<Integer>[] sets = (HashSet<Integer>[])new HashSet[N + 1];
		for(int i = 0; i < sets.length; i++) {
			sets[i] = new HashSet<Integer>();
		}
		return sets;
	}
}
