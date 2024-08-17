package xknr.euler.e010.e17;

public class Composer extends IComposer
{		
	@Override
	public void append(String s) 
	{
		len += s.length();
	}

	@Override
	public void reset() 
	{
		len = 0;
	}
	
	public int len()
	{
		return len;
	}

	private int len;
}