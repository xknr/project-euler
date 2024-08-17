package xknr.euler.e080;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import xknr.euler.Solution;
import xknr.euler.util.Util;

import java.util.Set;
import java.util.TreeMap;

public class E88 extends Solution
{
	public E88(boolean doPrint) {
		super(doPrint);
	}
	
	public long solve()
	{		
		added = new HashSet<Integer>();
		found = new boolean[LIMIT + 1];

		for(int n = 4; maxConsecutive <= LIMIT; n++)
		{			
			Map<Long, Integer> pf = Util.countDPF((long)n);
			
			if (pf.size() == 1 && pf.get(pf.keySet().iterator().next()) == 1)
			{
				continue;
			}
			
			int sum = 0;
			int pcount = 0;
			for(Entry<Long, Integer> entry: pf.entrySet()) 
			{
				long p = entry.getKey();
				int c = entry.getValue();
				sum += p * c;
				pcount += c;
			}
			
			int kAllSeparate = n - sum + pcount;
			if (kAllSeparate < maxConsecutive)
			{
				continue;
			}
			
			println("n=", n, " ", sum);
			
			evalCombs(pf, new ArrayList<Long>(), 0, n);
		}
		
		println("result=", result);
		
		added = null;
		found = null;
		
		return result;
	}

	private void evalCombs(
			Map<Long, Integer> orig, 
			List<Long> collect, 
			int depth, int n) 
	{		
		if (orig.isEmpty()) 
		{
			if (collect.size() > 1) 
			{
				long sum = sum(collect);
				assert sum <= n;
				
				int k = (int)( n - sum + collect.size() );
				
				if (k < found.length && !found[k]) 
				{
					found[k] = true;
					
					if (!added.contains(n)) 
					{
						added.add(n);
						result += n;
					}
					
					while(maxConsecutive <= LIMIT && found[maxConsecutive])
					{
						maxConsecutive++;
					}
				}
			}			
		} 
		else 
		{			
			TreeMap<Long, Integer> orig2 = new TreeMap<Long, Integer>();
			
			orig2.putAll(orig);
			
			List<List<Long>> subsets = collectSubsets(orig2, 
				new ArrayList<List<Long>>(), 
				new ArrayList<Long>(), 0);
			
			for(List<Long> subset: subsets) 
			{
				Map<Long, Integer> unused = dupAsHashMap(orig);
				
				for(long p: subset) 
				{
					addToCount(unused, p, -1);
				}
				
				collect.add(product(subset));
				evalCombs(unused, collect, depth + 1, n);
				collect.remove(collect.size() - 1);
			}		
		}		
	} 

	private List<List<Long>> collectSubsets(
			TreeMap<Long, Integer> unused, 
			List<List<Long>> collect, 
			List<Long> used, 
			int depth) {

		if (!used.isEmpty())
		{
			collect.add(dupAsArrayList(used));			
		}

		if (!unused.isEmpty())
		{
			TreeMap<Long, Integer> next1 = dupAsTreeMap(unused);
			
			for(long p: unused.keySet()) 
			{
				addToCount(next1, p, -1);
				used.add(p);
				collectSubsets(next1, collect, used, depth+1);
				used.remove( used.size() - 1);
				next1.remove(p); 
			}
		}

		return collect;
	}

	public static Map<Long, Integer> dupAsHashMap(Map<Long, Integer> src) 
	{
		Map<Long, Integer> unused2 = new HashMap<Long, Integer>();
		unused2.putAll(src);
		return unused2;
	}

	public static <K, V> TreeMap<K, V> dupAsTreeMap(Map<K, V> src) 
	{
		TreeMap<K, V> unused2 = new TreeMap<K, V>();
		unused2.putAll(src);
		return unused2;
	}
	
	public static List<Long> dupAsArrayList(List<Long> src) 
	{
		List<Long> copy = new ArrayList<Long>();
		copy.addAll(src);
		return copy;
	}
	
	public static Map<Long, Integer> addToCount(Map<Long, Integer> dic, long key, int amount)
	{
		Integer count = dic.get(key);
		count = count == null ? 0 : count;
		count += amount;
		
		if (count < 0) 
		{
			throw new ArithmeticException(); 
		}
		
		if (count == 0) 
		{
			dic.remove(key);
		} 
		else 
		{
			dic.put(key, count);
		}
		
		return dic;
	}
	
	public static long product(List<Long> used) 
	{
		long product = 1;
		
		for(long p: used) 
		{
			product *= p;
		}
		
		return product;
	}
	
	public static long sum(List<Long> li) 
	{
		long sum = 0;
		
		for(long e: li)
		{
			sum += e;
		}
		
		return sum;
	}

	private long result = 0;
	private boolean[] found;
	private Set<Integer> added;
	private int maxConsecutive = 2;

	private static final int LIMIT = 12000;
}

