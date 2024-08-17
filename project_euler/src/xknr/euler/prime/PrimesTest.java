package xknr.euler.prime;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import xknr.euler.util.Util;

import java.util.ArrayList;
import java.util.List;

public class PrimesTest 
{
	@Test
	public void method1Test() 
	{
		boolean[] sieve = Primes.makeSieve(SIEVE_SIZE);
		List<Integer> primes = new ArrayList<Integer>();
		Primes.collect(sieve, SIEVE_SIZE, primes);
		assertArrayEquals(Util.toArray(primes), Primes4000.primes4000);
	}

	@Test
	public void method2Test() 
	{
		boolean[] sieve = Primes2.makeSieve(SIEVE_SIZE);
		List<Integer> primes = new ArrayList<Integer>();
		Primes2.collect(sieve, SIEVE_SIZE, primes);
		assertArrayEquals(Util.toArray(primes), Primes4000.primes4000);
	}
	
	@Test
	public void method3Test() 
	{
		long[] sieve = Primes3.makeSieve(SIEVE_SIZE);
		List<Integer> primes = new ArrayList<Integer>();
		Primes3.collect(sieve, (long)SIEVE_SIZE, primes);
		assertArrayEquals(Util.toArray(primes), Primes4000.primes4000);
	}
	
	private static final int SIEVE_SIZE = 4000;
}
