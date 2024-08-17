package xknr.euler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static xknr.euler.Answers.*;

import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import xknr.euler.e000.E01;
import xknr.euler.e000.E01B;
import xknr.euler.e000.E02;
import xknr.euler.e000.E02B;
import xknr.euler.e000.E03;
import xknr.euler.e000.E03B;
import xknr.euler.e000.E03C;
import xknr.euler.e000.E04;
import xknr.euler.e000.E05;
import xknr.euler.e000.E05C;
import xknr.euler.e000.E06;
import xknr.euler.e000.E06Brute;
import xknr.euler.e000.E07;
import xknr.euler.e000.E08;
import xknr.euler.e000.E08B;
import xknr.euler.e000.E09;
import xknr.euler.e010.E10;
import xknr.euler.e010.E10B;
import xknr.euler.e010.E11;
import xknr.euler.e010.E11Brute;
import xknr.euler.e010.E11Data;
import xknr.euler.e010.E11Read;
import xknr.euler.e010.E12;
import xknr.euler.e010.E12B;
import xknr.euler.e010.E13;
import xknr.euler.e010.E13B;
import xknr.euler.e010.E14;
import xknr.euler.e010.E14B;
import xknr.euler.e010.E15;
import xknr.euler.e010.E16;
import xknr.euler.e010.E16B;
import xknr.euler.e010.E18;
import xknr.euler.e010.E18B;
import xknr.euler.e010.E19;
import xknr.euler.e010.E19B;
import xknr.euler.e010.e17.E17;
import xknr.euler.e010.e17.E17B;
import xknr.euler.e020.E20;
import xknr.euler.e020.E21;
import xknr.euler.e020.E22;
import xknr.euler.e020.E24;
import xknr.euler.e020.E25;
import xknr.euler.e020.E25B;
import xknr.euler.e020.E25BProof;
import xknr.euler.e020.E25C;
import xknr.euler.e020.E26;
import xknr.euler.e020.E27;
import xknr.euler.e020.E28;
import xknr.euler.e020.E28B;
import xknr.euler.e020.E29;
import xknr.euler.e020.E29B;
import xknr.euler.e020.e23.E23A;
import xknr.euler.e020.e23.E23B;
import xknr.euler.e020.e23.E23C;
import xknr.euler.e030.E30;
import xknr.euler.e030.E31;
import xknr.euler.e030.E31Print;
import xknr.euler.e030.E32;
import xknr.euler.e030.E33;
import xknr.euler.e030.E34;
import xknr.euler.e030.E35;
import xknr.euler.e030.E36;
import xknr.euler.e030.E36B;
import xknr.euler.e030.E37;
import xknr.euler.e030.E38;
import xknr.euler.e030.E39;
import xknr.euler.e030.E39B;
import xknr.euler.e040.E40;
import xknr.euler.e040.E40B;
import xknr.euler.e040.E40C;
import xknr.euler.e040.E41B;
import xknr.euler.e040.E42;
import xknr.euler.e040.E43;
import xknr.euler.e040.E44;
import xknr.euler.e040.E44B;
import xknr.euler.e040.E45;
import xknr.euler.e040.E46;
import xknr.euler.e040.E47;
import xknr.euler.e040.E47B;
import xknr.euler.e040.E48;
import xknr.euler.e040.E48B;
import xknr.euler.e040.E49;
import xknr.euler.e050.E50;
import xknr.euler.e050.E51;
import xknr.euler.e050.E52;
import xknr.euler.e050.E53;
import xknr.euler.e050.E54;
import xknr.euler.e050.E54B;
import xknr.euler.e050.E55;
import xknr.euler.e050.E56;
import xknr.euler.e050.E57;
import xknr.euler.e050.E58;
import xknr.euler.e050.E59;
import xknr.euler.e060.E60;
import xknr.euler.e060.E60B;
import xknr.euler.e060.E61;
import xknr.euler.e060.E62;
import xknr.euler.e060.E63;
import xknr.euler.e060.E64;
import xknr.euler.e060.E64B;
import xknr.euler.e060.E65;
import xknr.euler.e060.E66;
import xknr.euler.e060.E66B;
import xknr.euler.e060.E67;
import xknr.euler.e060.E68;
import xknr.euler.e060.E69;
import xknr.euler.e060.E69B;
import xknr.euler.e070.E70;
import xknr.euler.e070.E70Study1;
import xknr.euler.e070.E70Study2;
import xknr.euler.e070.E70b;
import xknr.euler.e070.E71;
import xknr.euler.e070.E72;
import xknr.euler.e070.E72B;
import xknr.euler.e070.E72Study1;
import xknr.euler.e070.E73;
import xknr.euler.e070.E73B;
import xknr.euler.e070.E74;
import xknr.euler.e070.E75;
import xknr.euler.e070.E75B;
import xknr.euler.e070.E75_mathblogdk;
import xknr.euler.e070.E76;
import xknr.euler.e070.E76B;
import xknr.euler.e070.E77;
import xknr.euler.e070.E78;
import xknr.euler.e070.E78B;
import xknr.euler.e070.E79;
import xknr.euler.e080.E80;
import xknr.euler.e080.E81;
import xknr.euler.e080.E82;
import xknr.euler.e080.E83;
import xknr.euler.e080.E84;
import xknr.euler.e080.E85;
import xknr.euler.e080.E85b;
import xknr.euler.e080.E86;
import xknr.euler.e080.E86Study;
import xknr.euler.e080.E86Study2;
import xknr.euler.e080.E86_mathblogdk;
import xknr.euler.e080.E87;
import xknr.euler.e080.E88;
import xknr.euler.e080.E89;
import xknr.euler.e090.E90;
import xknr.euler.e090.E91;
import xknr.euler.e090.E92;
import xknr.euler.e090.E93A;
import xknr.euler.e090.E93A2;
import xknr.euler.e090.E93B;
import xknr.euler.e090.E93C;
import xknr.euler.e090.E93D;
import xknr.euler.e090.E94;
import xknr.euler.e090.E94B;
import xknr.euler.e090.E94Study1;
import xknr.euler.e090.E94Study2;
import xknr.euler.e090.E95;
import xknr.euler.e090.E97;
import xknr.euler.e090.E97B;
import xknr.euler.e090.E98;
import xknr.euler.e090.E99;
import xknr.euler.e090.e96.E96;
import xknr.euler.e100.E100;
import xknr.euler.util.Util;

