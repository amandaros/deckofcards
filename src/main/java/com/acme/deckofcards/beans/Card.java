package com.acme.deckofcards.beans;

import java.util.Objects;

public class Card {
	private Suit suit;
	private Rank rank;

	public Suit getSuit() {
		return suit;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}
	
	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}
	
	@Override
	public boolean equals(Object obj) {	
		if (obj == this) {
			return true;
		}
		
		
		if (!(obj instanceof Card)) {
			return false;
		}
		
		Card card = (Card) obj;
		
		return suit.equals(card.getSuit()) 
				&& rank.equals(card.getRank());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(rank, suit);
	}

}
