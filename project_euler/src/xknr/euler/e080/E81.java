package xknr.euler.e080;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import xknr.euler.Constants;
import xknr.euler.Solution;

public class E81 extends Solution
{
	public E81(boolean doPrint) {
		super(doPrint);
	}

	public long solve() 
	{
		int[][] m = readInput(INPUT_FILENAME);
		
		findMinPathSums(m);

		return m[W-1][W-1];
	}

	public static void findMinPathSums(int[][] m)
	{		
		for(int k = 0; k < W; k++) 
		{
			for(int i = 0; i < k; i++) 
			{
				findMinPathSumCell(m, i, k);
			}
			
			for(int i = 0; i < k; i++) 
			{
				findMinPathSumCell(m, k, i);
			}
			
			findMinPathSumCell(m, k, k);
		}
	}

	public static void findMinPathSumCell(int[][] m, int x, int y) 
	{		
		// Take the smaller of of left and above. 
		int nx = x > 0 ? m[y][x-1] : Integer.MAX_VALUE;
		int ny = y > 0 ? m[y-1][x] : Integer.MAX_VALUE;
		int n = Math.min(nx, ny);
		
		// Add to yourself.
		if (n < Integer.MAX_VALUE) 
		{
			m[y][x] += n;
		}		
	}

	public static int[][] readInput(String inFilename)
	{		
		List<String> lines = null;
		
		try {
			lines = Files.readAllLines(Paths.get(inFilename));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		int[][] m = new int[W][W];
		
		assert lines.size() == W;
		
		for(int j = 0; j < W; j++) 
		{
			String line = lines.get(j);
			String[] parts = line.split(",");
			
			for(int i = 0; i < W; i++) 
			{
				m[j][i] = Integer.parseInt(parts[i]);
			}			
		}
		
		return m;
	}

	private static final int W = 80;
	private static final String INPUT_FILENAME = Paths.get(Constants.DATA_DIR, "p081_matrix.txt").toString();
}

