package com.ia.orchestrator.core.dtos;

import com.ia.orchestrator.domain.objects.Category;
import com.ia.orchestrator.domain.objects.Sentiment;

public record TextAnalysisRequestDTO(
        String rawData,
        String normalizedText,
        String analyzedData,
        float confidence,
        Category category,
        Sentiment sentiment
) {}
