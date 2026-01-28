package com.ia.orchestrator.application;

import com.ia.orchestrator.core.dtos.TextAnalysisRequestDTO;
import com.ia.orchestrator.core.dtos.TextAnalysisResponseDTO;
import com.ia.orchestrator.domain.TextAnalysis;

public interface ICreateTextAnalysisUseCase {
	public TextAnalysisResponseDTO create(TextAnalysisRequestDTO data);
}
