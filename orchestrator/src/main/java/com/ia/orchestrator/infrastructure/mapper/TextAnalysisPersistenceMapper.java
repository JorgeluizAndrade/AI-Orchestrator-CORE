package com.ia.orchestrator.infrastructure.mapper;

import org.springframework.stereotype.Component;

import com.ia.orchestrator.domain.TextAnalysis;
import com.ia.orchestrator.infrastructure.database.jpa.TextAnalysisEntity;


@Component
public class TextAnalysisPersistenceMapper implements ITextAnalysisPersistenceMapper {

	@Override
	public TextAnalysisEntity toEntity(TextAnalysis textAnalysis) {
		return new TextAnalysisEntity(textAnalysis.getId(), textAnalysis.getRawData(), textAnalysis.getNormalizedText(),
				textAnalysis.getAnalyzedData(), textAnalysis.getConfidence(), textAnalysis.getSentiment(),
				textAnalysis.getCategory(), textAnalysis.getCreaated_at());

	}

	@Override
	public TextAnalysis toDomain(TextAnalysisEntity textAnalysis) {
		return new TextAnalysis(textAnalysis.getId(), textAnalysis.getRawData(), textAnalysis.getNormalizedText(),
				textAnalysis.getConfidence(), textAnalysis.getCategory(), textAnalysis.getSentiment(),
				textAnalysis.getAnalyzedData(), textAnalysis.getCreated_at());

	}

}
