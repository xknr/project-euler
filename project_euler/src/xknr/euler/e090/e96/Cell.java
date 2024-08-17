package xknr.euler.e090.e96;

public class Cell 
{
	int g;
	boolean gained;
	boolean [] cant = new boolean[10];
	
	public boolean trySetCant(int digit) 
	{
		if (!cant[digit]) 
		{
			cant[digit] = true;
			
			if (!gained) 
			{
				gained = true;
				return true;
			}
		}
		
		return false;
	}
	
	public void copyOver(Cell dst) 
	{
		dst.g = this.g;
		dst.gained = this.gained;
		System.arraycopy(cant, 0, dst.cant, 0, cant.length);
	}

	public int definite() 
	{
		int found = 0;
		
		int cantCount = 0;
		
		for(int dgt = 1; dgt <= 9; dgt++)
		{
			if (cant[dgt])
			{
				cantCount++;
			}
			else
			{
				found = dgt;
			}
		}
		
		if (cantCount > 9) throw new AssertionError();
		if (cantCount == 9) throw new Impossible("cantCount == 9 in definite");
		if (cantCount == 8) return found;
		
		return 0;
	}
	
	public boolean setCantExcept(int dgt) 
	{
		boolean changed = false;
		
		for(int i = 1; i <= 9; i++) 
		{
			if (i == dgt)
			{
				continue;
			}
			
			if (trySetCant(i))
			{
				changed = true;
			}
		}
		
		return changed;
	}

}