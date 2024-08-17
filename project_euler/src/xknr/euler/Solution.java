package xknr.euler;

public class Solution
{	
	public Solution(boolean doPrint) 
	{
		this.doPrint = doPrint;		
		this.tag = getClass().getSimpleName();
	}

	public Solution(boolean doPrint, String tag) 
	{
		this.doPrint = doPrint;
		this.tag = tag;
	}
	
	public void println(String s)
	{
		format("%s%s", s, System.lineSeparator());
	}
	
	public void format(String format, Object ... args)
	{
		if (!doPrint)
		{
			return;
		}
		
		String s = String.format(format, args);

		internal(s);
	}
	
	private void internal(String s) 
	{		
		if (tag == null || tag.equals("")) 
		{
			System.out.format("%s", tag, s);
		}
		else
		{
			System.out.format("[%s] %s", tag, s);
		}		
	}

	public void println(Object ... args)
	{
		if (!doPrint)
		{
			return;
		}
		
		print_internal(true, args);
	}
	
	private void print_internal(boolean newline, Object ... args)
	{
		if (!doPrint) 
		{
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < args.length; i++)
		{
			if (i > 0)
			{
				sb.append(" ");
			}
			
			sb.append(args[i]);
		}

		println(sb.toString());
	}

	private boolean doPrint;
	
	public boolean doPrint() {
		return doPrint;
	}
	
	private String tag;
}