class EulerTests 
{
	@Test
	public void e01() {
		assertEquals(E01_ANS_TOY, new E01().solve(E01_PRM_TOY));
		assertEquals(E01_ANS, new E01().solve(E01_PRM));
	}
	
	@Test
	public void e01bProofs() {
		assertEquals(3, 19/5);
		assertEquals(4, 20/5);
		assertEquals(4, 21/5);
		assertEquals(3 + 6 + 9 + 12, 3 * Util.triangular(12/3));
	}
	
	@Test
	public void e01b() {
		assertEquals(E01_ANS_TOY, new E01B(false).solve(E01_PRM_TOY));
		assertEquals(E01_ANS, new E01B(false).solve(E01_PRM));
	}

	@Test
	public void e02() {
		assertEquals(E02_ANS, new E02(false).solve());
	}
	
	@Test
	public void e02B() {
		assertEquals(E02_ANS, new E02B(false).solve());
	}

	@Test
	public void e03() {
		assertEquals(E03_ANS_TOY, new E03(false).solve(E03_PRM_TOY));
		assertEquals(E03_ANS, new E03(false).solve(E03_PRM));
	}

	@Test
	public void e03B() {
		assertEquals(E03_ANS_TOY, new E03B(false).solve(E03_PRM_TOY));
		assertEquals(E03_ANS, new E03B(false).solve(E03_PRM));
	}

	@Test
	public void e03C() {
		assertEquals(E03_ANS_TOY, new E03C(false).solve(E03_PRM_TOY));
		assertEquals(E03_ANS, new E03C(false).solve(E03_PRM));
	}

