package com.ia.orchestrator.infrastructure.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;
import com.ia.orchestrator.infrastructure.dto.AiResponseDTO;

@Component
public class Ai implements IAi {

    private final ChatClient chatClient;

    private static final String SYSTEM_PROMPT = """
        You are a sentiment analysis assistant. Analyze the given text and return a JSON response.
        
        Category: PRODUCT, SERVICE, SUPPORT, OTHER
        Sentiment: POSITIVE, NEUTRAL, NEGATIVE
        
        Rules:
        - analyzedData: objective semantic analysis in English
        - category: must be exactly one of: PRODUCT, SERVICE, SUPPORT, OTHER
        - sentiment: must be exactly one of: POSITIVE, NEUTRAL, NEGATIVE
        - confidence: a number between 0.0 and 1.0
        
        CRITICAL: Return ONLY valid JSON without markdown code blocks, backticks, or explanations.
        """;

    private static final String USER_PROMPT_TEMPLATE = """
        Analyze this customer feedback: "%s"
        
        Return ONLY this exact JSON structure (no ```json, no markdown):
        {
          "analyzedData": "your analysis here",
          "category": "PRODUCT",
          "sentiment": "NEUTRAL",
          "confidence": 0.85
        }
        """;

    public Ai(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @Override
    public AiResponseDTO aiClient(String rawText) {
        try {
            String userPrompt = String.format(USER_PROMPT_TEMPLATE, 
                rawText.replace("\"", "\\\""));
            
            System.out.println("Sending to AI...");
            System.out.println("Prompt: " + userPrompt);

            AiResponseDTO res = this.chatClient
                .prompt()
                .system(SYSTEM_PROMPT)
                .user(userPrompt)
                .call()
                .entity(AiResponseDTO.class);
            
            System.out.println("RESRERSRESRE: " + res);

            if (res == null) {
                throw new IllegalStateException("AI returned null response");
            }

            // Validações
            validateResponse(res);

            System.out.println("RESPONSE IA: " + res);
            System.out.println("Confidence: " + res.confidence());
            System.out.println("Sentiment: " + res.sentiment());
            System.out.println("Category: " + res.category());

            return res;

        } catch (Exception e) {
            System.err.println("Error calling IA: " + e.getMessage());
            e.printStackTrace();
            throw new IllegalStateException("AI response could not be parsed: " + e.getMessage(), e);
        }
    }

    private void validateResponse(AiResponseDTO res) {
        if (res.analyzedData() == null || res.analyzedData().isBlank()) {
            throw new IllegalStateException("analyzedData is missing");
        }
        
//        if (res.category() == null || 
//            !java.util.List.of("PRODUCT", "SERVICE", "SUPPORT", "OTHER").contains(res.category())) {
//            throw new IllegalStateException("Invalid category: " + res.category());
//        }
//        if (res.sentiment() == null || 
//            !java.util.List.of("POSITIVE", "NEUTRAL", "NEGATIVE").contains(res.sentiment())) {
//            throw new IllegalStateException("Invalid sentiment: " + res.sentiment());
//        }
        
        if (res.confidence() < 0.0 || res.confidence() > 1.0) {
            throw new IllegalStateException("Invalid confidence: " + res.confidence());
        }
    }
}