package com.ia.orchestrator.core.dtos;

import java.time.Instant;

import com.ia.orchestrator.domain.objects.Category;
import com.ia.orchestrator.domain.objects.Sentiment;

public record TextAnalysisResponseDTO(
        Long id,
        String rawData,
        String normalizedText,
        String analyzedData,
        Float confidence,
        Category category,
        Sentiment sentiment,
        Instant createdAt
) {}
