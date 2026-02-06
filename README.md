# AI-Orchestrator-CORE

This project is 4FUN, but your complexity is interesting...
So, lets explain it: 

This project is an **AI orchestration backend**, designed to run on modest hardware, focusing on **engineering**, **clean architecture**, and **deep understanding of how AI systems actually work**.

It is **not a chatbot**, **not a prompt playground**, and **not a throwaway experiment**. It is a reusable cognitive core, designed to grow with control.

The central idea is simple, yet powerful:

> AI is not the center of the system. **AI is a pluggable cognitive service**.

>

Everything else—domain, rules, persistence, metrics—remains **classic** software engineering.

--
# Resquet to Backend
```
{
  "rawText": "I’m not fully happy yet, but I feel hopeful because each update has been steadily improving the experience."
}
```

The reponse will be:
```
{
  "id": 0,
  "rawData": "string",
  "normalizedText": "string",
  "analyzedData": "string",
  "confidence": 0,
  "sentiment": "POSITIVE",
  "category": "PRODUCT",
  "createdAt": "2026-02-06T12:21:46.681Z"
}
```

--

## Data Quality & Observability In System too

I see that in real-world scenarios, AI orchestration backends face "noisy" ingestion—caused by client-side retries, network glitches, or stress testing. A robust system must do more than just persist data; it must ensure data integrity.

During the my MVP stress tests, logs revealed a high volume of redundant entries. Instead of blindly trusting the input, I implemented an observability step to diagnose the ingestion health.
<img width="1400" height="1000" alt="Code_Generated_Image" src="https://github.com/user-attachments/assets/41b80ca2-9ffc-4b0e-af50-26f766eff97a" />

See that 60% nearly 60% of the stored records were exact duplicates, caused by an upstream retry loop.
So, the solution for this is: orchestrator/src/main/resources/sql/detete_duplicate.sql
Just remove the duplicate datas.

---


## Comming soon:
- Web Scrapping



