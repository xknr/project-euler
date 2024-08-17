package xknr.euler.e010;

public class E11Read
{
	/**
	 * Read character at given row and column.
	 * @param lines
	 * @param r
	 * @param c
	 * @return
	 */
	public static int readItem(String[] lines, int r, int c)
	{
		String line = lines[r];
		
		int index = c * 3;
		
		int d1 = E11Read.readDigit(line, index++);
		int d0 = E11Read.readDigit(line, index);
		
		return d1 * 10 + d0;
	}

	/**
	 * Read character as digit at given position from a string. 
	 * @param s Source string.
	 * @param ind Digit position.
	 * @return The digit at position.
	 */
	public static int readDigit(String s, int ind) 
	{
		final char c = s.charAt(ind);
		
		final int d = c - '0';		
		
		assert d >= 0 && d <= 9;
		
		return d;
	}
}
