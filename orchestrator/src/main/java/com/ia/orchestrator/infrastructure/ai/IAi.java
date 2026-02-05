package com.ia.orchestrator.infrastructure.ai;

import com.ia.orchestrator.infrastructure.dto.AiResponseDTO;

public interface IAi {
	public AiResponseDTO aiClient(String rawText);   	
}
