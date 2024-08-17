package xknr.euler.e050;



import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;

import xknr.euler.Constants;
import xknr.euler.Solution;
import xknr.euler.util.Util;

public class E54B extends Solution
{
	public E54B(boolean doPrint) {
		super(doPrint);
	}

	public long solve()
	{
		String fileContent = Util.readFileAsString(FILENAME);
		String lines[] = fileContent.split(regex_split_lines);
		
		int count = 0;
		
		StringBuilder sb = new StringBuilder();
		
		for(String line: lines) 
		{
			int result = process_line(line);
		
			count += result;
			
			if (sb != null)
			{
				sb.append(result);
			}
		}
		
		println(sb.toString());

		return count;
	}
	
	static class Score 
	{
		// we need 6 rank indicators
		// r[0] is not used...
		public int r[] = new int[7];
		
		public Score() 
		{ 
			for(int i = 0; i < r.length; i++) 
			{ 
				r[i] = 0xffff;
			}
		}
	}
	
	static enum Suit 
	{
		spade(0), 
		cross(1), 
		heart(2),
		diamond(3);
		
		Suit(int val) 
		{
			this.val = val;
		}
		
		private int val;
		
		public int val() 
		{
			return val;
		}
	}
	
	static class Card 
	{
		public int v;
		public Suit suit;
		
		public Card(int val, Suit suit) 
		{
			this.v = val;
			this.suit = suit;			
		}
		
		public Card(String str)
		{			
			if (str.length() != 2) 
				throw new AssertionError();
			
			char c0 = str.charAt(0);
			char c1 = str.charAt(1);
			
			v = selectVal(c0);			
			suit = selectSuit(c1);			
		}

		public static int selectVal(char c) 
		{			
			if (c >= '2' && c <= '9') {
				return c - '0';
			} else if (c == 'T') {
				return 10;
			} else if (c == 'A') {
				return 14;
			} else if (c == 'J') {
				return 11;
			} else if (c == 'Q') {
				return 12;
			} else if (c == 'K') {
				return 13;
			} else {
				throw new AssertionError();
			}
		}

		public static Suit selectSuit(char c) throws AssertionError 
		{
			if (c == 'S') {
				return Suit.spade;
			} else if (c == 'C') {
				return Suit.cross;
			} else if (c == 'H') {
				return Suit.heart;
			} else if (c == 'D') {
				return Suit.diamond;
			} else {
				throw new AssertionError();
			}
		}
	}


	private int process_line(String line)
	{
		line = line.trim();
		
		println(line);
		
		String [] words = line.split(" ");
		if (words.length != 10) 
			throw new AssertionError();

		Card p1[] = new Card[5];
		Card p2[] = new Card[5];
		
		for(int i = 0 ; i < 10; i++) 
		{
			String word = words[i];
			Card card = new Card(word);
			if (i < 5) p1[i] = card;
			else p2[i-5] = card;
		}
		
		sort_hand(p1);
		sort_hand(p2);
		
		print_hand(p1);
		print_hand(p2);
		
		Score s1 = evaluate(p1);
		Score s2 = evaluate(p2);
		
		int p1win = 1;
		int p2win = 0;
		
		for(int k = 1; k <= 7; k++)
		{
			if (s1.r[k] < s2.r[k]) {
				return p2win;
			} else if (s1.r[k] > s2.r[k]) {
				return p1win;
			}
		}
		
		throw new AssertionError();
	}

	public static void sort_hand(Card[] p) {
		Arrays.sort(p, new Comp1());
	}
	
	public static class Comp1 implements Comparator<Card>
	{
		@Override public int compare(Card o1, Card o2) {
			int index1 = o1.v * 4 + o1.suit.val();
			int index2 = o2.v * 4 + o2.suit.val();
			return Integer.compare(index1, index2);
		}
	}

	public static Score evaluate(Card[] hand)
	{	
		Score score = new Score();
		
		if (is_royal_flush(score, hand)) {
		} else if (is_straight_flush(score, hand)) {
		} else if (four_of_a_kind(score, hand)) {
		} else if (full_house(score, hand)) {
		} else if (is_flush(score, hand)) {
		} else if (is_straight(score, hand)) {
		} else if (three_of_a_kind(score, hand)) {
		} else if (two_pair(score, hand)) {
		} else if (one_pair(score, hand)) {
		} else  {
			score.r[1] = 0;
			score.r[2] = hand[4].v;
			score.r[3] = hand[3].v;
			score.r[4] = hand[2].v;
			score.r[5] = hand[1].v;
			score.r[6] = hand[0].v;
		}  

		return score;
	}
	
	private static boolean two_pair(Score score, Card[] h) 
	{
		if (h[0].v == h[1].v && h[2].v == h[3].v) {
			score.r[1] = 10-7;
			score.r[2] = h[2].v;
			score.r[3] = h[0].v;
			score.r[4] = h[4].v;
			return true;
		}
		
		if (h[0].v == h[1].v && h[3].v == h[4].v) {
			score.r[1] = 10-7;
			score.r[2] = h[3].v;
			score.r[3] = h[0].v;
			score.r[4] = h[2].v;
			return true;
		}
		
		if (h[1].v == h[2].v && h[3].v == h[4].v) {
			score.r[1] = 10-7;
			score.r[2] = h[3].v;
			score.r[3] = h[1].v;
			score.r[4] = h[0].v;
			return true;
		}
		
		return false;
	}

