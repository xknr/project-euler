package xknr.euler.e020;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import xknr.euler.Constants;
import xknr.euler.Solution;
import xknr.euler.util.Util;

public class E22 extends Solution
{
	public E22(boolean doPrint) {
		super(doPrint);
	}

	public long solve() throws IOException
	{
		String[] words = loadNames(FILENAME);
		
		Arrays.sort(words);
		
		long sum = 0;
		
		for(int wInd = 0; wInd < words.length; wInd++)
		{
			String word = words[wInd];
			int score = wordScore(word);
			sum += score * (wInd + 1);
		}
		
		return sum;
	}

	/**
	 * 
	 * @param word The input word. Must begin and end with double quotes. 
	 * @return
	 */
	public static int wordScore(String word)
	{
		assert word.charAt(0) == DOUBLE_QUOTE;
		assert word.charAt(word.length() - 1) == DOUBLE_QUOTE;
		
		// Ignore first and last character.
		int begin = 1, end = word.length() - 1;
		
		return wordScore(word, begin, end);
	}

	public static int wordScore(String word, int begin, int end)
	{
		int score = 0;

		for(int i = begin; i < end; i++) 
		{
			char c = word.charAt(i);
			assert isUpper(c);
			
			int charNum = c - 'A' + 1; 
			score += charNum;
		}
		
		return score;
	}
	
	public static boolean isUpper(char c) 
	{
		return c >= 'A' && c <= 'Z';
	}

	public static String[] loadNames(String fn) throws IOException
	{
		// Read lines in the file as strings.
		List<String> lines = Files.readAllLines(Paths.get(fn));
		
		// Entire file must be composed of a single line
		assert lines.size() == 1;
		
		// Get the first and only line.
		String data = lines.get(0);
		
		// Split words by commas
		String[] words = data.split(",");
		
		return words;
	}

	private static final char DOUBLE_QUOTE = '"';
	private static final String FILENAME = Paths.get(Constants.DATA_DIR, "p022_names.txt").toString();
}

