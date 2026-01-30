package com.ia.orchestrator.infrastructure.database.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ITextAnalysisSpringJpa extends JpaRepository<TextAnalysisEntity, Long> {

}
