package com.ia.orchestrator.application.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ia.orchestrator.application.IPythonScraperExecutor;
import com.ia.orchestrator.domain.ExternalTextSource;
import com.ia.orchestrator.infrastructure.dto.ReviewDTO;
import com.ia.orchestrator.infrastructure.dto.ScrapingResponseDTO;


@Service
public class PythonScraperExecutor implements IPythonScraperExecutor {

    private final ObjectMapper mapper = new ObjectMapper();

	
    @Override
    public ScrapingResponseDTO run(String asin, String url) {

        try {
            ProcessBuilder pb = new ProcessBuilder(
                "python3",
                "./python-scraper/scraping.py",
                "--asin",
                asin,
                "--url",
                url
            );
            
            System.out.println("PWD: " + System.getProperty("user.dir"));


            pb.redirectErrorStream(false);

            Process process = pb.start();

            // stdout → JSON
            String json = new BufferedReader(
                new InputStreamReader(process.getInputStream())
            ).lines().collect(Collectors.joining("\n"));
            
            System.out.println("JSON FROM PYTHON:\n" + json);


            // stderr → logs python
            String err = new BufferedReader(
                new InputStreamReader(process.getErrorStream())
            ).lines().collect(Collectors.joining("\n"));
            
            System.out.println("PY STDERR:\n" + err);


            int exit = process.waitFor();

            if (exit != 0) {
                throw new RuntimeException("Python falhou: " + err);
            }

            return mapper.readValue(json, ScrapingResponseDTO.class);

        } catch (Exception e) {
            throw new RuntimeException("Erro executando scraper", e);
        }
    }
    
    
    public List<ExternalTextSource> toDomain(ScrapingResponseDTO dto) {

        return dto.reviews.stream()
            .map(r -> new ExternalTextSource(r.getText()))
            .toList();
    }


}
