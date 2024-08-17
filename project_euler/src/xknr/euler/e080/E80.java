package xknr.euler.e080;

import java.math.BigDecimal;

import xknr.euler.Solution;

public class E80 extends Solution
{
	public E80(boolean doPrint) {
		super(doPrint);
	}

	public long solve() 
	{		
		int sum = 0;
		
		// TODO Make 100 a constant.
		for(int i = 1; i <= 100; i++)
		{			
			double sqrt = Math.sqrt(i);
			
			int isqrt = (int)(Math.round(sqrt) + 0.5f);
			
			if (isqrt * isqrt == i) 
			{
				continue;
			}
			
			BigDecimal b = new BigDecimal(Integer.toString(i));
		
			BigDecimal s = sqrt(b, 101);
			
			String str = s.toString();
			str = str.replace(".", "");
			
			println(str);
			
			for(int j = 0; j < 100; j++) 
			{
				char c = str.charAt(j);
				
				int d = c - '0';
				assert d >= 0 && d <= 9; 
				
				sum += d;
			}
			
			println(sum);			
		}
		
		return sum;		
	}
	
	
	
	
	/*
	private static final BigDecimal SQRT_DIG = new BigDecimal(101);
	private static final BigDecimal SQRT_PRE = new BigDecimal(10).pow(SQRT_DIG.intValue());
	*/

	/**
	 * Private utility method used to compute the square root of a BigDecimal.
	 * 
	 * @author Luciano Culacciatti 
	 * @url http://www.codeproject.com/Tips/257031/Implementing-SqrtRoot-in-BigDecimal
	 */
	/*
	private static BigDecimal sqrtNewtonRaphson  (BigDecimal c, BigDecimal xn, BigDecimal precision){
	    BigDecimal fx = xn.pow(2).add(c.negate());
	    BigDecimal fpx = xn.multiply(new BigDecimal(2));
	    BigDecimal xn1 = fx.divide(fpx,2*SQRT_DIG.intValue(),RoundingMode.HALF_DOWN);
	    xn1 = xn.add(xn1.negate());
	    BigDecimal currentSquare = xn1.pow(2);
	    BigDecimal currentPrecision = currentSquare.subtract(c);
	    currentPrecision = currentPrecision.abs();
	    if (currentPrecision.compareTo(precision) <= -1){
	        return xn1;
	    }
	    return sqrtNewtonRaphson(c, xn1, precision);
	}
	*/

	/**
	 * Uses Newton Raphson to compute the square root of a BigDecimal.
	 * 
	 * @author Luciano Culacciatti 
	 * @url http://www.codeproject.com/Tips/257031/Implementing-SqrtRoot-in-BigDecimal
	 */
	/*
	public static BigDecimal bigSqrt(BigDecimal c){
	    return sqrtNewtonRaphson(c,new BigDecimal(1),new BigDecimal(1).divide(SQRT_PRE));
	}
	*/
	
	private static BigDecimal TWO = new BigDecimal("2");
	
	public static BigDecimal sqrt(BigDecimal A, final int SCALE)
	{
	    BigDecimal x0 = new BigDecimal("0");
	    BigDecimal x1 = new BigDecimal(Math.sqrt(A.doubleValue()));
	    
	    while (!x0.equals(x1))
	    {
	        x0 = x1;
	        x1 = A.divide(x0, SCALE, BigDecimal.ROUND_HALF_UP);
	        x1 = x1.add(x0);
	        x1 = x1.divide(TWO, SCALE, BigDecimal.ROUND_HALF_UP);
	    }
	    
	    return x1;
	}
	
}

