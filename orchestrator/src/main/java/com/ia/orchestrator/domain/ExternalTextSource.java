package com.ia.orchestrator.domain;


public class ExternalTextSource {

	
	private final String text;
	
	
	public ExternalTextSource(String text) {
		// TODO Auto-generated constructor stub
		
		if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("Rewiew(TEXT) is required!");
        }

		this.text = text;
	}


	public String getText() {
		return text;
	}

}
