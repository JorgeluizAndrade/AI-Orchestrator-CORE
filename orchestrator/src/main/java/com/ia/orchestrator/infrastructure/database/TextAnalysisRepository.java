package com.ia.orchestrator.infrastructure.database;

import org.springframework.stereotype.Repository;

import com.ia.orchestrator.core.mapper.ITextAnalysisMapper;
import com.ia.orchestrator.domain.TextAnalysis;
import com.ia.orchestrator.domain.repositories.ITextAnalysisRepository;
import com.ia.orchestrator.infrastructure.database.jpa.ITextAnalysisSpringJpa;
import com.ia.orchestrator.infrastructure.database.jpa.TextAnalysisEntity;
import com.ia.orchestrator.infrastructure.mapper.ITextAnalysisPersistenceMapper;


@Repository
public class TextAnalysisRepository implements ITextAnalysisRepository{ 
	
	private final ITextAnalysisSpringJpa analysisPersistence;
	private final ITextAnalysisPersistenceMapper analysisMapper;
	
	
	public TextAnalysisRepository(ITextAnalysisSpringJpa analysisPersistence, ITextAnalysisPersistenceMapper analysisMapper) {
		this.analysisPersistence = analysisPersistence;
		this.analysisMapper = analysisMapper;
	}

	@Override
	public TextAnalysis save(TextAnalysis analysis) {
		
		  TextAnalysisEntity entity = analysisMapper.toEntity(analysis);

		  TextAnalysisEntity created = analysisPersistence.save(entity);

	       return analysisMapper.toDomain(created);
	}

}
