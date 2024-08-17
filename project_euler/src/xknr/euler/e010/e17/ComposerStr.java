package xknr.euler.e010.e17;

public class ComposerStr extends IComposer
{
	public ComposerStr()
	{
		reset();
	}
	
	@Override
	public void append(String s) 
	{
		sb.append(s);
	}
	
	@Override
	public void reset() 
	{
		sb = new StringBuilder();
	}

	public String str() 
	{
		return sb.toString();
	}

	private StringBuilder sb;
}
