package com.ia.orchestrator.domain;

import java.time.Instant;

import com.ia.orchestrator.domain.objects.Category;
import com.ia.orchestrator.domain.objects.Sentiment;

public class TextAnalysis {
	Long id;
	String rawData;
	String normalizedText; // normalized the data. clean, standardized version, prepared for analysis.
	String analyzedData; // analyzed text by AI
	float confidence;
	Sentiment sentiment;
	Category category;
	Instant created_at;

	public TextAnalysis(String rawData, String normalizedText, String analyzedData, float confidence,
			Sentiment sentiment, Category category) {
		super();
		this.rawData = rawData;
		this.normalizedText = normalizedText;
		this.analyzedData = analyzedData;
		this.confidence = confidence;
		this.sentiment = sentiment;
		this.category = category;
	}

	private TextAnalysis(String raw, String normalizedText, String analyzedData, Sentiment sentiment, Category category,
			float conficence) {
		this.rawData = raw;
		this.normalizedText = normalizedText;
		this.analyzedData = analyzedData;
		this.sentiment = sentiment;
		this.category = category;
		this.confidence = conficence;
	}

	public static TextAnalysis create(String rawData, String normalizedText, String analyzedData, Sentiment sentiment,
			Category category, float confidence

	) {
		if (normalizedText == null || normalizedText.isBlank()) {
			throw new IllegalArgumentException("Normalized text is required");
		}
		if (sentiment == null || category == null) {
			throw new IllegalArgumentException("Sentiment and Category are required");
		}

		if (confidence < 0.0 || confidence > 1.0) {
			throw new IllegalArgumentException("Confidence not be less than 0.0 ot more then 1.0!");
		}

		return new TextAnalysis(rawData, normalizedText, analyzedData, sentiment, category, confidence);
	}

	public TextAnalysis(Long id, String rawData, String normalizedText, float confidence, Category category,
			Sentiment sentiment, String analyzedData, Instant create_at) {

		if (rawData.isBlank() || rawData.trim() == "") {
			throw new IllegalArgumentException("raw data not be empty or blanck!");
		}

		if (normalizedText.isBlank() || normalizedText.trim() == "") {
			throw new IllegalArgumentException("normalized not be empty or blanck!");
		}

		if (analyzedData.isBlank() || analyzedData.trim() == "") {
			throw new IllegalArgumentException("Text(data) not be empty or blanck!");
		}

		this.id = id;
		this.rawData = rawData;
		this.normalizedText = normalizedText;
		this.analyzedData = analyzedData;
		this.confidence = confidence;
		this.category = category;
		this.sentiment = sentiment;
		this.created_at = create_at;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRawData() {
		return rawData;
	}

	public void setRawData(String rawData) {
		this.rawData = rawData;
	}

	public String getNormalizedText() {
		return normalizedText;
	}

	public void setNormalizedText(String normalizedText) {
		this.normalizedText = normalizedText;
	}

	public String getAnalyzedData() {
		return analyzedData;
	}

	public void setAnalyzedData(String analyzedData) {
		this.analyzedData = analyzedData;
	}

	public float getConfidence() {
		return confidence;
	}

	public void setConfidence(float confidence) {
		this.confidence = confidence;
	}

	public Sentiment getSentiment() {
		return sentiment;
	}

	public void setSentiment(Sentiment sentiment) {
		this.sentiment = sentiment;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Instant getCreaated_at() {
		return created_at;
	}

	public void setCreaated_at(Instant created_at) {
		this.created_at = created_at;
	}

}
