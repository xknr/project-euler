package xknr.euler.e080;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xknr.euler.Constants;
import xknr.euler.Solution;

public class E89 extends Solution
{	
	public E89(boolean doPrint) {
		super(doPrint);
	}

	public long solve() throws IOException
	{
		List<String> lines = Files.readAllLines(Paths.get(filename));
		
		int saved = 0;
		
		for(String line: lines) 
		{
			int value = romanToValue(line);
			String roman = valueToRoman(value);
			println(line + "\t    " + value + "\t    " + roman);
			saved += line.length() - roman.length();
		}
		
		return saved;
	}

	public static String valueToRoman(int value) 
	{
		StringBuilder sb = new StringBuilder();

		// Consume all 1000's in a greedy way:
		while(value >= 1000) 
		{
			sb.append('M');
			value -= 1000; 
		}
		
		// For the rest, consider each digit separately: 
		sb.append(convertDigit(value / 100, "C", "D", "M")); value %= 100;
		sb.append(convertDigit(value / 10, "X", "L", "C")); value %= 10;
		sb.append(convertDigit(value, "I", "V", "X"));
		
		return sb.toString();		
	}
	
	private static final String[] templates = {
		"", "a", "aa", "aaa", "ab", 
		"b", "ba", "baa", "baaa", "ac"
	};

	public static String convertDigit(int digit, 
		String s1, 
		String s5, 
		String s10)
	{
		return templates[digit]
			.replace("a", s1)
			.replace("b", s5)
			.replace("c", s10);
	}

	public static int romanToValue(String line) 
	{
		int sum = 0;
		int len = line.length();
		
		for(int i = 0; i < len; i++) 
		{			
			// Read current character:
			int v = values.get(line.charAt(i));
			
			// Read next character if it exists:
			int nv = i == len - 1 ? 0 : values.get(line.charAt(i + 1));
			
			// Is this is a subtractive combination together with next char?
			boolean isSub = false;
			isSub |= v == 1 && nv == 5;
			isSub |= v == 1 && nv == 10;
			isSub |= v == 10 && nv == 50;
			isSub |= v == 10 && nv == 100;
			isSub |= v == 100 && nv == 500;
			isSub |= v == 100 && nv == 1000;
			
			// If subtractive combination:
			if (isSub) 
			{
				v = nv - v;
				i++; // Consume the next character.
			}
			
			sum += v;
		}
		
		return sum;
	}

	private static final String filename = 
		Paths.get(Constants.DATA_DIR, "p089_roman.txt").toString();
	
	private static Map<Character, Integer> values = 
		new HashMap<Character, Integer>();
	
	static {
		values.put('M', 1000);
		values.put('D', 500);
		values.put('C', 100);
		values.put('L', 50);
		values.put('X', 10);
		values.put('V', 5);
		values.put('I', 1);
	}

}

