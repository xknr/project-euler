package xknr.euler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.LongFunction;

import org.junit.jupiter.api.Test;

import xknr.euler.prime.Primes4000;

public class FactorsTest
{
	
	public static interface FactorsFactory extends LongFunction<ISeq<Long>> 
	{		
	}
	
	@Test
	void test() 
	{
		// Integer list of some primes.
		List<Integer> primes = Util.asList(Primes4000.primes4000);
		
		// Same list as longs.
		List<Long> primesL = new ArrayList<Long>();
		for(int p: primes)
		{
			primesL.add((long)p);
		}
		
		FactorsFactory factory1 = new FactorsFactory() {
			@Override 
			public ISeq<Long> apply(long num) {
				return new FactorsFixed(num);
			}
		};

		FactorsFactory factory2 = new FactorsFactory() {
			@Override 
			public ISeq<Long> apply(long num) {
				return new FactorsPList(num, primes);
			}
		};

		FactorsFactory factory3 = new FactorsFactory() {
			@Override 
			public ISeq<Long> apply(long num) {
				return new Factors<>(num, new SeqList<>(primes));
			}
		};

		FactorsFactory factory4 = new FactorsFactory() {
			@Override 
			public ISeq<Long> apply(long num) {
				return new Factors<>(num, new Seq23579());
			}
		};

		FactorsFactory factory5 = new FactorsFactory() {
			@Override 
			public ISeq<Long> apply(long num) {
				return new Factors<>(num, new SeqList<>(primesL));
			}
		};

		FactorsFactory factories[] = {factory1, factory2, factory3, factory4, factory5};

		for(FactorsFactory factory: factories) 
		{
			testFactors(factory, new int[] {2,5,5,11} );
			testFactors(factory, new int[] {2,5} );
			testFactors(factory, new int[] {2} );
			testFactors(factory, new int[] {7} );
			testFactors(factory, new int[] {7,7} );
			testFactors(factory, new int[] {5,5,7,7} );
			testFactors(factory, new int[] {5,11,11} );
			testFactors(factory, new int[] {5,7,7,11,11} );
			testFactors(factory, new int[] {} );
		}
	}

	/**
	 * 
	 * @param factors Must be sorted prime numbers.
	 */
	private void testFactors(FactorsFactory factory, int[] factors) 
	{
		// Calculate product of all factors.
		long num = 1;
		for(int e: factors)
		{
			num *= e;
		}
		
		ISeq<Long> f = factory.apply(num);
		
		assertThrows(NoSuchElementException.class, () -> f.curr() );
		assertThrows(NoSuchElementException.class, () -> f.curr() );

		for(int e: factors)
		{
			assertEquals(true, f.has());
			assertEquals(true, f.has());
			assertEquals(e, f.adv());
			assertEquals(e, f.curr());
			assertEquals(e, f.curr());
		}
		
		assertThrows(NoSuchElementException.class, () -> f.adv() );
		assertThrows(NoSuchElementException.class, () -> f.adv() );
		assertEquals(false, f.has());

		if (factors.length > 0)
		{
			assertEquals(factors[factors.length-1], f.curr());
			assertEquals(factors[factors.length-1], f.curr());
		}		
	}
}
