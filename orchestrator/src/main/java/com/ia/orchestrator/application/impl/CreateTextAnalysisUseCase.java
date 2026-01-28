package com.ia.orchestrator.application.impl;

import com.ia.orchestrator.application.ICreateTextAnalysisUseCase;
import com.ia.orchestrator.core.dtos.TextAnalysisRequestDTO;
import com.ia.orchestrator.core.dtos.TextAnalysisResponseDTO;
import com.ia.orchestrator.core.mapper.ITextAnalysisMapper;
import com.ia.orchestrator.domain.TextAnalysis;
import com.ia.orchestrator.domain.repositories.ITextAnalysisRepository;

public class CreateTextAnalysisUseCase implements ICreateTextAnalysisUseCase{
	
	private final ITextAnalysisRepository analysisRepository;
	private final ITextAnalysisMapper analysisMapper;

	
	public CreateTextAnalysisUseCase(ITextAnalysisRepository analysisRepository, ITextAnalysisMapper analysisMapper) {
		// TODO Auto-generated constructor stub
		this.analysisRepository = analysisRepository;
		this.analysisMapper = analysisMapper;
	}

	@Override
	public TextAnalysisResponseDTO create(TextAnalysisRequestDTO data) {
		// TODO Auto-generated method stub
		
		TextAnalysis textAnalysis = analysisMapper.toDomain(data);
		
		
		TextAnalysis createdTextAnalysis = analysisRepository.save(textAnalysis);
		
	
		return  analysisMapper.toResponse(createdTextAnalysis); 		
	}

}
