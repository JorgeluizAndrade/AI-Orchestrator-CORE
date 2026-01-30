package com.ia.orchestrator.infrastructure.mapper;

import com.ia.orchestrator.domain.TextAnalysis;
import com.ia.orchestrator.infrastructure.database.jpa.TextAnalysisEntity;

public interface ITextAnalysisPersistenceMapper {
	TextAnalysisEntity toEntity(TextAnalysis textAnalysis);
	
	TextAnalysis toDomain(TextAnalysisEntity textAnalysis);

}
