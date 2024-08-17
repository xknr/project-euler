package xknr.euler.e090.e96;


import java.nio.file.Paths;
import java.util.List;

import xknr.euler.Constants;
import xknr.euler.Solution;
import xknr.euler.util.Util;

public class E96 extends Solution
{
	public E96(boolean doPrint) {
		super(doPrint);
	}

	private static final int NUM_TABLES = 50;

	private Table [] tables = new Table[NUM_TABLES];

	private int tableIndex;
	
	public long solve()
	{
		readInput();
		
		int sumCodes = 0;
		
		for(tableIndex = 0; tableIndex < NUM_TABLES; tableIndex++)
		{
			//System.out.println("tableIndex " + tableIndex);
			
			Table t = tables[tableIndex];
			printTable(t);

			setInitials(t);
			
			printStates(t);

			Table result = recursive(t, 0);

			int number = 0;
			number += result.cell1(0, 0).definite() * 100;
			number += result.cell1(0, 1).definite() * 10;
			number += result.cell1(0, 2).definite();
			sumCodes+= number;
			
			printStates(result);
			println(number);

//			t.cell(2, 0).cant[8] = true;						
//			exhaust(t);
//			printStates(t);
			
			//break;
		}
		
		return sumCodes;
		
	}

	private Table recursive(Table t, int depth)
	{
		format("recursive %d %d\n", depth, tableIndex);
		printStates(t);
		
		exhaust(t);
		
		if (t.complete()) {
			printStates(t);
			return t;
		}
		
		println("recursive", depth, " ", tableIndex, "will try");
		
		for(int r = 0; r < Table.EDGE; r++)
		{
			for(int c = 0; c < Table.EDGE; c++) 
			{
				if (t.cell1(r, c).definite() == 0) 
				{
					//System.out.println(r + " " + c);
					
					for(int dgt = 1; dgt <= 9; dgt++) 
					{
						if (!t.cell1(r, c).cant[dgt]) 
						{
							Table t2 = t.createCopy();	
							
							//System.out.println("set digit " + dgt);
							t2.cell1(r, c).setCantExcept(dgt);
							
							try {
								return recursive(t2, depth+1);
							} catch(Impossible e) {			
								//System.out.println(e.getMessage() + " " + depth);
							}
						}
					}
					
					//System.out.println("tried all");
					throw new Impossible("tried all");					
				}
			}
		}
		
		throw new Impossible("no valid solutions for recursive " + depth);
		
	}

	private void exhaust(Table t)
	{
		while(true) 
		{			
			printStates(t);
			
			t.forEachCell( (c) -> c.gained = false );
			
			int gained = 0;
			
			for(int r = 0; r < 9; r++) {
				for(int c = 0; c < 9; c++) {
					int dgt = t.cell1(r, c).definite();
					if (dgt == 0) continue;
					int s = Table.sectorSubIndex(r, c);
					int si = Table.sectorId(r, c);
					for(int i = 0; i < 9; i++) {
						if (i != c && t.cell1(r, i).trySetCant(dgt)) gained++;
						if (i != r && t.cell1(i, c).trySetCant(dgt)) gained++;
						if (i != s && t.cell2(si, i).trySetCant(dgt)) gained++;
					}
				}
			}
			
			for(int s = 0; s < 9; s++) {
				for(int dgt = 1; dgt <= 9; dgt++) {					
					for(int mode = 0; mode < 3; mode++) {
						int count = t.countCan(dgt, s, mode);
						if (count == 0) throw new Impossible("count=0 in exhaust, mode=" + mode + " dgt=" + dgt + " s=" + s);
						if (count != 1) continue;
						int only = t.findOnly(dgt, s, mode);							
						if (t.cell3(s, only, mode).setCantExcept(dgt))
							gained++;
						
					}
				}
			}
			
//			System.out.println("gainedCount " + gained);
			if (gained == 0) break;
		}
		
	}

	private void setInitials(Table t)
	{
		t.forEachCell( (cell) -> {
			if (cell.g != 0) {
				for(int i = 1; i <= 9; i++) {
					cell.cant[i] = i != cell.g;
				}
			}
		} );
	}

	private void printStates(Table t)
	{
		if (!doPrint())
			return;

		StringBuilder sb = new StringBuilder();
		
		for(int r = 0; r < Table.EDGE; r++) 
		{
			for(int c = 0; c < Table.EDGE; c++) 
			{				
				String unk = t.cell1(r, c).definite() == 0 ? "." : "_" ;
				
				for(int i = 1; i <= 9; i++) {
					sb.append(t.cell1(r, c).cant[i] ? unk : "" + i);
				}
				
				sb.append(" ");
				
				if (c % 3 == 2) 
					sb.append("  ");
				// sb.append("\n");
			}
			
			sb.append("\n");
			
			if (r % 3 == 2) 
				sb.append("\n");
		}
		println("\n" + sb.toString());
	}

	public void printTable(Table t)
	{	
		if (!doPrint()) 
			return;
		
		StringBuilder sb = new StringBuilder();
		
		for(int r = 0; r < Table.EDGE; r++) 
		{
			for(int c = 0; c < Table.EDGE; c++) 
			{
				int cell = t.cell1(r, c).g;
				
				if (cell == 0)
					sb.append(".");
				else
					sb.append(cell);
				
				if (c % 3 == 2) 
					sb.append(" ");
			}
			
			sb.append("\n");
			
			if (r % 3 == 2) 
				sb.append("\n");
		}

		println(sb.toString());
	}

	private void readInput()
	{
		List<String> lines = Util.readAllLines(filename);
		
		int lineInd = 0;
		for(int i = 0; i < NUM_TABLES; i++) 
		{
			String heading = lines.get(lineInd++);			
			assert String.format("Grid %02d", i + 1).equals(heading);
			
			Table t = tables[i] = new Table();

			for(int row = 0; row < Table.EDGE; row++) 
			{
				String line = lines.get(lineInd++);
				for(int col = 0; col < Table.EDGE; col++) {
					int num = line.charAt(col) - '0';
					t.cell1(row, col).g = num;
				}
			}
		}
	}
	
	private static final String filename = Paths.get(Constants.DATA_DIR, "p096_sudoku.txt").toString();
	
}

