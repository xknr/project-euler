package xknr.euler.util;


public class LSQRT
{
	
	
	/**
	 * Compiled from these sources: 
	 * http://stackoverflow.com/a/21626924
	 * http://www.ophome.cn/question/31389
	 */

	//private static final int debruijn= 0x07C4ACDD;
	private static final long debruijn = (~0x0218A392CD3D5DBFL) >>> 6;
	private static long[] SQRT = new long[64];
	private static int[] DeBruijnArray = new int[64];

	static {
		for (int x = 0; x < 64; ++x) {
			final long v = ~(-2L << (x));
			int index = (int) ((v * debruijn) >>> 58);
			DeBruijnArray[index] = x;
		}
		for (int x = 0; x < 64; ++x)
			SQRT[x] = (long) (Math.sqrt((1L << DeBruijnArray[x]) * Math.sqrt(2)));
	}

	public static long lsqrtB(final long num) {
		long y;
		if (num == 0)
			return num;

		{
			long v = num;
			v |= v >>> 1; // first round up to one less than a power of 2
			v |= v >>> 2;
			v |= v >>> 4;
			v |= v >>> 8;
			v |= v >>> 16;
			v |= v >>> 32;

			int index = (int) ((v * debruijn) >>> 58);
			y = SQRT[index];
		}
		y = (y + num / y) >>> 1;
		y = (y + num / y) >>> 1;
		y = (y + num / y) >>> 1;
		y = (y + num / y) >>> 1;
		return y * y > num ? y - 1 : y;
	}	
	
}