package xknr.euler.prime;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PrimesTestBig 
{
	@Test
	public void bigTest() 
	{
		final long maxPrime = 1_000_000_000L;
		
		boolean[] sieve1 = Primes.makeSieve(maxPrime);
		boolean[] sieve2 = Primes2.makeSieve(maxPrime);
		long[] sieve3 = Primes3.makeSieve(maxPrime);
		
		// For every integer in range [0, maxPrime]
		for(long p = 0; p <= maxPrime; p++)
		{			
			// Take three values for p from the three methods.
			final boolean val1 = !sieve1[(int)p];
			final boolean val2 = Primes2.isPrime(sieve2, p);
			final boolean val3 = Primes3.isPrime(sieve3, p);
			
			try 
			{
				// p = 2 must be prime.
				assertTrue(p != 2 || val1);
				
				// Every even p > 2 must be nonprime.
				assertTrue(!(p > 2 && p % 2 == 0) || !val1);
				
				// Other methods' outputs must be equals to method1's.
				assertEquals(val1, val2);
				assertEquals(val1, val3);
			} 
			catch(AssertionError e) 
			{
				// Print detailed info about program state.
				System.err.format("p = %d --- %b %b %b\n", p, val1, val2, val3);
				throw e;
			}
		}
	}	
}