	@Test
	public void e04() {		 
		assertEquals(E04_ANS_TOY, new E04(false).solve(E04_PRM_TOY));
		assertEquals(E04_ANS, new E04(false).solve(E04_PRM));
	}
	
	@Test
	public void e05() {
		assertEquals(E05_ANS_TOY, new E05(false).solve(E05_PRM_TOY));
		assertEquals(E05_ANS, new E05(false).solve(E05_PRM));
	}

	@Test
	public void e05C() {
		assertEquals(E05_ANS_TOY, new E05C(false).solve(E05_PRM_TOY, E05C.PRIMES));
		assertEquals(E05_ANS, new E05C(false).solve(E05_PRM, E05C.PRIMES));
	}

	@Test
	public void e06Brute() {
		assertEquals(E06_ANS_TOY, new E06Brute(false).solve(E06_PRM_TOY));
		assertEquals(E06_ANS, new E06Brute(false).solve(E06_PRM));
	}

	@Test
	public void e06() {
		assertEquals(E06_ANS_TOY, new E06(false).solve(E06_PRM_TOY));
		assertEquals(E06_ANS, new E06(false).solve(E06_PRM));
	}

	@Test
	public void e07() {
		assertEquals(E07_ANS, new E07(false).solve());
	}

	@Test
	public void e08() {
		assertEquals(E08_ANS_TOY, new E08(false).solve(E08_TOY_PRM));
		assertEquals(E08_ANS, new E08(false).solve(E08_PRM));
	}

	@Test
	public void e08B() {
		assertEquals(E08_ANS_TOY, new E08B(false).solve(E08_TOY_PRM));
		assertEquals(E08_ANS, new E08B(false).solve(E08_PRM));
	}

	@Test
	public void e09() {
		assertEquals(E09_ANS, new E09(false).solve());
	}

	@Test
	public void e10() {
		assertEquals(E10_ANS, new E10(false).solve(E10_PRM));
	}

	@Test
	public void e10B() {
		assertEquals(E10_ANS, new E10B(false).solve(E10_PRM));
	}
	
	@Test
	public void e11ReadItem() {
		assertEquals(15, E11Read.readItem(E11Data.LINES, 0, 5));
		assertEquals(81, E11Read.readItem(E11Data.LINES, 1, 5));
		assertEquals(14, E11Read.readItem(E11Data.LINES, 2, 6));
	}

	@Test
	public void e11ReadDigit() {
		assertEquals(1, E11Read.readDigit("123", 0));
		assertEquals(2, E11Read.readDigit("123", 1));
		assertEquals(3, E11Read.readDigit("123", 2));
	}

	@Test
	public void e11() {
		assertEquals(E11_ANS, new E11(false).solve(E11_PRM));
	}	

	@Test
	public void e11Brute() {
		assertEquals(E11_ANS, new E11Brute(false).solve(E11_PRM));
	}	
	
	@Test
	public void e12() {
		assertEquals(E12_ANS, new E12(false).solve());
	}
	
	@Test
	public void e12b() {
		assertEquals(E12_ANS, new E12B(false).solve());
	}
	
	@Test
	public void e13() {
		assertEquals(E13_ANS, new E13(false).solve());
	}	

	@Test
	public void e13B() {
		assertEquals(E13_ANS, new E13B(false).solve());
	}	
	
	@Test
	public void e14() {
		assertEquals(E14_ANS, new E14(false).solve());
	}	
	
	@Test
	public void e14b() {
		assertEquals(E14_ANS, new E14B(false).solve());
	}

	@Test
	public void e15() {
		assertEquals(E15_ANS, new E15(false).solve());
	}	

	@Test
	public void e16() {
		assertEquals(E16_ANS, new E16(false).solve());
	}	

	@Test
	public void e16B() {
		assertEquals(E16_ANS, new E16B(false).solve());
	}	

	@Test
	public void e17() {
		assertEquals(E17_ANS, new E17(false).solve());
	}	

	@Test
	public void e17b() {
		assertEquals(E17_ANS, new E17B(false).solve());
	}	

	@Test
	public void e18() {
		assertEquals(E18_ANS, new E18(false).solve());
	}	

