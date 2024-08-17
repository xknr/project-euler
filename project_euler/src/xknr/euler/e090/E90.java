package xknr.euler.e090;

import xknr.euler.Solution;

public class E90 extends Solution
{
	public E90(boolean doPrint) {
		super(doPrint);
	}

	private int counter = 0;
	
	public long solve()
	{
		State state = new State();
		
		search(state, 0);
		
		return counter / 2;
	}

	private void search(State state, int n)
	{		
		boolean[][] t = state.table;
					
//		for(int j = 0; j < 2; j++) {
//			int count = 0;
//			for(int i = 0; i < 10; i++) if (t[j][i]) count++;
//			if (count > 6) return;
//		}
		
		if (n == 10)
		{
			for(int j = 0; j < 2; j++) 
			{
				int count = 0;
				
				for(int i = 0; i < 10; i++)
				{
					if (t[j][i])
					{
						count++;
					}
				}
				
				if (count != 6)
				{
					return;
				}
			}
			
			if (!canWrite(t, 0, 1)) return;
			if (!canWrite(t, 0, 4)) return;
			if (!canWrite(t, 0, 9) && !canWrite(t, 0, 6)) return;
			if (!canWrite(t, 1, 6) && !canWrite(t, 1, 9)) return;
			if (!canWrite(t, 2, 5)) return;
			if (!canWrite(t, 3, 6) && !canWrite(t, 3, 9)) return;
			if (!canWrite(t, 4, 9) && !canWrite(t, 4, 6)) return;
			if (!canWrite(t, 6, 4) && !canWrite(t, 9, 4)) return;
			if (!canWrite(t, 8, 1)) return;
			
			if (same(t)) 
			{
				throw new AssertionError();
			}
			
			printCurrent(t);
						
			counter++;			
		} 
		else 
		{
			State next = state.klon();

			int start = n == 7 || n == 6 || n == 9 ? 0 : 1;
			
			//int start = 1;
			for(int i = start; i < 4; i++) 
			{
				next.table[0][n] = i / 2 == 1;
				next.table[1][n] = i % 2 == 1;
				search(next, n + 1);
			}
		}		
	}

	public static boolean same(boolean[][] t)
	{		
		for(int i = 0; i < 10; i++) 
		{
			if (t[0][i] != t[1][i]) 
			{
				return false;
			}
		}
		return true;		
	}

	public void printCurrent(boolean[][] t)
	{
		if (!doPrint()) 
		{
			return;
		}
		
		for(int j = 0; j < 2; j++) 
		{
			for(int i = 0; i < 10; i++) 
			{
				if (t[j][i])
				{
					System.out.print(i);
				}
				else
				{
					System.out.print(".");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	public static boolean canWrite(boolean[][] t, int a, int b)
	{		
		if (t[0][a] && t[1][b])
		{
			return true;
		}
		
		if (t[1][a] && t[0][b])
		{
			return true;
		}
		
		return false;
	}		
	
	static class State
	{
		boolean [][] table = new boolean[2][10];
		
		void copyOver(State dst) 
		{
			for(int j = 0; j < 2; j++) 
			{
				for(int i = 0; i < 10; i++) 
				{
					dst.table[j][i] = table[j][i];
				}
			}
		}
		
		State klon()
		{
			State state = new State();
			copyOver(state);
			return state;
		}
	}

}

