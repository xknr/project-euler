package xknr.euler.prime;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/*
 *
 * Some results:
 * 
 * 700M       14.4 - 6.7 - 4.5
 * 
 */
public class PrimesBenchmark 
{	
	@BeforeAll
	public static void beforeAll() 
	{
		//System.out.println("beforeClass");		
		warmup();
	}
	
	public static void warmup() 
	{
		if (warmupDone) 
		{
			return;
		}
		
		Primes3.makeSieve(MAX_PRIME);
		
		warmupDone = true;
	}

	@Test
	public void timing1() {
		Primes.makeSieve(MAX_PRIME);
	}
	
	@Test
	public void timing2() {
		Primes2.makeSieve(MAX_PRIME);
	}
	
	@Test
	public void timing3() {
		Primes3.makeSieve(MAX_PRIME);
	}

	private static final long MAX_PRIME = 700_000_000;
	
	private static boolean warmupDone = false; 
}
