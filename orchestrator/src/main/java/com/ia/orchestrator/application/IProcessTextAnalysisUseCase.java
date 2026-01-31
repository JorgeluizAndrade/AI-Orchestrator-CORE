package com.ia.orchestrator.application;

import com.ia.orchestrator.core.dtos.TextAnalysisResponseDTO;

public interface IProcessTextAnalysisUseCase {
	public TextAnalysisResponseDTO processAnalysis(String raw);
}
