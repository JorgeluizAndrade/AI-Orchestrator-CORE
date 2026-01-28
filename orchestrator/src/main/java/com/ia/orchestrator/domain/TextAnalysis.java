package com.ia.orchestrator.domain;

import java.time.Instant;

import com.ia.orchestrator.domain.objects.Category;
import com.ia.orchestrator.domain.objects.Sentiment;

public class TextAnalysis {
    Long id;
    String rawData;
    String normalizedText;
    Float score;
    Sentiment sentiment;
    Category category;
    Instant creaated_at;


    public TextAnalysis() {
    }
    
    
    public TextAnalysis(Long id, String rawData, String normalizedText, Float score, Category category, Sentiment sentiment, Instant created_at) {
 
    	if(rawData.isBlank() || rawData.isEmpty() || rawData.trim() == "") {
    		return;
    	}
    	
    	
    	if(normalizedText.isBlank() || normalizedText.isEmpty() || normalizedText.trim() == "") {
    		return;
    	}
    	
    	
    	if(score < 0) {
    		return;
    	}
    	
    	this.id = id;
        this.rawData = rawData;
        this.normalizedText = normalizedText;
        this.score = score;
        this.category =  category;
        this.sentiment = sentiment;
        this.creaated_at = created_at;
    }
    
 
}
