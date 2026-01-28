package com.ia.orchestrator.domain.repositories;

import com.ia.orchestrator.domain.TextAnalysis;

public interface ITextAnalysisRepository {
	TextAnalysis save(TextAnalysis analysis);
}