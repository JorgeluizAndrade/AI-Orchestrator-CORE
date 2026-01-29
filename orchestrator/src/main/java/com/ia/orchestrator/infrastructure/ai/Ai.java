package com.ia.orchestrator.infrastructure.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

import com.ia.orchestrator.core.dtos.AiResponseDTO;


@Component
public class Ai implements IAi {
	
	private final String prompt = "Você é um sistema de interpretação semântica determinística.\n"
			+ "Não seja criativo. Não adicione informações que não estejam implícitas no texto.\n"
			+ "Não explique suas decisões.\n"
			+ "\n"
			+ "Tarefa:\n"
			+ "Analise o texto fornecido e retorne APENAS um objeto JSON válido,\n"
			+ "seguindo exatamente a estrutura definida abaixo.\n"
			+ "\n"
			+ "Regras obrigatórias:\n"
			+ "- Não inclua texto fora do JSON.\n"
			+ "- Não inclua comentários.\n"
			+ "- Não inclua markdown.\n"
			+ "- Não invente categorias ou sentimentos.\n"
			+ "- Se houver ambiguidade, escolha NEUTRAL e reduza o confidence.\n"
			+ "- confidence deve ser um número entre 0.0 e 1.0.\n"
			+ "- normalizedText deve tornar explícito o significado implícito do texto, sem adicionar fatos.\n"
			+ "\n"
			+ "Sentiment permitido:\n"
			+ "- POSITIVE\n"
			+ "- NEUTRAL\n"
			+ "- NEGATIVE\n"
			+ "\n"
			+ "Category permitida:\n"
			+ "- PRODUCT\n"
			+ "- SERVICE\n"
			+ "- SUPPORT\n"
			+ "- OTHER\n"
			+ "\n"
			+ "Estrutura EXATA do retorno:\n"
			+ "\n"
			+ "{\n"
			+ "  \"rawData\": \"<texto original>\",\n"
			+ "  \"normalizedText\": \"<interpretação semântica objetiva do texto>\",\n"
			+ "  \"sentiment\": \"<POSITIVE | NEUTRAL | NEGATIVE>\",\n"
			+ "  \"category\": \"<PRODUCT | SERVICE | SUPPORT | OTHER>\",\n"
			+ "  \"confidence\": <número entre 0.0 e 1.0>\n"
			+ "}\n"
			+ ""; 
	
	private final ChatClient chatClient;
	
	public Ai(ChatClient.Builder chatClientBuilder) {
		// TODO Auto-generated constructor stub
		this.chatClient = chatClientBuilder.build();
	}


	@Override
	public  AiResponseDTO aiClient(String rawText) {		
		return this.chatClient.prompt(prompt)
	            .user(rawText)
	            .call()
	            .entity(AiResponseDTO.class);
		

	}

}