	@Test
	public void e18b() {
		assertEquals(E18_ANS, new E18B(false).solve());
	}	
	
	@Test
	public void e19() {
		assertEquals(E19_ANS, new E19(false).solve());
	}	

	@Test
	public void e19B() {
		assertEquals(E19_ANS, new E19B(false).solve());
	}	

	@Test
	public void e20() {
		assertEquals(E20_ANS, new E20(false).solve());
	}	

	@Test
	public void e21() {
		assertEquals(E21_ANS, new E21(false).solve());
	}
	
	@Test
	public void e22() throws IOException {
		assertEquals(E22_ANS, new E22(false).solve());
	}		

	@Test
	public void e23a() {
		assertEquals(E23_ANS, new E23A(false).solve());
	}	

	@Test
	public void e23b() {
		assertEquals(E23_ANS, new E23B(false).solve());
	}	

	@Test
	public void e23c() {
		assertEquals(E23_ANS, new E23C(false).solve());
	}
	
	@Test
	public void e24() {
		assertEquals(E24_ANS, new E24(false).solve() );
	}		

	@Test
	public void e25() {
		assertEquals(E25_ANS, new E25(false).solve() );
	}

	@Test
	public void e25b() {
		assertEquals(E25_ANS, new E25B(false).solve() );
	}

	// @Test
	public void e25BProof() {
		new E25BProof().test();
	}
	
	@Test
	public void e25c() {
		assertEquals(E25_ANS, new E25C(false).solve() );
	}

	@Test
	public void e26() {
		assertEquals(E26_ANS, new E26(false).solve() );
	}

	@Test
	public void e27() {
		assertEquals(E27_ANS, new E27(false).solve() );
	}

	@Test
	public void e28() {
		assertEquals(E28_ANS, new E28(false).solve() );
	}

	@Test
	public void e28b() {
		assertEquals(E28_ANS, new E28B(false).solve() );
	}

	@Test
	public void e29() {
		assertEquals(E29_ANS, new E29(false).solve() );
	}

	@Test
	public void e29B() {
		assertEquals(E29_ANS, new E29B(false).solve() );
	}

	@Test
	public void e30() {
		assertEquals(E30_ANS, new E30(false).solve() );
	}

	@Test
	public void e31() {
		assertEquals(E31_ANS, new E31(false).solve() );
	}

//	@Test
	public void e31Print() {
		assertEquals(E31_ANS, new E31Print().solve() );
	}

	@Test
	public void e32() {
		assertEquals(E32_ANS, new E32(false).solve() );
	}

	@Test
	public void e33() {
		assertEquals(E33_ANS, new E33(false).solve() );
	}

	@Test
	public void e34() {
		assertEquals(E34_ANS, new E34(false).solve() );
	}

	@Test
	public void e35() {
		assertEquals(E35_ANS, new E35(false).solve() );
	}

	@Test
	public void e36() {
		assertEquals(E36_ANS, new E36(false).solve() );
	}

	@Test
	public void e36b() {
		assertEquals(E36_ANS, new E36B(false).solve() );
	}

	@Test
	public void e37() {
		assertEquals(E37_ANS, new E37(false).solve() );
	}

	@Test
	public void e38() {
		assertEquals(E38_ANS, new E38(false).solve() );
	}

	@Test
	public void e39() {
		// 2 seconds for 1000 runs
		assertEquals(E39_ANS, new E39(false).solve() );
	}

	@Test
	public void e39b() {
		// 0.183 seconds for 1000 runs
		assertEquals(E39_ANS, new E39B(false).solve() );
	}

	@Test
	public void e40() {
		// 0.579 seconds for 100 iterations
		assertEquals(E40_ANS, new E40(false).solve() );
	}

	@Test
	public void e40b() {
		// 0.558 seconds for 100 iterations
		assertEquals(E40_ANS, new E40B(false).solve() );
	}
	
	@Test
	public void e40c() {
		// 0.017 seconds for 100 iterations
		assertEquals(E40_ANS, new E40C(false).solve() );
	}

