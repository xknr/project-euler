package xknr.euler.e070;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import xknr.euler.Constants;
import xknr.euler.Solution;

public class E79 extends Solution
{
	
	public E79(boolean doPrint) {
		super(doPrint);
	}

	public long solve() throws IOException 
	{
		List<String> lines = Files.readAllLines(Paths.get(filename));
		
		int[][] compare = new int[10][10];
		
		int[] n = new int[3];
		
		for(String line: lines)
		{
			line = line.trim();
			
			if (line.length() == 0) 
			{
				continue;
			}
			
			if (line.length() != 3)
			{
				throw new AssertionError();
			}
			
			for(int i = 0; i < 3; i++) 
			{
				n[i] = line.charAt(i) - '0';
			}

			println(n[0] + " " + n[1] + " " + n[2]);
			
			compare[n[0]][n[1]] = 1; compare[n[1]][n[0]] = -1;
			compare[n[0]][n[2]] = 1; compare[n[2]][n[0]] = -1;
			compare[n[1]][n[2]] = 1; compare[n[2]][n[1]] = -1;
		}
		
		printState(compare);
		
		int sorted[] = new int[8];

		for(int i = 0 ; i < 10; i++)
		{
			if (i == 4 || i == 5) 
			{
				continue;
			}
			
			int score = 0;
			
			for(int j = 0 ; j < 10; j++)
			{
				if (j == 4 || j == 5)
				{
					continue;
				}
				
				score += (compare[j][i] == 1) ? 1 : 0;
			}
			
			sorted[score] = i;
		}		
		
		return calcResult(sorted);
	}

	public static int calcResult(int[] sorted) 
	{
		int result = 0;
		
		for(int i = 0 ; i < sorted.length; i++)
		{
			result *= 10;
			result += sorted[i];
		}
		
		return result;
	}

	private void printState(int[][] compare)
	{
		if (!doPrint())
			return;
		
		for(int j = 0 ; j < 10; j++)
		{
			if (j == 4 || j == 5) 
			{
				continue;
			}
			
			for(int i = 0 ; i < 10; i++) 
			{
				if (i == 4 || i == 5)
				{
					continue;
				}
				format("%3d ", compare[j][i]);	
			}
			
			println();
		}
	}
	
	private static final String filename = Paths.get(Constants.DATA_DIR, "p079_keylog.txt").toString();
}

