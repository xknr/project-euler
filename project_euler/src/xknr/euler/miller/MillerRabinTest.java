package xknr.euler.miller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import xknr.euler.prime.PrimesFirst1000;

import java.math.BigInteger;

public class MillerRabinTest
{

	@Test
	public void miller()
	{
		for(int i = 1; i < PrimesFirst1000.lastOf1000(); i++) {
			//if (i > 2000) break;
			boolean expected = PrimesFirst1000.IS_PRIME_FIRST_1000(i);
			boolean result = MillerRabin1.isPrime(i);
//			System.out.println(i + " "+ expected + " " + result);
			assertEquals(expected, result);
		}
	}
	
	@Test
	public void miller1()
	{
		for(int i = 1; i < PrimesFirst1000.lastOf1000(); i++) {
			//if (i > 2000) break;
			boolean expected = PrimesFirst1000.IS_PRIME_FIRST_1000(i);
			boolean result = MillerRabin2.isPrime2(i);
//			System.out.println(i + " "+ expected + " " + result);
			assertEquals(expected, result);
		}
	}	
	
	@Test
	public void miller3()
	{
		for(int i = 1; i < PrimesFirst1000.lastOf1000(); i++) {
			//if (i > 2000) break;
			boolean expected = PrimesFirst1000.IS_PRIME_FIRST_1000(i);
			boolean result = MillerRabin3.isPrime(i);
//			System.out.println(i + " "+ expected + " " + result);
			assertEquals(expected, result);
		}
	}
	
	@Test
	public void millerBig()
	{
		for(int i = 1; i < PrimesFirst1000.lastOf1000(); i++) {
			//if (i > 2000) break;
			boolean expected = PrimesFirst1000.IS_PRIME_FIRST_1000(i);
			boolean result = MillerRabinBig.isPrime(BigInteger.valueOf(i), false);
			//System.out.println(i + " "+ expected + " " + result);
			assertEquals(expected, result);
		}
	}
	
/*
	@Test
	public void miller2() {
		
		for(int i = 3; i < 23_000_001; i += 2) {
			//if (i > 2000) break;
			if (i % 100000 == 1) System.out.println(i);
			//System.out.println(i + " " + Prime4.isPrime2(i));
			boolean expected = Util.isPrime(i);
			boolean result = Prime4.isPrime(i);
//			System.out.println(i + " "+ expected + " " + result);
			assertEquals(expected, result);			
		}
		 
	}
	*/
/*
	@Test
	public void miller4() {
		
		for(long i = 2_000_000_000L - 1L; i > 0; i -= 2) {
			//if (i > 2000) break;
			if (i % 100000 == 1) System.out.println(i);
			//System.out.println(i + " " + Prime4.isPrime2(i));
			boolean expected = Util.isPrime(i);
			boolean result = Prime4.isPrime2(i);
//			System.out.println(i + " "+ expected + " " + result);
			assertEquals(expected, result);			
		}
		 
	}
	*/
	
	/*
	@Test
	public void miller3() {

		for(int i = 3; i < 23_000_000; i += 2) {
			//if (i > 2000) break;
			boolean expected = Util.isPrime(i);
			boolean result = Prime4.isPrime2(i);
			//System.out.println(i + " "+ expected + " " + result);
			assertEquals(expected, result);			
		}
		 
	}
	*/
	

	@Test
	public void forDebugMiller() {
		MillerRabin2.isPrime2(13);
		BigInteger n = BigInteger.valueOf(13);
		MillerRabinBig.isPrime(n);
	}

}
