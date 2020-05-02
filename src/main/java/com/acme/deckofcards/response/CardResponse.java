package com.acme.deckofcards.response;

import java.util.UUID;

import org.springframework.hateoas.RepresentationModel;

import com.acme.deckofcards.beans.Card;
import com.acme.deckofcards.beans.Rank;
import com.acme.deckofcards.beans.Suit;

// TODO: do we need to extend this class?
public class CardResponse extends RepresentationModel<CardResponse> {
	private final Rank rank;
	private final Suit suit;
	private final UUID deckId;
	
	public CardResponse (Card card, UUID deckId) {
		rank = card.getRank();
		suit = card.getSuit();
		this.deckId = deckId;
	}

	public Rank getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public UUID getDeckId() {
		return deckId;
	}

}
