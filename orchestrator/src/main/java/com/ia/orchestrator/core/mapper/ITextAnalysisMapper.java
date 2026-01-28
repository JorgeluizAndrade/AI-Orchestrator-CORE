package com.ia.orchestrator.core.mapper;

import com.ia.orchestrator.core.dtos.TextAnalysisRequestDTO;
import com.ia.orchestrator.core.dtos.TextAnalysisResponseDTO;
import com.ia.orchestrator.domain.TextAnalysis;

public interface ITextAnalysisMapper {
    TextAnalysis toDomain(TextAnalysisRequestDTO data);
    TextAnalysisResponseDTO toResponse(TextAnalysis entity);
}