	@Test
	public void e41B() {
		assertEquals(E41_ANS, new E41B(false).solve() );
	}

	@Test
	public void e42() throws IOException {
		assertEquals(E42_ANS, new E42(false).solve() );
	}

	// @Test
	public void E42_isTriangleSimple()
	{
		// Test method with every integer <= T(100000) 
		
		long tested = 1;
		for(long n = 1; n < 100000; n++)
		{
			long t = n * (n + 1) / 2;
			for (; tested < t; tested++) 
			{
				assertEquals(false, E42.isTriangleSimple(tested));
			}
			assertEquals(true, E42.isTriangleSimple(tested));
			
			tested++;
		}
	}

	@Test
	public void e43() {
		assertEquals(E43_ANS, new E43(false).solve() );		
	}	

	// @Test
	public void e44() {
		// This solution takes 20 seconds.
		assertEquals(E44_ANS, new E44(false).solve() );
	}

	@Test
	public void e44B() {
		// This is faster but not a proved way to reach the solution.
		assertEquals(E44_ANS, new E44B(false).solve() );
	}

	@Test
	public void e45() {
		assertEquals(E45_ANS, new E45(false).solve() );
	}

	@Test
	public void e46() {
		assertEquals(E46_ANS, new E46(false).solve() );
	}

	@Test
	public void e47() {
		assertEquals(E47_ANS, new E47(false).solve() );
	}

	@Test
	public void e47B() {
		assertEquals(E47_ANS, new E47B(false).solve() );
	}

	@Test
	public void e48() {
		assertEquals(E48_ANS, new E48(false).solve() );
	}

	@Test
	public void e48B() {
		assertEquals(E48_ANS, new E48B(false).solve() );
	}

	@Test
	public void e49() {
		assertEquals(E49_ANS, new E49(false).solve() );
	}

	@Test
	public void e50() {
		assertEquals(E50_ANS, new E50(false).solve() );
	}

	@Test
	public void e51() {
		assertEquals(E51_ANS, new E51(false).solve() );
	}

	@Test
	public void e52() {
		assertEquals(E52_ANS, new E52(true).solve() );
	}

	@Test
	public void e53() {
		assertEquals(E53_ANS, new E53(false).solve() );
	}

	@Test
	public void e54() {
		assertEquals(E54_ANS, new E54(false).solve() );
	}	

	@Test
	public void e54b() {
		assertEquals(E54_ANS, new E54B(false).solve() );
	}
	
	@Test
	public void e55() {
		assertEquals(E55_ANS, new E55(false).solve() );
	}

	@Test
	public void e56() {
		assertEquals(E56_ANS, new E56(false).solve() );
	}

	@Test
	public void e57() {
		assertEquals(E57_ANS, new E57(false).solve() );
	}
	
	@Test
	public void e58() {
		assertEquals(E58_ANS, new E58(false).solve() );
	}

	@Test
	public void e59() {
		assertEquals(E59_ANS, new E59(false).solve() );
	}

	@Test
	public void e60() {
		assertEquals(E60_ANS, new E60(false).solve() );
	}

	@Test
	public void e60b() {
		assertEquals(E60_ANS, new E60B(false).solve() );
	}

	@Test
	public void e61() {
		assertEquals(E61_ANS, new E61(false).solve() );
	}
	
	@Test
	public void e62() {
		assertEquals(E62_ANS, new E62(false).solve() );
	}

	@Test
	public void e63() {
		assertEquals(E63_ANS, new E63(false).solve() );
	}

	@Test
	public void e64() {
		assertEquals(E64_ANS, new E64(false).solve() );
	}

	@Test
	public void e64b() {
		assertEquals(E64_ANS, new E64B(false).solve() );
	}

	@Test
	public void e65() {
		assertEquals(E65_ANS, new E65(false).solve() );
	}

	@Test
	public void e66() {
		assertEquals(E66_ANS, new E66(false).solve() );
	}

	@Test
	public void e66b() {
		assertEquals(E66_ANS, new E66B(false).solve() );
	}

