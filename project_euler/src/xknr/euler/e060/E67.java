package xknr.euler.e060;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import xknr.euler.Constants;
import xknr.euler.Solution;
import xknr.euler.e010.E18B;
import xknr.euler.util.Util;


public class E67 extends Solution
{
	public E67(boolean doPrint) {
		super(doPrint);
	}

	public long solve() throws IOException 
	{
		String [] lines = Util.toArrayS(Files.readAllLines(Paths.get(FILENAME)));
		
		E18B solver = new E18B(doPrint());
		return solver.solve(lines);
	}
	
	private static final String FILENAME = Paths.get(Constants.DATA_DIR, "p067_triangle.txt").toString();
}
