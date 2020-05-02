package com.acme.deckofcards.response;

import org.springframework.hateoas.RepresentationModel;

public class MessageResponse extends RepresentationModel<MessageResponse>{
	
	private final String message;
	
	public MessageResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
