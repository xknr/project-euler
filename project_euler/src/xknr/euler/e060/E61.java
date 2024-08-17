package xknr.euler.e060;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.IntToLongFunction;

import xknr.euler.Solution;
import xknr.euler.util.Util;

public class E61 extends Solution
{	
	public E61(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{	
		maps = generateMaps();

		triangular = maps[0];

		List<FamilyMap> unused = initialUnused();
		
		// selected number for permutation at each level of recursion
		int[] selected = new int[generators.length];

		for(List<Integer> li: triangular.values()) 
		{
			for(int t: li) 
			{
				selected[0] = t;
				
				int result = permutation(unused, suffix(t), selected, 1);
				
				if (result != 0) 
				{
					return result;
				}
			}
		}
		
		throw new AssertionError();
	}

	/**
	 * Generate all numbers in range for all polygonal families and put them in FamilyMap structure. 
	 * @return array of generated FamilyMap objects
	 */
	private FamilyMap[] generateMaps()
	{		
		FamilyMap[] maps = new FamilyMap[generators.length];
		
		for(int i = 0; i < maps.length; i++)
		{
			FamilyMap map = new FamilyMap(); 
			generate(map, generators[i]);
			maps[i] = map;
		}

		return maps;
	}

	/*
	 * A list containing maps for all polygonal number families except triangular.
	 */
	public List<FamilyMap> initialUnused()
	{		
		List<FamilyMap> unused = new ArrayList<FamilyMap>();
		
		for(FamilyMap p: maps) 
		{
			// skip triangular
			if (p != triangular) 
			{
				unused.add(p);
			}
		}
		
		return unused;
	}
	
	/**
	 * Tries to add an element to an incomplete permutation by selecting from 
	 * all possible polygonal numbers with given prefix.
	 * @param unusedFamilies Each polygonal number family can be used once. These are the ones that have not yet been used.
	 * @param prefix The suffix of previously selected number. Permutation should continue with this prefix. If -1, any number can follow.
	 * @param selected Numbers selected for permutation so far.
	 * @param depth Recursion depth. Also the count of numbers that have been selected for permutation so far.
	 * @return
	 */
	private int permutation(List<FamilyMap> unusedFamilies, int prefix, int[] selected, int depth)
	{
		for(FamilyMap p: unusedFamilies) 
		{
			List<Integer> li = p.get(prefix);
			if (li == null) 
			{
				continue;
			}
			
			for(int t: li) 
			{
				selected[depth] = t;
				if ( 1 == unusedFamilies.size() ) 
				{
					if ( prefix(selected[0]) == suffix(t) ) 
					{
						return Util.sumArr(selected);
						//print(selected);
					}
				} 
				else 
				{
					int result = permutation(Util.removeOneElem(unusedFamilies, p), suffix(t), selected, depth + 1);
					
					if (result != 0) 
					{
						return result;
					}
				}
			}
		}
		
		return 0;
		
	}

	/**
	 * Generate all 4 digit polygonal numbers with given generator function in range and put them in 
	 * the FamilyMap object. 
	 * @param map The FamilyMap object
	 * @param generator Method that generates a polygonal number given n.
	 */
	public void generate(FamilyMap map, IntToLongFunction generator)
	{
		for(int n = 0; ; n++) 
		{
			int x = (int)generator.applyAsLong(n);
			
			if (x >= 10000) 
			{
				break;
			}
			else if (x >= 1000) 
			{
				map.addElement(x);				
			}
		}
	}


	/**
	 * Return first 2 digits of a 4 digit integer.
	 * @param t The 4 digit integer.
	 * @return An integer that is comprised of the first 2 digits of t.
	 */
	public static int prefix(int t)
	{
		assert t >= 1000 && t < 10000;
		
		return t / 100;
	}

	/**
	 * Return last 2 digits of a 4 digit integer.
	 * @param t The 4 digit integer.
	 * @return An integer that is comprised of the last 2 digits of t.
	 */
	public static int suffix(int t)
	{
		assert t >= 1000 && t < 10000;
		
		return t % 100;
	}

	/**
	 * Organizes all 4 digit elements that can be generated in one polygonal type.
	 * This is basically a dictionary which maps each possible 2 digit prefix to a list of 
	 * polygonal numbers starting with that prefix.
	 */
	private static class FamilyMap extends HashMap<Integer, List<Integer>>
	{
		private static final long serialVersionUID = 1867972607689808506L;

		public void addElement(int x)
		{
			// Get the list of numbers starting with prefix for x.
			List<Integer> list = this.get( prefix(x) );
			
			// Create list if not created yet.
			if (list == null) 
			{
				list = new ArrayList<Integer>();
				this.put( prefix(x) , list);
			}
			
			list.add(x);
		}	
	}
	
	/**
	 * Array of lambda functions which calculate a polygonal number type given n. 
	 */
	public static IntToLongFunction[] generators = { 
		(n) -> Util.triangular(n),
		(n) -> Util.square(n),
		(n) -> Util.pentagonal(n),
		(n) -> Util.hexagonal(n),
		(n) -> Util.heptagonal(n),
		(n) -> Util.octagonal(n),
	};

	private FamilyMap[] maps;
	private FamilyMap triangular; // reference to the first element of maps
}


