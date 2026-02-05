package com.ia.orchestrator.infrastructure.database.jpa;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.ia.orchestrator.domain.objects.Category;
import com.ia.orchestrator.domain.objects.Sentiment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity(name = "TextAnalysis")
@AllArgsConstructor
@Data
public class TextAnalysisEntity {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	  	Long id;
	    
	    @Column(name = "raw_data")
	    String rawData;
	    
	    @Column(name = "normalized_text")
	    String normalizedText; 
	    
	    @Column(name = "analyzed_data")
	    String analyzedData;
	    
	    @Column(name = "confidence")
	    Float confidence;
	    
	    @Column(name = "sentiment")
	    @Enumerated(EnumType.STRING)
	    Sentiment sentiment;
	    
	    @Column(name = "category")
	    Category category;
	    
	    
	    @Column(name = "created_at", nullable = false, updatable = false)
	    @CreationTimestamp
	    Instant created_at;
	
	
}
