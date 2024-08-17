package xknr.euler.e050;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import xknr.euler.Constants;
import xknr.euler.Solution;

public class E54 extends Solution
{	
	public E54(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{		
		List<Case> cases = loadData(FILENAME);
		
		int count = 0;
		
		for(Case cas: cases) 
		{
			int result = cas.doesPlayer0Win() ? 1 : 0;
			count += result;
		}
		
		return count;
	}
	
	public List<Case> loadData(String filename) 
	{
		List<String> lines = null;
		
		try {
			lines = Files.readAllLines(Paths.get(filename));
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		List<Case> cases = new ArrayList<Case>();
		
		for(String line: lines)
		{
			String[] parts = line.split(" ");
			assert parts.length == 10;
			
			Case cas = new Case();
			
			Card[][] cards = new Card[2][5];
			
			for(int i = 0; i < parts.length; i++)
			{
				cards[i/5][i%5] = Card.fromStr(parts[i]);
			}
			
			cases.add(cas);
			cas.hands[0] = new Hand(cards[0], parts[0]+" "+parts[1]+" "+parts[2]+" "+parts[3]+" "+parts[4]);
			cas.hands[1] = new Hand(cards[1], parts[5]+" "+parts[6]+" "+parts[7]+" "+parts[8]+" "+parts[9]);
			cas.hands[0].sort();
			cas.hands[1].sort();
		}
	
		return cases;
	}
	
	public static final int M = 1000;
	
	public static class Card
	{
		int val, suit;
		
		public Card(int val, int suit) 
		{
			this.val = val;
			this.suit = suit;
		}
		
		public static Card fromStr(String str) 
		{
			int v = str.charAt(0);
			
			if (v == 'T') v = 10;
			else if (v == 'J') v = 11;
			else if (v == 'Q') v = 12;
			else if (v == 'K') v = 13;
			else if (v == 'A') v = 14;
			else if (v >= '2' && v <= '9') v = v - '0';
			else throw new AssertionError();
			
			int s = str.charAt(1);
			if (s == 'H') s = 0;
			else if (s == 'S') s = 1;
			else if (s == 'C') s = 2;
			else if (s == 'D') s = 3;
			else throw new AssertionError();
			
			return new Card(v, s);
		}

		public long hash()
		{
			// highest card is 14
			return suit * M + val;
		}		
	}
	
	public static class Hand 
	{
		Card[] c = new Card[5];
		String str;
		
		public Hand(Card[] cards, String str) 
		{
			this.c = cards;
			this.str = str;
		}

		public Hand(Card c0, Card c1, Card c2, Card c3, Card c4) 
		{
			this.c = new Card[]{c0, c1, c2, c3, c4};
		}

		public long score()
		{			
			boolean all_same_suit = all_same_suit();
			
			if (all_same_suit) {
				// royal flush
				if (royal_flush()) return makeScore(14);
				
				// straight flush
				if (all_consecutive()) return makeScore(13, c[0].val);
			}

			// four_of_a_kind
			if (sameval(0, 4)) return makeScore(12, c[0].val, c[4].val); 
			if (sameval(1, 4)) return makeScore(12, c[1].val, c[0].val); 

			// full house
			if (sameval(0, 3) && sameval(3, 2)) return makeScore(11, c[0].val, c[4].val);
			if (sameval(0, 2) && sameval(2, 3)) return makeScore(11, c[4].val, c[0].val);
			
			// flush
			if (all_same_suit) return makeScore(10, c[4].val, c[3].val, c[2].val, c[1].val, c[0].val);
			
			// straight
			if (all_consecutive()) return makeScore(9, c[0].val);
			
			// three of a kind
			if (sameval(0, 3)) return makeScore(8, c[0].val, c[4].val, c[3].val);
			if (sameval(1, 3)) return makeScore(8, c[1].val, c[4].val, c[0].val);
			if (sameval(2, 3)) return makeScore(8, c[2].val, c[1].val, c[0].val);
			
			// two pairs
			if (sameval(0, 2) && sameval(2, 2)) return makeScore(7, c[2].val, c[0].val, c[4].val);
			if (sameval(0, 2) && sameval(3, 2)) return makeScore(7, c[3].val, c[0].val, c[2].val);
			if (sameval(1, 2) && sameval(3, 2)) return makeScore(7, c[3].val, c[1].val, c[0].val);

			// one pair
			if (sameval(0, 2)) return makeScore(6, c[0].val, c[4].val, c[3].val, c[2].val);
			if (sameval(1, 2)) return makeScore(6, c[1].val, c[4].val, c[3].val, c[0].val);
			if (sameval(2, 2)) return makeScore(6, c[2].val, c[4].val, c[1].val, c[0].val);
			if (sameval(3, 2)) return makeScore(6, c[3].val, c[2].val, c[1].val, c[0].val);
			
			return makeScore(5, c[4].val, c[3].val, c[2].val, c[1].val, c[0].val);
		}

		private boolean royal_flush()
		{
			return c[0].val == 10 && all_consecutive();
		}

		private boolean all_consecutive()
		{
			return 
				c[1].val == c[0].val + 1 && 
				c[2].val == c[0].val + 2 && 
				c[3].val == c[0].val + 3 && 
				c[4].val == c[0].val + 4; 
		}

		private boolean all_same_suit() {
			if (c[1].suit != c[0].suit) return false;
			if (c[2].suit != c[0].suit) return false;
			if (c[3].suit != c[0].suit) return false;
			if (c[4].suit != c[0].suit) return false;
			return true;
		}
		

		private boolean sameval(int beg, int len) 
		{
			for(int i = beg + 1; i < beg + len; i++) 
			{
				if (c[beg].val != c[i].val) 
				{
					return false;
				}
			}
			return true;
		}

		private long makeScore(int type) {
			return makeScore(type, 0);
		}
		
		private long makeScore(int type, int a) {
			return makeScore(type, a, 0);
		}
		
		private long makeScore(int type, int a, int b) {
			return makeScore(type, a, b, 0);
		}
		
		private long makeScore(int type, int a, int b, int c) {
			return makeScore(type, a, b, c, 0);
		}
		
		private long makeScore(int type, int a, int b, int c, int d) {
			return makeScore(type, a, b, c, d, 0);
		}
		
		private long makeScore(int type, int a, int b, int c, int d, int e)
		{
			assert(type >= 0 && type < M);
			assert(a >= 0 && a< M);
			assert(b>= 0 && b< M);
			assert(c>= 0 && c< M);
			assert(d>= 0 && d< M);
			assert(e>= 0 && e< M);
			
			long result = type;
			
			result *= M; result += a;
			result *= M; result += b;
			result *= M; result += c;
			result *= M; result += d;
			result *= M; result += e;
			
			return result;
		}

		public void sort() 
		{
			Arrays.sort(c, comparator);
		} 

		private static Comparator<Card> comparator = new Comparator<E54.Card>() 
		{
			@Override
			public int compare(Card a, Card b) 
			{
				return Integer.compare(a.val, b.val);
				//return Integer.compare(a.hash(), b.hash());
			}
		};
	}
	
	public static class Case
	{
		Hand hands[] = new Hand[2];

		boolean doesPlayer0Win()
		{			
			long s0 = hands[0].score();
			long s1 = hands[1].score();
			
			return s0 > s1; 
		}
	}

	private static final String FILENAME = Paths.get(Constants.DATA_DIR, "p054_poker.txt").toString();	
}

