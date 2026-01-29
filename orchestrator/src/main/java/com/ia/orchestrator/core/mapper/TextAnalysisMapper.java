package com.ia.orchestrator.core.mapper;

import org.springframework.stereotype.Component;

import com.ia.orchestrator.core.dtos.TextAnalysisRequestDTO;
import com.ia.orchestrator.core.dtos.TextAnalysisResponseDTO;
import com.ia.orchestrator.domain.TextAnalysis;


@Component
public class TextAnalysisMapper implements ITextAnalysisMapper {

	
	public TextAnalysis toDomain(TextAnalysisRequestDTO data) {
		return new TextAnalysis(
			    data.rawData(),
			    data.normalizedText(),
			    data.analyzedData(),
			    data.confidence(),
			    data.sentiment(),
			    data.category()
			);
		
	
	}
	
	
	public TextAnalysisResponseDTO toResponse(TextAnalysis textAnalysis) {
		return new TextAnalysisResponseDTO(
				textAnalysis.getId(),
				textAnalysis.getRawData(),
				textAnalysis.getNormalizedText(),
				textAnalysis.getAnalyzedData(),
				textAnalysis.getConfidence(),
				textAnalysis.getSentiment(),
				textAnalysis.getCategory(),
				textAnalysis.getCreaated_at()
				);
	}

}
