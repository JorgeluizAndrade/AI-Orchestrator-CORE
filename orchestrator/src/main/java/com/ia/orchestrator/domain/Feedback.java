package com.ia.orchestrator.domain;



public class Feedback {
    Long id;
    String message;
    Float score;
    Sentiment sentiment;
    Category category;
    Metrics metrics;


    public Feedback() {
    }
    public Feedback(Long id, String message,  Float score) {
        this.id = id;
        this.message = message;
        this.score = score;
    }
}
