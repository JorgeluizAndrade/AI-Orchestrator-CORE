package com.ia.orchestrator.application;

import com.ia.orchestrator.domain.TextAnalysis;

public interface ICreateTextAnalysisUseCase {
	public TextAnalysis create(TextAnalysis data);
}