	@Test
	public void e67() throws IOException {
		assertEquals(E67_ANS, new E67(false).solve() );
	}

	@Test
	public void e68() {
		assertEquals(E68_ANS, new E68(false).solve() );
	}

	@Test
	public void e69() {
		assertEquals(E69_ANS, new E69(false).solve() );
	}
	
	@Test
	public void e69b() {
		assertEquals(E69_ANS, new E69B(false).solve() );
	}
	
	@Test 
	public void E70() {	
		// 765 msec
		assertEquals(E70_ANS, new E70(false).solve());
	}
	
	@Test 
	public void E70b() {	
		// 1.8 seconds
		assertEquals(E70_ANS, new E70b(false).solve());
	}
	
	//@Test 
	public void e70Study1() {	
		// 52 sec
		assertEquals(E70_ANS, new E70Study1(false).solve());
	}

	//@Test 
	public void e70Study2() {	
		// 26 sec
		assertEquals(E70_ANS, new E70Study2(false).solve());
	}

	@Test 
	public void e71() {
		assertEquals(E71_ANS , new E71(false).solve());
	}

	@Test
	public void e72() {
		assertEquals(E72_ANS_TOY1, new E72(false).solve(E72_PRM_TOY1));
		assertEquals(E72_ANS_TOY2, new E72(false).solve(E72_PRM_TOY2));
		assertEquals(E72_ANS, new E72(false).solve(E72_PRM));
	}

	@Test
	public void e72Solve2() {
		assertEquals(E72_ANS_TOY1, new E72Study1(false).solve(E72_PRM_TOY1));
		assertEquals(E72_ANS_TOY2, new E72Study1(false).solve(E72_PRM_TOY2));
//		assertEquals(E72_ANS, new E72Study1(true).solve(E72_PRM));
	}

	@Test
	public void e72b() {
		assertEquals(E72_ANS_TOY1, new E72B(false).solve(E72_PRM_TOY1));
		assertEquals(E72_ANS_TOY2, new E72B(false).solve(E72_PRM_TOY2));
		assertEquals(E72_ANS, new E72B(false).solve(E72_PRM));
	}

	@Test
	public void e73() {
		assertEquals(E73_ANS, new E73(false).solve());
	}

	@Test
	public void e73B() {
		assertEquals(E73_ANS, new E73B(false).solve());
	}

	@Test 
	public void e74() {
		assertEquals(E74_ANS, new E74(false).solve());
	}

	@Test
	public void e75() {		
		assertEquals(E75_ANS, new E75(false).solve());
	}

	@Test
	public void e75b() {		
		assertEquals(E75_ANS, new E75B(false).solve());
	}

	@Test
	public void e75_mathblogdk() {		
		assertEquals(E75_ANS, new E75_mathblogdk(false).solve());
	}

	@Test
	public void e76() {
		assertEquals(E76_ANS, new E76(false).solve());
	}

	@Test
	public void e76b() {		
		assertEquals(E76_ANS, new E76B(false).solve());
	}
	
	@Test
	public void e76_func() 
	{
		HashMap<Integer, Integer> cache = E76.createCache();
		
		assertEquals(3, E76.f(3, 3, cache));
		assertEquals(6, E76.f(5, 4, cache));
		assertEquals(3, E76.f(4, 2, cache));
		assertEquals(1, E76.f(1, 1, cache));
		assertEquals(1, E76.f(5, 1, cache));
	}
	
	@Test
	public void e77() {		
		assertEquals(E77_ANS, new E77(false).solve());
	}

	@Test
	public void e78() {		
		assertEquals(E78_ANS, new E78(false).solve());
	}

	@Test
	public void e78b() {		
		assertEquals(E78_ANS, new E78B(false).solve());
	}

	@Test
	public void e79() throws IOException {		
		assertEquals(E79_ANS, new E79(false).solve());
	}

	@Test
	public void e80() {		
		assertEquals(E80_ANS, new E80(false).solve());
	}

	@Test
	public void e81() {		
		assertEquals(E81_ANS, new E81(false).solve());
	}

