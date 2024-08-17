package xknr.euler.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

public class FibTest
{
	@Test
	void test()
	{
		Fib f = new Fib();

		// Control array index.
		int expIndex = 0;
		
		while(f.has())
		{
			// Advance and query the new value.
			assertEquals(F[expIndex], f.adv());

			// Query current value.
			assertEquals(F[expIndex], f.curr());

			// Query current value again.
			assertEquals(F[expIndex], f.curr());
			
			// Advance control array index.
			expIndex++;
		}

		// Control array must be exhausted at this point.
		assertEquals(F.length, expIndex);
		
		// f.current must be the last element in control array.
		assertEquals(F[F.length - 1], f.curr());
		assertEquals(F[F.length - 1], f.curr());
		
		assertThrows(NoSuchElementException.class, () -> f.adv() );
		assertThrows(NoSuchElementException.class, () -> f.adv() );

		// Calls to curr after exception
		assertEquals(F[F.length - 1], f.curr());
		assertEquals(F[F.length - 1], f.curr());
	}
	
	static final long[] F = 
	{
		0,
		1,
		1,
		2,
		3,
		5,
		8,
		13,
		21,
		34,
		55,
		89,
		144,
		233,
		377,
		610,
		987,
		1597,
		2584,
		4181,
		6765,
		10946,
		17711,
		28657,
		46368,
		75025,
		121393,
		196418,
		317811,
		514229,
		832040,
		1346269,
		2178309,
		3524578,
		5702887,
		9227465,
		14930352,
		24157817,
		39088169,
		63245986,
		102334155,
		165580141,
		267914296,
		433494437,
		701408733,
		1134903170,
		1836311903,
		2971215073L,
		4807526976L,
		7778742049L,
		12586269025L,
		20365011074L,
		32951280099L,
		53316291173L,
		86267571272L,
		139583862445L,
		225851433717L,
		365435296162L,
		591286729879L,
		956722026041L,
		1548008755920L,
		2504730781961L,
		4052739537881L,
		6557470319842L,
		10610209857723L,
		17167680177565L,
		27777890035288L,
		44945570212853L,
		72723460248141L,
		117669030460994L,
		190392490709135L,
		308061521170129L,
		498454011879264L,
		806515533049393L,
		1304969544928657L,
		2111485077978050L,
		3416454622906707L,
		5527939700884757L,
		8944394323791464l,
		14472334024676221L,
		23416728348467685L,
		37889062373143906L,
		61305790721611591L,
		99194853094755497L,
		160500643816367088L,
		259695496911122585L,
		420196140727489673L,
		679891637638612258L,
		1100087778366101931L,
		1779979416004714189L,
		2880067194370816120L,
		4660046610375530309L,
		7540113804746346429L,
	};
}
