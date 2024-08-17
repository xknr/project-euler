package xknr.euler.e050;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import xknr.euler.Constants;
import xknr.euler.Solution;

public class E59 extends Solution
{	
	public E59(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{			
		String content = null;
		
		try {
			content = new String(Files.readAllBytes(Paths.get(FILENAME)));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		// Remove endline related characters.
		content = content.replace("\n", "");
		content = content.replace("\r", "");
		
		String[] parts = content.split(",");
		int[] enc = new int[parts.length];
		for(int i = 0; i < parts.length; i++) {
			enc[i] = Integer.parseInt(parts[i]);
		}

		boolean doPrint = doPrint();
				
		// 3 letter permutations of a-z
		int[] c = new int[3];
		final int numTokens = 'z' - 'a' + 1;
		for(int code = numTokens * numTokens * numTokens - 1; code >= 0; code--) 
		{	
			c[0] = 'a' + code / (numTokens * numTokens);
			c[1] = 'a' + (code / numTokens) % numTokens;
			c[2] = 'a' + code % numTokens;
			
			boolean found = true;
			
			StringBuilder sb = doPrint ? new StringBuilder() : null;
			
			int sum = 0;
	
			for(int i = 0; i < enc.length; i++) 
			{
				int dec = enc[i] ^ c[i % 3];
				
				// After trial and error, found these criteria which 
				// leaves out a single solution.
				if (dec < 32 || dec > 122 || '`' == dec) 
				{
					found = false;
					break;
				}
				
				sum += dec;
				if (doPrint) sb.append((char)dec);
			}
			if (found) {
				if (doPrint) println(sb.toString());
				return sum;
			}
		}
		
		throw new RuntimeException();
	}
	
	private static final String FILENAME = Paths.get(Constants.DATA_DIR, "p059_cipher.txt").toString();
}