	private static boolean one_pair(Score score, Card[] h) 
	{
		if (h[0].v == h[1].v) {
			score.r[1] = 10-8;
			score.r[2] = h[0].v;
			score.r[3] = h[4].v;
			score.r[4] = h[3].v;
			score.r[5] = h[2].v;
			return true;
		}
		
		if (h[1].v == h[2].v) {
			score.r[1] = 10-8;
			score.r[2] = h[1].v;
			score.r[3] = h[4].v;
			score.r[4] = h[3].v;
			score.r[5] = h[0].v;
			return true;
		}
		
		if (h[2].v == h[3].v) {
			score.r[1] = 10-8;
			score.r[2] = h[2].v;
			score.r[3] = h[4].v;
			score.r[4] = h[1].v;
			score.r[5] = h[0].v;
			return true;
		}
		
		if (h[3].v == h[4].v) {
			score.r[1] = 10-8;
			score.r[2] = h[3].v;
			score.r[3] = h[2].v;
			score.r[4] = h[1].v;
			score.r[5] = h[0].v;
			return true;
		}
		
		return false;
	}

	/**
	 * All cards are consecutive values.
	 */
	private static boolean is_straight(Score score, Card[] hand)
	{		
		int val0 = hand[0].v;	
		
		if (hand[1].v != val0 + 1) return false;
		if (hand[2].v != val0 + 2) return false;
		if (hand[3].v != val0 + 3) return false;
		if (hand[4].v != val0 + 4) return false;
		
		score.r[1] = 10-5;
		
		score.r[2] = hand[4].v;		
		
		return true;
	}

	private static boolean four_of_a_kind(Score score, Card[] h)
	{
		if (h[0].v == h[1].v && h[0].v == h[2].v && h[0].v == h[3].v) {
			score.r[1] = 10-2;
			score.r[2] = h[0].v;
			return true;
		}
		
		if (h[1].v == h[2].v && h[1].v == h[3].v && h[1].v == h[4].v) {
			score.r[1] = 10-2;
			score.r[2] = h[1].v;
			return true;
		}
		
		return false;
	}
	
	private static boolean three_of_a_kind(Score score, Card[] h) 
	{
		if (h[0].v == h[1].v && h[0].v == h[2].v) {
			score.r[1] = 10-6;
			score.r[2] = h[2].v;
			score.r[3] = h[4].v;
			return true;
		}
		
		if (h[1].v == h[2].v && h[1].v == h[3].v) {
			score.r[1] = 10-6;
			score.r[2] = h[2].v;
			score.r[3] = h[4].v;
			return true;
		}
		
		if (h[2].v == h[3].v && h[2].v == h[4].v) {
			score.r[1] = 10-6;
			score.r[2] = h[2].v;
			score.r[3] = h[1].v;
			return true;
		}
		
		return false;
	}

	private static boolean full_house(Score score, Card[] hand)
	{
		//three_of_a_kind_and_a_pair
		if (hand[0].v == hand[1].v && hand[0].v == hand[2].v && hand[3].v == hand[4].v) {
			score.r[1] = 10-3;
			score.r[2] = hand[0].v;
			score.r[3] = hand[3].v;
			return true;
		}
		
		if (hand[0].v == hand[1].v && hand[2].v == hand[3].v && hand[2].v == hand[4].v) {
			score.r[1] = 10-3;
			score.r[2] = hand[2].v; 
			score.r[3] = hand[0].v; 
			return true;
		}
		
		return false;
	}

	public void print_hand(Card[] hand)
	{
		if (hand.length != 5)
		{
			throw new AssertionError();
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0 ; i < 5; i++)
		{
			if (i > 0) 
			{
				sb.append(" ");
			}

			sb.append(hand[i].v);
			
			//sb.append(" ");
			//sb.append(hand[i].suit.val());
		}
		
		println(sb.toString());		
	}

	/**
	 * note: returns true for a royal flush too
	 * @param hand
	 * @return
	 */
	public static boolean is_straight_flush(Score score, Card[] hand)
	{		
		if (!is_flush(score, hand))
		{
			return false;
		}

		// note: score.primary changed 

		if (!is_straight(score, hand)) 
		{
			return false;
		}

		// note: score.primary changed
		
		score.r[1] = 10-1;
		score.r[2] = hand[4].v; // note: already set by is_straight
		
		return true;
	}

	public static boolean is_royal_flush(Score score, Card[] hand)
	{		
		if (!is_flush(score, hand)) 
		{
			return false;	
		}

		if (hand[0].v != 10) return false;
		if (hand[1].v != 11) return false;
		if (hand[2].v != 12) return false;
		if (hand[3].v != 13) return false;
		if (hand[4].v != 14) return false;
		
		score.r[1] = 10;
		
		return true;
	}

	//all cards of the same suit
	public static boolean is_flush(Score score, Card[] hand)
	{		
		Suit hand0suit = hand[0].suit;
		
		for(int i = 1; i < hand.length; i++) 
		{
			if (hand0suit != hand[i].suit) 
			{
				return false;
			}
		}
		
		score.r[1] = 10-4;
		
		//TODO how to compare from here? should we check for three pair and so on?.. or just highest ranking card?.. 
		//instructions tell us just to use the highest ranking card 
		score.r[2] = hand[4].v;
		
		return true;
	}

	public static boolean containsVal(Card[] hand, int val)
	{
		if (val < 2 || val > 14)
		{
			throw new AssertionError();
		}
		
		for(Card c: hand) 
		{
			if (c.v == val) 
			{
				return true;
			}
		}
		
		return false;
	}

	//private static final String dataFn = "bin/data/p054_poker.txt";
	private static final String FILENAME = Paths.get(Constants.DATA_DIR, "p054_poker.txt").toString();
	private static final String regex_split_lines = "\\r\\n|\\n|\\r";
}
