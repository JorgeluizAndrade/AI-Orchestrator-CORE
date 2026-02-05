package com.ia.orchestrator.infrastructure.dto;

import com.ia.orchestrator.domain.objects.Category;
import com.ia.orchestrator.domain.objects.Sentiment;


public record AiResponseDTO(String analyzedData, Sentiment sentiment, Category category, float confidence) {	
}
