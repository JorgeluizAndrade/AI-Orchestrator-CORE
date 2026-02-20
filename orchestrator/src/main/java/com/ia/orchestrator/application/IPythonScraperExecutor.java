package com.ia.orchestrator.application;

import com.ia.orchestrator.infrastructure.dto.ScrapingResponseDTO;

public interface IPythonScraperExecutor {
    public ScrapingResponseDTO run(String asin, String url);
}