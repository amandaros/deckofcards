package com.acme.deckofcards.controllers;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.acme.deckofcards.beans.Card;
import com.acme.deckofcards.beans.Deck;
import com.acme.deckofcards.repos.DeckRepository;
import com.acme.deckofcards.response.CardResponse;
import com.acme.deckofcards.response.DeckResponse;
import com.acme.deckofcards.response.MessageResponse;

@RestController
@RequestMapping(value = "/decks", produces = "application/json")
public class DeckController {
	
	@Autowired
	private DeckRepository deckRepository;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<DeckResponse> createDeck() {
		Deck deck = deckRepository.create(new Deck());
		
		return new ResponseEntity<>(new DeckResponse(deck), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}/shuffle", method = RequestMethod.PUT)
	public ResponseEntity<?> shuffleDeck(@PathVariable UUID id) {
		ResponseEntity<?> response;
		Optional<Deck> deck = deckRepository.findById(id);
		 
		if(deck.isPresent()) {
			deck.get().shuffle();
			response = new ResponseEntity<>(new DeckResponse(deck.get()), HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return response;
	}
	
	@RequestMapping(value = "/{id}/draw", method = RequestMethod.PUT)
	public ResponseEntity<?> draw(@PathVariable UUID id) {
		ResponseEntity<?> response;
		Optional<Deck> optDeck = deckRepository.findById(id);
		
		if(optDeck.isPresent()) {
			Deck deck = optDeck.get();
			Card card = deck.drawCard();
			
			if(card != null) {
				deckRepository.update(deck.getId(), deck);
				response = new ResponseEntity<>(new CardResponse(card, deck.getId()), HttpStatus.OK);
			} else {
				response = new ResponseEntity<>(new MessageResponse("Deck is empty"), HttpStatus.FORBIDDEN);
			}
		} else {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return response;
	}
	
	@RequestMapping(value = "/{id}/discard", method = RequestMethod.PUT)
	public ResponseEntity<?> discard(@PathVariable UUID id, @RequestBody Card card) {
		ResponseEntity<?> response;
		Optional<Deck> optDeck = deckRepository.findById(id);
			
			if(optDeck.isPresent()) {
				Deck deck = optDeck.get();
				
				if(deck.discard(card)) {
					deckRepository.update(deck.getId(), deck);
					response = new ResponseEntity<>(new DeckResponse(deck), HttpStatus.OK);
				} else {
					response = new ResponseEntity<>(new MessageResponse("Invalid card"), HttpStatus.FORBIDDEN);
				}
			} else {
				response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		
		return response;
	}
	

}
