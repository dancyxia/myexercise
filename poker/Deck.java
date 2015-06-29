/*
 * Card.java
 * 
 * Copyright 2015 dancy <dancy@DANCY-HOME>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * 
 */

//~ 8.1 Design the data structures for a generic deccmdk of cards. Explain how you would
//~ subclass the data structures to implement blackjack.
package poker;

import poker.Card;
//NOTE:
//pay attention to 
//1. setting value by this.value = value
//2. if generic type extends a class, this class must be declared independently(i.e, can't be a inner class)
//3. if enum is declared as an independent public enum, it must be in an independent java file, unless it's declared as public inner enum.
import java.util.List;
import java.util.LinkedList;
import java.util.Calendar;
import java.util.Random;

public class Deck <T extends Card> {

	T[] cards;
	Hand[] hands;

	public Deck(T[] cards) {
		this.cards = cards;
		hands = new Hand[4];
		for (int i = 0; i < hands.length; i++)
			hands[i] = new Hand();
	}
	
	public void shuffle() {
		Random rand = new Random(Calendar.getInstance().getTimeInMillis());
		for (int i = 0; i < cards.length; i++) {
			int j = rand.nextInt(cards.length-i);
			swap(cards.length-i-1, j);
		}
	}
	
	private void swap(int a, int b) {
		T t = cards[a];
		cards[a] = cards[b];
		cards[b] = t;
	}
	
	public Hand[] deal() {
		shuffle();
		int rem = cards.length;
		
		while (rem > 0) {
			for (int i = 0; i < hands.length; i++) {
				hands[i].add(cards[--rem]);
			}
		}
		
		for (int i = 0; i < hands.length; i++) {
			System.out.println(hands[i]);
		}
		
		return hands;
	}

	public enum Suit {
		SPADE(0, "spade"), HEART(1, "heart"), DIAMOND(2, "diamond"), CLUB(3, "club");
		private int index;
		private String alias;
		
		private Suit(int index, String alias) {
			this.index = index;
			this.alias = alias;
		}
		
		public String getAlias() {
			return alias;
		}
		
		public int getIndex() {
			return index;
		}
		
		public int getGlobalValue(int suitValue) {
			return index*13+suitValue;
		}

		public static Suit getSuitFromValue(int value) {
				switch(value/13) {
					case 0: 
						return Suit.SPADE;
					case 1:
						return Suit.HEART;
					case 2:
						return Suit.DIAMOND;
					case 3:
						return Suit.CLUB;
					default:
						return null;
				}
		}
	}

	private class Hand <T extends Card> {
		private List<T> cards;
		
		public Hand() {
			cards = new LinkedList<T>();
		}
		
		public List<T> getCards() {
			return cards;
		}
		
		public void discard(T card) {
			cards.remove(card);
		}
		
		public void add(T card) {
			cards.add(card);
		}
		
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (Card card : cards) {
				sb.append(card).append(" ");;
			}
			sb.append("\n");
			return sb.toString();
		}
	}
	
	public static void main (String args[]) {
		Card[] cards = new Card[52];
		for (int i = 0; i < 52; i++) {
			cards[i] = new Card(Suit.getSuitFromValue(i), i%13);
		}
		
		Deck deck = new Deck(cards);
		deck.deal();
	}
}

