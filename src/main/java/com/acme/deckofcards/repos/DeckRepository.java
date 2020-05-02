package com.acme.deckofcards.repos;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.acme.deckofcards.beans.Deck;

@Repository
public class DeckRepository {
	
	private List<Deck> decks = Collections.synchronizedList(new ArrayList<Deck>());
	
	public Optional<Deck> findById(UUID id) {
		return decks.stream().filter(deck -> deck.getId().equals(id)).findFirst();
	}
	
	public Deck create(Deck deck) {
		decks.add(deck);
		deck.setId(UUID.randomUUID());
		
		return deck;
	}
	
	public boolean delete(UUID id) {
		return decks.removeIf(deck -> deck.getId().equals(id));
	}
	
	public boolean update(UUID id, Deck updatedDeck) {
		boolean success = false;
		
		if(updatedDeck != null) {
			Optional<Deck> deck = findById(id);
			deck.ifPresent(existingDeck -> updateIfPresent(existingDeck, updatedDeck));
			success = deck.isPresent();
		}
		
		return success;
	}
	
	private void updateIfPresent(Deck existingDeck, Deck updatedDeck) {
		existingDeck.setCards(updatedDeck.getCards());
		existingDeck.setRemovedCards(updatedDeck.getRemovedCards());
	}

}
