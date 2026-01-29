package com.ia.orchestrator.application;

import com.ia.orchestrator.core.dtos.TextAnalysisRequestDTO;
import com.ia.orchestrator.core.dtos.TextAnalysisResponseDTO;
import com.ia.orchestrator.domain.TextAnalysis;

public interface IProcessTextAnalysisUseCase {
	public TextAnalysisResponseDTO processAnalysis(TextAnalysisRequestDTO analysisRequestDTO);
}
