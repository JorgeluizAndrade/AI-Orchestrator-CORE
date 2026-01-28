package com.ia.orchestrator.domain;

import java.time.Instant;

import com.ia.orchestrator.domain.objects.Category;
import com.ia.orchestrator.domain.objects.Sentiment;

public class TextAnalysis {
    Long id;
    String rawData;
    String normalizedText; // normalized the data. clean, standardized version, prepared for analysis.
    String analyzedData; // analyzed text by AI
    Float confidence;
    Sentiment sentiment;
    Category category;
    Instant creaated_at;


    public TextAnalysis() {
    }
    
    
    public TextAnalysis(Long id, String rawData, String normalizedText, 
    		Float confidence, Category category, Sentiment sentiment, String analyzedData) {
 
    	if(rawData.isBlank() || rawData.trim() == "") {
    		throw new IllegalArgumentException("raw data not be empty or blanck!");
    	}
    	
    	
    	if(normalizedText.isBlank() || normalizedText.trim() == "") {
    		throw new IllegalArgumentException("normalized not be empty or blanck!");
    	}
    	
    	if(analyzedData.isBlank() || analyzedData.trim() == "") {
    		throw new IllegalArgumentException("Text(data) not be empty or blanck!");
    	}
    	
    	
    	if(confidence < 0.0 ||confidence > 1.0) {
    		throw new IllegalArgumentException("Confidence not be less than 0.0 ot more then 1.0!");
    	}
    	
    	this.id = id;
        this.rawData = rawData;
        this.normalizedText = normalizedText;
        this.analyzedData = analyzedData;
        this.confidence = confidence;
        this.category =  category;
        this.sentiment = sentiment;
    }
    
 
}
