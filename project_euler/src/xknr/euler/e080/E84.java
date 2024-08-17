package xknr.euler.e080;


import static xknr.euler.e080.E84.CellType.*;

import xknr.euler.Solution;
import xknr.euler.util.MersenneTwisterFast;

public class E84 extends Solution
{	
	public E84(boolean doPrint) {
		super(doPrint);
	}

	public enum CellType {
		  GO,  A1, CC1,  A2,  T1,  R1,  B1, CH1,  B2,  B3, 
		JAIL,  C1,  U1,  C2,  C3,  R2,  D1, CC2,  D2,  D3,
		  FP,  E1, CH2,  E2,  E3,  R3,  F1,  F2,  U2,  F3, 
		 G2J,  G1,  G2, CC3,  G3,  R4, CH3,  H1,  T2,  H2,											
	}

	private static MersenneTwisterFast rng = new MersenneTwisterFast(1);
	//private static Random rng = new Random();
	
	private static int dicea;
	private static int diceb;
	private static int doubleCount = 0;

	public int solve() 
	{		
		CellType currentCell;
		
		currentCell = GO;
		
		doubleCount = 0;
		
		int cellCounters[] = new int[40];
		int rollCount;		
		
		// TODO make 10M a constant
		for(rollCount = 0; rollCount < 10_000_000; rollCount++) 
		{			
			format("move %d, currently at %s\n", rollCount, currentCell);
			
			currentCell = decideNextCell(currentCell);
			//if (currentCell == JAIL) doubleCount = 0;
			
			cellCounters[currentCell.ordinal()]++;
			
			println();			
		}
		
		println("last seen on " + currentCell);

		//printReport(cellCounters, rollCount);
		
		int best[] = {-1, -1, -1};
		
		for(int i = 0; i < 40; i++)
		{
			if (best[0] == -1 || cellCounters[i] > cellCounters[best[0]]) 
			{
				best[2] = best[1];
				best[1] = best[0];
				best[0] = i;
			}
			else if (best[1] == -1 || cellCounters[i] > cellCounters[best[1]]) 
			{
				best[2] = best[1];
				best[1] = i;
			}
			else if (best[2] == -1 || cellCounters[i] > cellCounters[best[2]]) 
			{
				best[2] = i;
			}						
		}
		
		if (doPrint())
		{
			System.out.println("----");
			
			for(int i = 0 ; i < 3; i++) 
			{
				int b = best[i];
				String name = CellType.values()[b].toString();
				int count = cellCounters[b];
				double freq = count / (double)rollCount; 
				System.out.format("%4s : %d of %d (%f%%)\n", name, count, rollCount, freq * 100);
			}
		}
		
		String result = modalString(best[0]) + modalString(best[1]) + modalString(best[2]);
		
		return Integer.parseInt(result);
	}

	protected void printReport(int[] cellCounters, int rollCount)
	{
		if (!doPrint())
		{
			return;
		}
		
		println("cell frequencies");
		
		// TODO Make 40 a constant. 
		for(int i = 0; i < 40; i++) 
		{
			String name = CellType.values()[i].toString();
			int count = cellCounters[i];
			double freq = count / (double)rollCount; 
			format("%4s : %d of %d (%f%%)\n", name, count, rollCount, freq * 100);						
		}
	}
	
	public static String modalString(int no) 
	{
		if (no < 10) 
		{
			return "0" + no;
		} 
		else 
		{
			return Integer.toString(no);				
		}
	}

	private CellType decideNextCell(CellType cell)
	{
		rollDice();
		printDice();
				
		if (isDiceDouble()) 
		{
			doubleCount++;
			if (doubleCount == 3) 
			{
				doubleCount = 0;
				println("3 consecutive doubles... goto jail");
				return JAIL;
			}
		} else {
			doubleCount = 0;
		}

		cell = addToCellNo(cell, diceSum() );		
		
		if (isCellCH(cell)) cell = draw_ch(cell);
		if (isCellCC(cell)) cell = draw_cc(cell);
		
		if (cell == G2J)
		{
			cell = JAIL;
		}
		
		return cell; 
	}

	private static CellType draw_cc(CellType currentCell) 
	{		
		int card = rng.nextInt(16) + 1;
		
		switch(card) 
		{
			case 1: return GO;
			case 2: return JAIL;
			default: return currentCell;
		}
	}

	private static CellType draw_ch(CellType currentCell) 
	{
		int card = rng.nextInt(16) + 1;
		
		switch(card) 
		{
			case 1: return GO;
			case 2: return JAIL;
			case 3: return C1;
			case 4: return E3;
			case 5: return H2;
			case 6: return R1;
			case 7: 
			case 8: return nextRailway(currentCell);
			case 9: return nextUtility(currentCell);
			case 10: return addToCellNo(currentCell, -3);
			default: return currentCell;
		}
	}

	private static CellType nextUtility(CellType cell) {
		switch(cell) 
		{
			case CH2: return U2;
			case CH1: 
			case CH3: return U1;
			default: throw new AssertionError();
		}
	}

	private static CellType nextRailway(CellType cell) {
		switch(cell) 
		{
			case CH1: return R2;
			case CH2: return R3;
			case CH3: return R1;
			default: throw new AssertionError(); 
		}
	}

	public static CellType addToCellNo(CellType cell, int offset) {
		
		int no = cell.ordinal();
		
		no += offset;
		no = no % 40;
		
		no += 40;
		no = no % 40;
		
		return CellType.values()[no];		
	}

	private static boolean isCellCC(CellType c) {
		return c == CC1 || c == CC2 || c == CC3;
	}

	private static boolean isCellCH(CellType c) {
		return c == CH1 || c == CH2 || c == CH3;
	}

	private static boolean isDiceDouble() {
		return dicea == diceb;
	}

	private static int diceSum() {
		return dicea + diceb;
	}
	
	private void printDice() {
		println("    [" + dicea + " - " + diceb + "]");
	}
	
	public static void rollDice() {
		
		dicea = rng.nextInt(4) + 1;
		diceb = rng.nextInt(4) + 1;
		
//		dicea = rng.nextInt(6) + 1;
//		diceb = rng.nextInt(6) + 1;
		
		//dicea = (int)(rng.nextFloat() * 6) + 1;
		//diceb = (int)(rng.nextFloat() * 6) + 1;
	}
	
	
}

