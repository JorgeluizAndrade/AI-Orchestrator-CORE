package com.ia.orchestrator.core.cognitive;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ia.orchestrator.application.IProcessTextAnalysisUseCase;

@RestController
@RequestMapping("/v1/text-analyses")
public class CognitiveController {
	
	
	private final IProcessTextAnalysisUseCase processTextAnalysisUseCase;
	
	
	public CognitiveController(IProcessTextAnalysisUseCase processTextAnalysisUseCase) {
		// TODO Auto-generated constructor stub
		this.processTextAnalysisUseCase = processTextAnalysisUseCase;
	}

}
