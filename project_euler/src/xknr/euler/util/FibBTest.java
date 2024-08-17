package xknr.euler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

public class FibBTest
{
	@Test
	void test()
	{
		FibB f = new FibB();
		
		for(int i = 0; i < FibTest.F.length; i++) 
		{
			assertEquals(true, f.has());
			assertEquals(Util.B(FibTest.F[i]), f.adv());
			assertEquals(Util.B(FibTest.F[i]), f.curr());
			assertEquals(Util.B(FibTest.F[i]), f.curr());
		}
		
		for(int i = 0; i < next10.length; i++) 
		{
			assertEquals(true, f.has());
			assertEquals(next10[i], f.adv());
			assertEquals(next10[i], f.curr());
			assertEquals(next10[i], f.curr());
		}
	}
	
	// Starting from 4660046610375530309L and 7540113804746346429L:
	private static final BigInteger[] next10 = 
	{
		new BigInteger("12200160415121876738"),
		new BigInteger("19740274219868223167"),
		new BigInteger("31940434634990099905"),
		new BigInteger("51680708854858323072"),
		new BigInteger("83621143489848422977"),
		new BigInteger("135301852344706746049"),
		new BigInteger("218922995834555169026"),
		new BigInteger("354224848179261915075"),
		new BigInteger("573147844013817084101"),
		new BigInteger("927372692193078999176"),
	};

}
