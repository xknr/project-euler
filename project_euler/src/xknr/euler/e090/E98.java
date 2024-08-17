package xknr.euler.e090;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import xknr.euler.Constants;
import xknr.euler.Solution;

public class E98 extends Solution
{	
	public static final int MAX_LEN = 9;

	public E98(boolean doPrint) {
		super(doPrint);
	}

	public long solve() throws IOException
	{
		String srcData = readInput();
		
		String[] words = parseWords(srcData);
		
		Map<String, List<String>> anagramGroups = findAnagrams(words);
		
		List<String[]> pairs = findAnagramPairs(anagramGroups);

		Map<Integer, List<Integer>> squares = addSquares(pairs);
		
		return findBest(pairs, squares);
	}


	private int findBest(List<String[]> pairs, Map<Integer, List<Integer>> squares) 
	{
		int best = 0;
		
		for(String[] pair: pairs) 
		{			
			println(pair[0], pair[1]);
	
			int len = pair[0].length();
			
			List<Integer> numbers = squares.get(len);
			
			for(int n1: numbers) 
			{				
				Map<Character, Character> map = makeMap(pair[0], n1);
				
				if (map == null) 
				{
					continue;
				}

				String strN2 = makeNumber2(map, len, pair[1]);
				
				int n2 = Integer.parseInt(strN2);
				
				strN2 = Integer.toString(n2);
				
				if (strN2.length() != len)
				{
					continue;				
				}
				
				if (!numbers.contains(n2))
				{
					continue;
				}
				
				println(pair[0], pair[1], len,  pair[0].length(), n1, n2);
				
				best = Math.max(best, n1);
				best = Math.max(best, n2);
			}
			
		}
		return best;
	}

	private String makeNumber2(Map<Character, Character> map, int len, String pair1)
	{
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < len; i++) 
		{
			char p = pair1.charAt(i);
			char c = map.get(p);
			sb.append(c);
		}
		
		String sn2 = sb.toString();
		
		return sn2;
	}

	private Map<Character, Character> makeMap(String pair0, int n)
	{
		String sn = Integer.toString(n);
		
		Map<Character, Character> map = new HashMap<Character, Character>();

		boolean [] digitUsed = new boolean[10];
		
		for(int i = 0; i < pair0.length(); i++)
		{
			char digit = sn.charAt(i);
			char letter = pair0.charAt(i);

			// Was a letter assigned to this digit before?
			if (map.containsKey(letter))
			{
				// If assigned, it should be the same digit.
				char existing = map.get(letter);
				
				if (existing != digit) 
				{
					return null;
				}
			} 
			else 
			{						
				if (digitUsed[digit-'0']) 
				{
					// We cannot use the same digit for another letter.
					return null;
				}
				
				// Add record.
				digitUsed[digit-'0'] = true;
				map.put(letter, digit);
			}
		}
		
		return map;		
	}

	private Map<Integer, List<Integer>> addSquares(List<String[]> pairs) 
	{
		boolean[] lengthExists = detectLengths(pairs);
		
		int maxLen = findMaxLen(lengthExists);
		
		Map<Integer, List<Integer>> squares = new HashMap<Integer, List<Integer>>();
		
		for(int i = 1; ; i++)
		{
			final int iSq = i * i;
			
			int numDigits = Integer.toString(iSq).length();
			
			if (numDigits > maxLen) 
			{ 
				break;
			}
			
			if (lengthExists[numDigits]) 
			{
				if(!squares.containsKey(numDigits)) 
				{
					squares.put(numDigits, new ArrayList<Integer>());
				}
				squares.get(numDigits).add(iSq);
			}
		}
		return squares;
	}

	private int findMaxLen(boolean[] seenLengths) 
	{
		int maxLen = 0;
		
		for(int len = 0; len < seenLengths.length; len++)
		{
			if (seenLengths[len])
			{
				maxLen = Math.max(maxLen, len);
			}
		}
		
		return maxLen;
	}

	private boolean[] detectLengths(List<String[]> pairs) 
	{
		boolean[] seenLengths = new boolean[MAX_LEN + 1];
		
		for(String[] pair: pairs) 
		{
			int len = pair[0].length();
			
			seenLengths[len] = true;
		}
		
		return seenLengths;
	}

	private List<Character> getDistinctChars(String[] pair) 
	{
		Set<Character> distinct = new HashSet<Character>();
		
		for(char c: pair[0].toCharArray()) 
		{
			distinct.add(c);
		}
		
		List<Character> result = new ArrayList<Character>();
		
		result.addAll(distinct);
		
		return result;
	}

	private List<String[]> findAnagramPairs(Map<String, List<String>> anagramGroups)
	{
		List<String[]> pairs = new ArrayList<String[]>();

		for(List<String> li: anagramGroups.values()) 
		{
			if (li.size() == 1) 
			{
				continue;
			}
			else if (li.size() == 2) 
			{
				pairs.add(makePair(li, 0, 1));
			}
			else if (li.size() == 3) 
			{
				pairs.add(makePair(li, 0, 1));
				pairs.add(makePair(li, 1, 2));
				pairs.add(makePair(li, 2, 0));
			}
		}
		
		return pairs;
	}

	private String[] makePair(List<String> li, int ind0, int ind1) 
	{
		return new String[]{li.get(ind0), li.get(ind1)};
	}

	private Map<String, List<String>> findAnagrams(String[] words) 
	{
		Map<String, List<String>> groups = new HashMap<String, List<String>>();
		
		for(String word: words) 
		{
			String key = makeKey(word);
			
			List<String> li = groups.get(key);
			
			if (li == null) 
			{
				li = new ArrayList<String>();
				groups.put(key, li);
			}
			
			li.add(word);			
		}
		
		return groups;
	}

	private String[] parseWords(String srcData) 
	{
		String[] words = srcData.split(",");
		
		for(int i = 0; i < words.length; i++) 
		{
			words[i] = words[i].replace("\"", "").trim();
		}
		
		return words;
	}

	// TODO from util?
	public String readInput() throws IOException 
	{
		return new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
	}
	
	public static String makeKey(String word) 
	{
		char[] arr = word.toCharArray();
		
		Arrays.sort(arr);
		
		return new String(arr);
	}

	private static final String filename = Paths.get(Constants.DATA_DIR, "p098_words.txt").toString();
}

