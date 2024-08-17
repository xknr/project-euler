package xknr.euler.e040;

import java.io.IOException;
import java.nio.file.Paths;

import xknr.euler.Constants;
import xknr.euler.Solution;
import xknr.euler.e020.E22;

public class E42 extends Solution
{
	public E42(boolean doPrint) {
		super(doPrint);
	}

	public long solve() throws IOException
	{		
		String[] words = E22.loadNames(FILENAME);
		
		int count = 0;

		// Add up every word's score if it's a triangle number.
		for(String word: words)
		{			
			int score = calcWordScore(word);
			
			if (isTriangleSimple(score)) 
			{
				count++;
			}
		}
		
		return count;		
	}

	public static int calcWordScore(String word)
	{
		int score = 0;
		
		for(char c: word.toCharArray()) 
		{
			// Words given will be in double quotes. 
			if (c == '"')
			{
				continue;
			}
			
			assert c >= 'A' && c <= 'Z';

			// Get letter score.
			int d = c - 'A' + 1;
			assert d >= 01 && d <= 26;

			// Add letter score to word score.
			score += d;
		}
		
		return score;
	}

	public static boolean isTriangleSimple(long tri)
	{
		assert tri > 0;
		
		// t = n * (n + 1) / 2		
		// t * 2 = n * (n + 1)		
		// n <= sqrt(t * 2) < n + 1  
		double root = Math.sqrt(tri * 2L);
		
		// This should give n.
		long n = (long)Math.floor(root);
		
		// Test whether this is can produce the original number.
		return n * (n + 1L) / 2L == tri;
	}

	private static final String FILENAME = Paths.get(Constants.DATA_DIR, "p042_words.txt").toString();
}

