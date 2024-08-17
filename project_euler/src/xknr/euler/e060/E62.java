package xknr.euler.e060;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xknr.euler.Solution;

public class E62 extends Solution
{	
	public E62(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{			
		Map<String, List<Long>> map = new HashMap<String, List<Long>>();
		
		for(long n = 1; n <= LIMIT; n++) 
		{
			long c = n * n * n;
			
			String hash = hash(c);
			
			List<Long> li = map.get(hash);
			
			if (li == null) 
			{
				li = new ArrayList<Long>();
				map.put(hash, li);
			}
			
			li.add(c);
			
			if (li.size() == 5) 
			{
				println(c + " "+ hash + " "+ li.size());
				
				return li.get(0);
			}			
		}
		
		throw new RuntimeException("we shouldn't have reached here");
	}

	public static String hash(long c)
	{
		String s = Long.toString(c);
		char[] cs = s.toCharArray();
		Arrays.sort(cs);
		return new String(cs);
	}
	
	private static final int LIMIT = 10000;
}


