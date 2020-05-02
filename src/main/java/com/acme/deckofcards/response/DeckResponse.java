package com.acme.deckofcards.response;

import java.util.UUID;

import org.springframework.hateoas.RepresentationModel;

import com.acme.deckofcards.beans.Deck;

//TODO: do we need to extend this class?
public class DeckResponse extends RepresentationModel<DeckResponse> {
	private final UUID id;
	
	public DeckResponse(Deck deck) {
		id = deck.getId();
	}

	public UUID getId() {
		return id;
	}

}