	@Test
	public void e82() {		
		assertEquals(E82_ANS, new E82(false).solve());
	}

	@Test
	public void e83() {		
		assertEquals(E83_ANS, new E83(false).solve());
	}

	@Test
	public void e84() {		
		assertEquals(E84_ANS, new E84(false).solve());
	}

	@Test 
	public void e84_addToCellNo()
	{
		assertEquals(E84.CellType.T1, E84.addToCellNo(E84.CellType.GO, 4));
		assertEquals(E84.CellType.H1, E84.addToCellNo(E84.CellType.GO, -3));
		assertEquals(E84.CellType.C3, E84.addToCellNo(E84.CellType.CC2, -3));
		assertEquals(E84.CellType.CC3, E84.addToCellNo(E84.CellType.E1, 12));
	}	
	
	@Test
	public void e85() {		
		assertEquals(E85_ANS, new E85(false).solve());
	}

	@Test
	public void e85b() {		
		assertEquals(E85_ANS, new E85b(false).solve());
	}


//	@Test
	public void e86Study() {	
		new E86Study(true).study();
	}
	
//	@Test
	public void e86_junk() {		
		new E86Study2(true).study();
	}
	
	@Test
	public void e86() {	
		// 3 seconds
		assertEquals(E86_ANS, new E86(false).solve());
	}
	
	@Test
	public void e86_mathblogdk() {
		// 0.151 seconds
		assertEquals(E86_ANS, new E86_mathblogdk(false).solve());
	}

	@Test
	public void e87() {
		assertEquals(E87_ANS, new E87().solve());
	}

	@Test
	public void e88() {		
		// 2.8 seconds
		assertEquals(E88_ANS, new E88(false).solve());
	}

	@Test
	public void e89() throws IOException {		
		assertEquals(E89_ANS, new E89(false).solve());
	}
	
	@Test
	public void e90() {		
		assertEquals(E90_ANS, new E90(false).solve());
	}

	@Test
	public void e91() {		
		assertEquals(E91_ANS, new E91(false).solve());
	}

	@Test
	public void e92() {		
		assertEquals(E92_ANS, new E92(false).solve());
	}
	
	@Test
	public void e93a() {		
		assertEquals(E93_ANS, new E93A(false).solve());
	}
	
	@Test
	public void e93a2() {		
		assertEquals(E93_ANS, new E93A2(false).solve());
	}
	
	@Test
	public void e93b() {		
		assertEquals(E93_ANS, new E93B(false).solve());
	}
	
	@Test
	public void e93c() {		
		assertEquals(E93_ANS, new E93C(false).solve());
	}
	
	@Test
	public void e93d() {		
		assertEquals(E93_ANS, new E93D(false).solve());
	}
	

//	@Test
	public void e94Study1() {
		// 4 seconds
		assertEquals(E94_ANS, new E94Study1(true).study1());
	}
	
//	@Test
	public void e94Test1() {
		// 7.121 seconds
		assertEquals(E94_ANS, new E94Study2(true).study2());
	}

	@Test
	public void e94() {	
		// 2.8 seconds
		assertEquals(E94_ANS, new E94(false).solve());
	}
		
	@Test
	public void e94B() {		
		// 0.06 seconds
		assertEquals(E94_ANS, new E94B(false).solve());
	}
	
	@Test
	public void e95() {		
		assertEquals(E95_ANS, new E95(false).solve());
	}

	@Test
	public void e96() {		
		assertEquals(E96_ANS, new E96(false).solve());
	}

	@Test
	public void e97() {		
		// 1.3 second
		assertEquals(E97_ANS, new E97(false).solve());
	}

	@Test
	public void e97B() {		
		// 0.17 seconds
		assertEquals(E97_ANS, new E97B(false).solve());
	}

	@Test
	public void e98() throws IOException {		
		assertEquals(E98_ANS, new E98(false).solve());
	}

	@Test
	public void e99() throws IOException {
		assertEquals(E99_ANS, new E99(false).solve() );
	}

	@Test
	public void e100() {
		assertEquals(E100_ANS, new E100(false).solve() );
	}	
}
