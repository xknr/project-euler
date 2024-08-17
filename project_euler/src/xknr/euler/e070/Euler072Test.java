package xknr.euler.e070;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import xknr.euler.util.Util;

public class Euler072Test 
{
	@Test
	public void euler072_tests()
	{
		assertEquals(count1(10000, 3), countCheck(10000, 3));
		assertEquals(count1(10000, 5), countCheck(10000, 5));
		assertEquals(count2(10000, 3, 5), countCheck(10000, 3*5));
		assertEquals(count2(10000, 5, 7), countCheck(10000, 5*7));
		assertEquals(count3(10000, 3, 5, 7), countCheck(10000, 3*5*7));
		assertEquals(count4(10000, 5, 11, 7, 19), countCheck(10000, 5*11*7*19));
	}
	
	public static int count4(int N, int y1, int y2, int y3, int y4)
	{
		int c = 0;
		c += N/y1;
		c += N/y2;
		c += N/y3;
		c += N/y4;
		c -= N/(y1*y2);
		c -= N/(y1*y3);
		c -= N/(y1*y4);
		c -= N/(y2*y3);
		c -= N/(y2*y4);
		c -= N/(y3*y4);
		c += N/(y1*y2*y3);
		c += N/(y1*y2*y4);
		c += N/(y1*y3*y4);
		c += N/(y2*y3*y4);
		c -= N/(y1*y2*y3*y4);
		
		return N - c;
	}
	
	public static int count3(int N, int y1, int y2, int y3)
	{
		int c = N/y1 + N/y2 + N/y3 - N/(y1*y2) - N/(y1*y3) - N/(y2*y3) + N/(y1*y2*y3);
		
		return N - c;
	}

	public static int count2(int N, int y1, int y2)
	{
		int c = N/y1 + N/y2 - N/(y1*y2);
		
		return N - c;
	}

	public static int count1(int N, int y1)
	{
		return (N - N/y1);
	}
	
	public static int countCheck(int N, int y)
	{		
		int c = 0;
		
		for(int x = 1; x <= N; x++) 
		{
			if (Util.gcd(y, x) == 1) 
			{
				c++;
			}
		}
		
		return c;
	}
}
