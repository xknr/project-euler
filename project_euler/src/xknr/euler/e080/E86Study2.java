package xknr.euler.e080;

import xknr.euler.Solution;

public class E86Study2 extends Solution
{
	public E86Study2(boolean doPrint) {
		super(doPrint);
	}

	public void study() 
	{
		int edgex = 6;
		int edgey = 5;
		int edgez = 3;
				
		search(edgex, edgey, edgez);
		search(edgey, edgez, edgex);
		search(edgez, edgex, edgey);
	}
	
	public String search(float edgex, float edgey, float edgez)
	{	
		// sqrt(z^2 + edgex^2) + sqrt((edgez-z)^2 + edgey^2)
		
		float min_len = edgex + edgey + edgez;
		float best_z = 0;
		
		for(float z = 0; z < edgez; z += 0.001) 
		{
			float mz = edgez - z;
			float term1 = sqrt(sq(z) + sq(edgex));
			float term2 = sqrt(sq(mz) + sq(edgey));
			float len = term1 + term2;
			
			if (len < min_len) 
			{
				min_len = len;
				best_z = z;
			}
			
			//format("%f %f + %f = %f    %f %f\n", z, term1, term2, len, tan1, tan2);
			format("%f %f + %f = %f\n", z, term1, term2, len);			
		}
		
		//System.out.println(best_z);

		float best_mz = edgez - best_z;
		float tan1 = best_z / edgex;
		float tan2 = best_mz / edgey;
		float term1 = sqrt(sq(best_z) + sq(edgex));
		float term2 = sqrt(sq(best_mz) + sq(edgey));
				
		String result = String.format("%f %f + %f = %f    %f %f", 
			best_z, term1, term2, min_len, tan1, tan2);

		println(result);
		
		return result;
	}
	
	public static float sqrt(float x) 
	{
		return (float)Math.sqrt(x);		
	}

	public static float sq(float x) 
	{
		return x*x;		
	}
}
