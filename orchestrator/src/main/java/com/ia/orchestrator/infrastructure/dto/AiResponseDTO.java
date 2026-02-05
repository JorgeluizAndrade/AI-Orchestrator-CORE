package com.ia.orchestrator.core.dtos;

import com.ia.orchestrator.domain.objects.Category;
import com.ia.orchestrator.domain.objects.Sentiment;

public record AiResponseDTO(String analyzedData, Sentiment sentiment, Category category, Float confidence) {
}
