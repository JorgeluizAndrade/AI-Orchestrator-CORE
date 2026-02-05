package com.ia.orchestrator.application.impl;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.ia.orchestrator.application.IProcessTextAnalysisUseCase;
import com.ia.orchestrator.core.dtos.TextAnalysisResponseDTO;
import com.ia.orchestrator.core.mapper.ITextAnalysisMapper;
import com.ia.orchestrator.domain.TextAnalysis;
import com.ia.orchestrator.domain.repositories.ITextAnalysisRepository;
import com.ia.orchestrator.infrastructure.ai.IAi;
import com.ia.orchestrator.infrastructure.dto.AiResponseDTO;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

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
	public TextAnalysisResponseDTO processAnalysis(String rawText) {
		
		System.out.println("RWAWWWW: "  +  rawText);

		if (rawText == null || rawText.isBlank()) {
			throw new IllegalArgumentException("raw data not be empty or blanck!");
		}

	
		JsonNode node = new ObjectMapper().readTree(rawText);

		String rawTextString = node.get("rawText").asString();
		
		System.out.println("CLEAN RWAWWWW: " + rawTextString);
		
		String normalizedText = rawTextString
			    .toLowerCase(Locale.ROOT)
			    .replace("’", "'")                 // aspas tipográficas
			    .replaceAll("[^a-z0-9\\s']", " ")  // remove pontuação
			    .replaceAll("\\s+", " ")           // colapsa espaços
			    .trim();

		
		

		// Sentiment, Category, Analyzed Data and Confidence
		AiResponseDTO aiResponse = ai.aiClient(normalizedText);

		String analyzedData = aiResponse.analyzedData();
		float confidence = aiResponse.confidence();

		System.out.println("Confidence IA: " + confidence);

		TextAnalysis analysis = TextAnalysis.create(rawText, normalizedText, analyzedData, aiResponse.sentiment(),
				aiResponse.category(), confidence);

		TextAnalysis persisted = repository.save(analysis);

		return analysisMapper.toResponse(persisted);

	}

}
