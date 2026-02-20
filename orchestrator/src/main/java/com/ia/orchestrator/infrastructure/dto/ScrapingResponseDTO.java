package com.ia.orchestrator.infrastructure.dto;

import java.util.List;

import com.ia.orchestrator.domain.ExternalTextSource;

import lombok.Data;


@Data
public class ScrapingResponseDTO {
    public List<ReviewDTO> reviews;
    
}
