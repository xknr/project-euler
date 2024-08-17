package xknr.euler.e090;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import xknr.euler.Solution;
import xknr.euler.prime.Primes3;


public class E95 extends Solution
{
	public E95(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{
		println("primes ");
		
		generatePrimes();		
		
		println("calculate sums "  + elapsed());
		
		calculateProperDivisorSums();	
		
		println("search " + elapsed());
		
		int searchMethod = 1;

		int result;		
		if (searchMethod == 1)
		{
			result = search1();
		}
		else if (searchMethod == 2)
		{
			result = search2();
		}
		else 
		{
			throw new RuntimeException();
		}
		
		println("found "+ elapsed());
		
		return result;
	}

	private void calculateProperDivisorSums()
	{
		for(int i = 1; i < LIM; i++) 
		{
			for(int j = i; j < LIM; j += i) 
			{
				properDivisorSums[j] += i;
			}
			
			properDivisorSums[i] -= i;
		}

		boolean printDetail = false;
		
		if (printDetail) 
		{			
			for(int i = 1; i < LIM; i++) 
			{
				format("%d -> %d\n", i, properDivisorSums[i]);
			}
		}
		
	}
	
	private void calculateProperDivisorSums2() 
	{
		for(int n = 1; n < LIM; n++) {
			properDivisorSums[n] = sumOfProperDivisors(sieve, primes, n);
		}
	}
	
	private void generatePrimes()
	{
		int maxPrime = LIM - 1;
		
		sieve = Primes3.makeSieve(maxPrime);
		primes = Primes3.collect(sieve, maxPrime, null);		
	}

	public double elapsed()
	{
		return (System.nanoTime() - startTime) / 1e9;
	}

	private int search1()
	{
		Group[] groups = new Group[LIM];

		int longestChainLength = 1;
		int longestChainSmallest = 0;

		List<Integer> traversed = new ArrayList<Integer>();
		
		for(int x = 1; x < LIM; x++) 
		{
			traversed.clear();
			Group group = new Group();
			int n = x;
			
			while(groups[n] == null) 
			{
				groups[n] = group;
				
				int next = properDivisorSums[n];
				
				if (next == n || next >= LIM) 
				{
					group.len = 1;
					break;
				}
				
				traversed.add(n);
				
				n = next;
			}
			
			if (groups[n] == group && group.len == 0) 
			{				
				if (traversed.size() >= longestChainLength) 
				{
					int start = traversed.indexOf(n);
					
					group.len = traversed.size() - start;
					
					if (group.len > longestChainLength) 
					{						
						longestChainLength = group.len;
						
						longestChainSmallest = Integer.MAX_VALUE;
						
						for(int j = start; j < traversed.size(); j++) 
						{							
							longestChainSmallest = Math.min(traversed.get(j), longestChainSmallest);
						}
					}
				}
			}
		}
		
		return longestChainSmallest;		
	}

	private int search2()
	{
		int [] chainLength = new int[LIM];

		for(int n = 1; n < LIM; n++) 
		{
			int next = properDivisorSums[n];
			if (next <= 1) chainLength[n] = 1;
			else if (next == n) chainLength[n] = 1;
			else if (next > LIM) chainLength[n] = 1;
		}
		
		
		int best_len = 1;		
		int best_smallest = 0;
		for(int n = 1; n < LIM; n++) 
		{			
			if (chainLength[n] == 1)
			{
				continue;
			}
			
			List<Integer> seenList = new ArrayList<Integer>();
			Set<Integer> seen = new HashSet<Integer>();			
			
			int len = analyze(chainLength, seen, seenList, n);
			
			if (len > 1 && len > best_len) 
			{
				best_len = len;				
				
				int smallest = LIM;
				
				for(int i = seenList.size() - len; i < seenList.size(); i++) 
				{
					smallest = Math.min(smallest, seenList.get(i));
				}
				
				best_smallest = smallest;
			}
			
//			if (len != 1) 
//			{
//				System.out.println(n + " " + len + " " + seen);
//			}
		}
		return best_smallest;
	}

	
	private int analyze(int[] chainLength, Set<Integer> seen, List<Integer> seenList, int n) 
	{
		if (chainLength[n] != 0) 
		{
			return chainLength[n];
		}
		
		if (seen.contains(n)) 
		{
			int index = seenList.indexOf(n);
			int result = seenList.size() - index;
			chainLength[n] = result;
		}
		
		seenList.add(n);
		seen.add(n);
		
		int next = properDivisorSums[n];
		
		if (next == n) return 1;
		if (next >= LIM) return 1;
		if (next == 1) return 1;
		
		int result = analyze(chainLength, seen, seenList, next);
		
		chainLength[n] = result;
		
		return result;		
	}

	private int sumOfProperDivisors(long[] sieve, List<Integer> primes, int n)
	{
		Map<Integer, Integer> counts = primeFactorCounts(sieve, primes, n);
		
		if (counts.size() == 0) return 1;
		
		//System.out.println(n + " "+ counts);
		
		List<Integer> distinctPrimeFactors = new ArrayList<Integer>();
		
		distinctPrimeFactors.addAll(counts.keySet());
		
		int[] sumDivisors = new int[1];
		
		rec(sumDivisors, 1, counts, distinctPrimeFactors, 0);
		
		sumDivisors[0] -= n;
		
		return sumDivisors[0];
	}

	private void rec(int[] sumDivisors, 
		int currentProduct, 
		Map<Integer, Integer> counts, 
		List<Integer> distinctPrimeFactors, 
		int primeIndex) 
	{
		
		if (primeIndex == distinctPrimeFactors.size()) 
		{
			sumDivisors[0] += currentProduct;
			return;
		}
		
		int p = distinctPrimeFactors.get(primeIndex);
		
		int pCount = counts.get(p); 
		
		for(int i = 0; i <= pCount; i++) 
		{
			rec(sumDivisors, currentProduct, counts, distinctPrimeFactors, primeIndex + 1);
			
			currentProduct *= p;
		}		
	}

	private Map<Integer, Integer> primeFactorCounts(long[] sieve, List<Integer> primes, int n)
	{		
		Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
		
		if (Primes3.isPrime(sieve, n)) 
		{
			return counts;
		}
		
		int x = n;
		int pi = 0;
		int p = primes.get(pi);
		
		while(x > 1) 
		{
			if (x % p == 0) 
			{
				x /= p;
				
				//TODO keep an integer counter and update map only when p changes
				Integer count = counts.get(p);
				counts.put(p, count == null ? 1 : count + 1);
			}
			else 
			{
				p = primes.get(++pi);
				int d = x / p;
				if (d < p)
				{
					p = x;
				}
			}
		}
		
		return counts;		
	}
	

	public static class Group {
		int len;
	}		

	private int[] properDivisorSums = new int[LIM];
	private static final int LIM = 1000_000;
	private List<Integer> primes;
	
	private long[] sieve;

	private long startTime = System.nanoTime();
}
