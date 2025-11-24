package org.example.chatservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GroqClient {
    private static final Logger log = LoggerFactory.getLogger(GroqClient.class);
    private final RestTemplate rest;
    private final String baseUrl;
    private final String apiKey;
    private final String model;

    public GroqClient(
            @Value("${groq.base-url:https://api.groq.com/openai}") String baseUrl,
            @Value("${groq.api-key}") String apiKey,
            @Value("${groq.model:llama-3.1-8b-instant}") String model
    ) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.model = model;

        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException("GROQ API key no inyectada. Setear GROQ_API_KEY.");
        }

        var factory = new org.springframework.http.client.SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(10_000);
        factory.setReadTimeout(60_000);
        this.rest = new RestTemplate(factory);
        this.rest.setErrorHandler(new DefaultResponseErrorHandler() {
            //@Override
            public void handleError(org.springframework.http.client.ClientHttpResponse response) throws IOException {
                if (response.getStatusCode().isError()) {
                    String body = new String(response.getBody().readAllBytes());
                    throw new HttpClientErrorException(response.getStatusCode(), "Groq error: " + body);
                }
            }
        });
        log.info("GroqClient iniciado. base={}, model={}", baseUrl, model);
    }

    @SuppressWarnings("unchecked")
    public String preguntar(String prompt) {
        String url = baseUrl + "/v1/chat/completions";

        Map<String, Object> body = new HashMap<>();
        body.put("model", model);
        body.put("temperature", 0.3);
        body.put("messages", List.of(Map.of("role", "user", "content", prompt)));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        HttpEntity<Map<String, Object>> req = new HttpEntity<>(body, headers);
        ResponseEntity<Map> resp = rest.exchange(url, HttpMethod.POST, req, Map.class);

        List<?> choices = (List<?>) ((Map<?, ?>) resp.getBody()).get("choices");
        Map<?, ?> choice0 = (Map<?, ?>) choices.get(0);
        Map<?, ?> message = (Map<?, ?>) choice0.get("message");
        return (String) message.get("content");
    }
}
