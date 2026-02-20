package com.ia.orchestrator.application.impl;

import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ia.orchestrator.application.IProcessTextAnalysisUseCase;
import com.ia.orchestrator.application.IPythonScraperExecutor;
import com.ia.orchestrator.core.dtos.TextAnalysisResponseDTO;
import com.ia.orchestrator.core.mapper.ITextAnalysisMapper;
import com.ia.orchestrator.domain.TextAnalysis;
import com.ia.orchestrator.domain.repositories.ITextAnalysisRepository;
import com.ia.orchestrator.infrastructure.ai.IAi;
import com.ia.orchestrator.infrastructure.dto.AiResponseDTO;
import com.ia.orchestrator.infrastructure.dto.ScrapingResponseDTO;


@Service
public class ProcessTextAnalysisUseCase implements IProcessTextAnalysisUseCase {

	private final IAi ai;
	private final ITextAnalysisMapper analysisMapper;
	private final ITextAnalysisRepository repository;
	private final IPythonScraperExecutor scraperExecutor;

	public ProcessTextAnalysisUseCase(IAi ai, ITextAnalysisMapper analysisMapper, ITextAnalysisRepository repository,
			IPythonScraperExecutor scraperExecutor) {
		// TODO Auto-generated constructor stub
		this.ai = ai;
		this.analysisMapper = analysisMapper;
		this.repository = repository;
		this.scraperExecutor = scraperExecutor;

	}

	@Override
	public TextAnalysisResponseDTO processAnalysis(String asin, String url) {

		if (url == null || url.isBlank()) {
			throw new IllegalArgumentException("raw data not be empty or blanck!");
		}

		ScrapingResponseDTO scraping = scraperExecutor.run(asin, url);

		String rawTextString = scraping.getReviews().stream().map(r -> r.getText()).collect(Collectors.joining(" "));

		System.out.println("CLEAN RWAWWWW: " + rawTextString);

		String normalizedText = rawTextString.toLowerCase(Locale.ROOT).replace("’", "'") // aspas tipográficas
				.replaceAll("[^a-z0-9\\s']", " ") // remove pontuação
				.replaceAll("\\s+", " ") // colapsa espaços
				.trim();

		// Sentiment, Category, Analyzed Data and Confidence
		AiResponseDTO aiResponse = ai.aiClient(normalizedText);

		String analyzedData = aiResponse.analyzedData();
		float confidence = aiResponse.confidence();

		System.out.println("Confidence IA: " + confidence);

		TextAnalysis analysis = TextAnalysis.create(rawTextString, normalizedText, analyzedData, aiResponse.sentiment(),
				aiResponse.category(), confidence);

		TextAnalysis persisted = repository.save(analysis);

		return analysisMapper.toResponse(persisted);

	}

}
