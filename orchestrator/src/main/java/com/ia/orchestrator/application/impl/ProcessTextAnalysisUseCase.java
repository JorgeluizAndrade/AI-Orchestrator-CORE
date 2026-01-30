package com.ia.orchestrator.application.impl;

import java.util.Locale;

import org.springframework.stereotype.Service;

import com.ia.orchestrator.application.IProcessTextAnalysisUseCase;
import com.ia.orchestrator.core.dtos.AiResponseDTO;
import com.ia.orchestrator.core.dtos.TextAnalysisRequestDTO;
import com.ia.orchestrator.core.dtos.TextAnalysisResponseDTO;
import com.ia.orchestrator.core.mapper.ITextAnalysisMapper;
import com.ia.orchestrator.domain.TextAnalysis;
import com.ia.orchestrator.domain.repositories.ITextAnalysisRepository;
import com.ia.orchestrator.infrastructure.ai.IAi;

@Service
public class ProcessTextAnalysisUseCase implements IProcessTextAnalysisUseCase {

	private final IAi ai;
	private final ITextAnalysisMapper analysisMapper;
    private final ITextAnalysisRepository repository;


	

	public ProcessTextAnalysisUseCase(IAi ai, ITextAnalysisMapper analysisMapper, ITextAnalysisRepository repository) {
		// TODO Auto-generated constructor stub
		this.ai = ai;
		this.analysisMapper = analysisMapper;
		this.repository = repository;

	}

	@Override
	public TextAnalysisResponseDTO processAnalysis(TextAnalysisRequestDTO analysisRequestDTO) {
		
		String raw = analysisRequestDTO.rawData();
				
		if (raw != null && !raw.isBlank()) {
    		throw new IllegalArgumentException("raw data not be empty or blanck!");

		}
		
		String normalizedText = raw.trim().toLowerCase(Locale.ROOT).replaceAll("\\s+", " ");		
	

		//  Sentiment, Category, Analyzed Data and Confidence
		AiResponseDTO aiResponse = ai.aiClient(normalizedText);
		
		
		String analyzedData = aiResponse.analyzedData();
		Float confidence = aiResponse.confidence();

		
		
		
		TextAnalysis analysis = TextAnalysis.create(normalizedText, analyzedData, 
				aiResponse.sentiment(), aiResponse.category(), confidence);
		
		
		TextAnalysis persisted = repository.save(analysis);
		
		
		return analysisMapper.toResponse(persisted);

	}
	

}
