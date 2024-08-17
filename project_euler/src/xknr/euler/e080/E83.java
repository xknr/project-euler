package xknr.euler.e080;


import static java.lang.Math.abs;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import xknr.euler.Constants;
import xknr.euler.Solution;

public class E83 extends Solution
{
	public E83(boolean doPrint) {
		super(doPrint);
	}
	
	public long solve() 
	{		
		m = E81.readInput(INPUT_FILENAME);
				
		closed = new ArrayList<Point>();
		open = new ArrayList<Point>();		
		
		Point initial = new Point(0, 0);
		
		m[initial.y][initial.x] *= -1;
		add_to_closed(initial);
		
		while(true)
		{						
			Point lowest_open = find_lowest_open();
			println(m[lowest_open.y][lowest_open.x]);
			
			open.remove(lowest_open);
			add_to_closed(lowest_open);
			
			if (m[W-1][W-1] < 0) 
			{
				break;
			}
		}
		
		return -m[W-1][W-1];
	}
	
	private void add_to_closed(Point p)
	{		
		final int nx[] = {-1, 0, 1, 0};
		final int ny[] = {0, -1, 0, 1};
		
		closed.add(p);

		println("adding to closed :  " + p.x + " " + p.y);
		
		for(int ni = 0; ni < 4; ni++) 
		{			
			int x = p.x + nx[ni];
			int y = p.y + ny[ni];
			if (x < 0 || x >= W || y < 0 || y >= W) 
			{
				continue;
			}
			
			if (m[y][x] > 0) 
			{
				m[y][x] += abs(m[p.y][p.x]);
				m[y][x] *= -1;
				open.add(new Point(x, y));
			}

		}		
	}

	private Point find_lowest_open()
	{		
		Point r = null; 
		for(Point p: open) 
		{
			if (r == null || abs(m[p.y][p.x]) < abs(m[r.y][r.x])) 
			{ 
				r = p;
			}
		}		
		return r;		
	}
	
	protected void print1()
	{
		if (!doPrint())
			return;
		
		for(int j = 0 ; j < W; j++) 
		{
			for(int i = 0 ; i < W; i++) 
			{
				System.out.format("%5d", m[j][i]);
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.println();
	}

	public static class Point 
	{
		int x, y;
		Point(int x, int y) 
		{
			this.x = x;
			this.y = y;
		}
	}

	private int[][] m;
	private List<Point> closed;
	private List<Point> open;
	
	private static final int W = 80;
	private static final String INPUT_FILENAME = Paths.get(Constants.DATA_DIR, "p083_matrix.txt").toString();
}
