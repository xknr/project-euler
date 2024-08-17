package xknr.euler.e090;

import static java.lang.Math.log;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import xknr.euler.Constants;
import xknr.euler.Solution;
import xknr.euler.util.Util;


public class E99 extends Solution
{
	public E99(boolean doPrint) {
		super(doPrint);
	}

	public long solve() throws IOException
	{
		List<String> lines = Util.readAllLines(filename);
		
		return findBestLine(lines);
	}

	private long findBestLine(List<String> lines)
	{		
		int bestLine = -1;
		int bestBase = 0, bestExp = 0;
		
		for(int lineNum = 0; lineNum < lines.size(); lineNum++)
		{
			String line = lines.get(lineNum);
			
			// Parse line.
			String[] parts = line.split(",");			
			
			assert parts.length == 2;			
			
			int base = Integer.parseInt(parts[0]);
			int exp = Integer.parseInt(parts[1]);

			boolean better = true;
			
			if (bestLine != -1) 
			{
				format("%d ^ %d VS %d ^ %d\n", bestBase, bestExp, base, exp);
			
				better = greater(base, exp, bestBase, bestExp);
			}
			
			if (better) 
			{
				bestLine = lineNum;
				bestBase = base;
				bestExp = exp;
			}
		}
		
		//System.out.println(B + " " + E + " " + best_line_no);
	
		return bestLine + 1;
		
	}

	public static boolean greater(int base, int exp, int bestBase, int bestExp) 
	{
		// log (b ^ e) = e * logb
		// e * log b > E * log B
		// e > E * log B / log b
		
		double t = log(bestBase) / log(base);
		
		return exp > t * bestExp;
	}

	private static final String filename = Paths.get(Constants.DATA_DIR, "p099_base_exp.txt").toString();

}


