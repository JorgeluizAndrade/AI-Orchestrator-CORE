package com.ia.orchestrator.core.cognitive;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ia.orchestrator.application.IProcessTextAnalysisUseCase;
import com.ia.orchestrator.core.dtos.TextAnalysisResponseDTO;

@RestController
@RequestMapping("/v1/text-analyses")
public class CognitiveController {

	private final IProcessTextAnalysisUseCase processTextAnalysisUseCase;

	public CognitiveController(IProcessTextAnalysisUseCase processTextAnalysisUseCase) {
		// TODO Auto-generated constructor stub
		this.processTextAnalysisUseCase = processTextAnalysisUseCase;
	}

	
	@PostMapping
	public TextAnalysisResponseDTO analysis(@RequestBody String rawText) {
		return this.processTextAnalysisUseCase.processAnalysis(rawText);
	}

}
