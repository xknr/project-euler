package xknr.euler.util;

/**
 * 
 * Source:
 * Fastest way to determine if an integer's square root is an integer 
 * http://stackoverflow.com/a/18686659
 */
public class Maartinus
{	
	private static long goodMask; // 0xC840C04048404040 computed below
	
	static 
	{
	    for (int i = 0; i < 64; ++i) { 
	    	goodMask |= Long.MIN_VALUE >>> (i*i);
	    }
	}

	public static boolean isSquare(long x)
	{
	    // This tests if the 6 least significant bits are right.
	    // Moving the to be tested bit to the highest position saves us masking.
	    if (goodMask << x >= 0) return false;
	    final int numberOfTrailingZeros = Long.numberOfTrailingZeros(x);
	    // Each square ends with an even number of zeros.
	    if ((numberOfTrailingZeros & 1) != 0) return false;
	    x >>= numberOfTrailingZeros;
	    // Now x is either 0 or odd.
	    // In binary each odd square ends with 001.
	    // Postpone the sign test until now; handle zero in the branch.
	    if ((x&7) != 1 | x <= 0) return x == 0;
	    // Do it in the classical way.
	    // The correctness is not trivial as the conversion from long to double is lossy!
	    final long tst = (long) Math.sqrt(x);
	    return tst * tst == x;
	}
	
}