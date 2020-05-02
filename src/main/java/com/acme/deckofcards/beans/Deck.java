package com.acme.deckofcards.beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Deck {
	private UUID id;
	private List<Card> cards;
	private List<Card> removedCards;
	
	public Deck( ) {
		this.setRemovedCards(new ArrayList<Card>());
		this.cards = new ArrayList<Card>();
		
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				Card card = new Card(suit, rank);
				cards.add(card);
			}
		}
	}
	
	public Card drawCard() {
		Card card = null; 
		
		if(!cards.isEmpty()) {
			card = cards.remove(0);
			removedCards.add(card);
		}
		
		return card;
	}
	
	public boolean discard(Card card) {
		boolean success = false; 
		
		if(card != null && removedCards.contains(card)) {
			cards.add(cards.size(), card);
			removedCards.remove(card);
			success = true;
		}
		
		return success;
	}
	
	public void shuffle() {
		Collections.shuffle(this.cards);
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public List<Card> getRemovedCards() {
		return removedCards;
	}

	public void setRemovedCards(List<Card> removedCards) {
		this.removedCards = removedCards;
	}

}
