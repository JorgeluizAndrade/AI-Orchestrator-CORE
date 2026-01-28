package com.ia.orchestrator.application.impl;

import com.ia.orchestrator.application.ICreateTextAnalysisUseCase;
import com.ia.orchestrator.domain.TextAnalysis;
import com.ia.orchestrator.domain.repositories.TextAnalysisRepository;

public class CreateTextAnalysisUseCase implements ICreateTextAnalysisUseCase{
	
	private final TextAnalysisRepository analysisRepository;
	
	public CreateTextAnalysisUseCase(TextAnalysisRepository analysisRepository) {
		// TODO Auto-generated constructor stub
		this.analysisRepository = analysisRepository;
	}

	@Override
	public TextAnalysis create(TextAnalysis data) {
		// TODO Auto-generated method stub
		
		return analysisRepository.save(data); // For a while...
		
	}

}
