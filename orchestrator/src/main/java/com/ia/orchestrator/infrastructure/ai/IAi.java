package com.ia.orchestrator.infrastructure.ai;

import com.ia.orchestrator.core.dtos.AiResponseDTO;

public interface IAi {
	public AiResponseDTO aiClient(String rawText);   	
}
