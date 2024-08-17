package xknr.euler;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import xknr.euler.e070.Euler072Test;
import xknr.euler.miller.MillerRabinTest;
import xknr.euler.prime.PrimesTest;
import xknr.euler.util.FactorsTest;
import xknr.euler.util.FibBTest;
import xknr.euler.util.FibTest;
import xknr.euler.util.UtilTest;

@Suite
@SelectClasses({ 
	EulerTests.class,
	UtilTest.class,
	FibTest.class,
	FibBTest.class,
	FactorsTest.class,
	PrimesTest.class,
	MillerRabinTest.class,
	Euler072Test.class,
	})
public class AllTests {
}
