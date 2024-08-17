package xknr.euler.e010;

import xknr.euler.Solution;

/**
 * Uses an array of bytes to keep track of digits.
 */
public class E16B extends Solution
{
	public E16B(boolean doPrint) {
		super(doPrint);
	}

	/*
	 * Use an array of digits to hold large numbers.
	 * Digits are placed in the array in increasing significance.
	 * Multiplication by 2 is done in-place. 
	 */
	public long solve()
	{					
		// Estimate the number of digits required.
		// 10 ^ 1000 = 2 ^ n
		int estimate = (int)Math.ceil(LIM* Math.log(2) / Math.log(10)) + 1;
		
		byte[] x = new byte[estimate];
		
		// Write the number 1 as initial value.
		x[0] = 1;

		int len = 1;
		
		for(int k = 0; k < LIM; k++) 
		{
			printNumber(x, len);
			len = multiplyBy2(x, len);			
		}
		
		// Add digits.
		return digitSum(x, len);
	}
	
	/**
	 * 
	 * @param x Number to be multiplied by 2.
	 * @param len Length of x.
	 * @return Length of the result.
	 */
	public int multiplyBy2(byte[] x, int len)
	{		
		int carry = 0;
		
		for(int j = 0; j < len; j++)
		{			
			// Multiply current digit by two and add carry.
			int p = x[j] * 2 + carry;
			
			// Write the last digit in-place.
			x[j] = (byte)(p % 10);
			
			// Other digit carries over.
			carry = p / 10;
		}
		
		// If there is still carry
		if (carry != 0) 
		{
			// Add it as a new digit.
			x[len++] = (byte)carry;
		}
		
		// Return new length.
		return len;		
	}

	static long digitSum(byte[] x, int len)
	{
		int sum = 0;
		
		for(int i = 0; i < len; i++) 
		{
			sum += x[i];
		}
		
		return sum;
	}	
	
	public void printNumber(byte[] x, int len) 
	{
		if (!doPrint())
		{
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int j = 0; j < len; j++) 
		{
			sb.append(x[j]);
		}
		
		println(sb);
	}

	public static final int LIM = 1000;
}

