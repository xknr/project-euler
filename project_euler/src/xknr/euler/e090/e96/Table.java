package xknr.euler.e090.e96;

import java.util.function.Consumer;

public class Table 
{	

	public Table() 
	{
		for(int i = 0; i < EDGE * EDGE; i++) {
			cells[i] = new Cell();		
		}
	}

	public int index(int r, int c) 
	{
		assert r >= 0 && r < EDGE;
		assert c >= 0 && c < EDGE;
		
		return r * EDGE + c;
	}

	public Table createCopy()
	{
		Table t2 = new Table();		
		copyOver(t2);
		return t2;
	}

	private void copyOver(Table dst) 
	{
		for(int i = 0; i < EDGE * EDGE; i++) 
		{
			cells[i].copyOver(dst.cells[i]);
		}
	}

	public boolean complete() 
	{
		for(int r = 0; r < EDGE; r++) 
		{
			for(int c = 0; c < EDGE; c++) 
			{
				if (cell1(r, c).definite() == 0) 
				{
					return false;
				}
			}
		}
		
		return true;
	}

	public int findOnly(int dgt, int s, int mode) 
	{
		for(int i = 0; i < EDGE; i++) 
		{
			if (!cell3(s, i, mode).cant[dgt]) 
			{ 
				return i;
			}
		}
		
		throw new AssertionError();
	}

	public int countCan(int dgt, int s, int mode) 
	{
		int count = 0;
		
		for(int i = 0; i < EDGE; i++) 
		{
			if (!cell3(s, i, mode).cant[dgt]) 
			{
				count++;
			}
		}
		
		return count;
	}

	public static int sectorId(int r, int c) 
	{
		return (r / 3) * 3 + (c / 3);
	}

	public static int sectorSubIndex(int r, int c) 
	{
		return (r % 3) * 3 + (c % 3);
	}
	
	//////// Cell indexing methods ////////
	
	public Cell cell1(int r, int c) 
	{
		return cells[index(r, c)];
	}

	public Cell cell2(int sectorId, int index) 
	{
		assert sectorId >= 0 && sectorId < EDGE;
		assert index >= 0 && index < EDGE;
		
		int sectorRow = sectorId / 3;
		int sectorCol = sectorId % 3;
		
		int subRow = index / 3;
		int subCol = index % 3;
		
		return cell1(sectorRow * 3 + subRow, sectorCol * 3 + subCol);
	}
	
	public Cell cell3(int a, int b, int mode) 
	{
		if (mode == 0) return cell1(a, b);
		else if (mode == 1) return cell1(b, a);
		else if (mode == 2) return cell2(a, b);
		else throw new AssertionError();
	}

	public void forEachCell(Consumer<Cell> on) 
	{
		for(int r = 0; r < EDGE; r++) 
		{
			for(int c = 0; c < EDGE; c++) 
			{
				on.accept(cell1(r, c));
			}
		}
	}
	
	private Cell cells[] = new Cell[EDGE * EDGE];

	public static final int EDGE = 9;
}