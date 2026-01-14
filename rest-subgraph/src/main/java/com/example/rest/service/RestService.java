package com.example.rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.http.HttpStatusCode;

@Service
public class RestService {

    private static final Logger logger = LoggerFactory.getLogger(RestService.class);

    private final RestClient restClient;

    public RestService() {
        this.restClient = RestClient.builder().build();
    }

    /**
     * Issue a GET request to the provided URL. If the URL contains URI templates, provide uriVariables.
     * Returns the response body deserialized to responseType or throws a RuntimeException on error.
     */
    public <T> T get(String url, Class<T> responseType, Object... uriVariables) {
        if (url == null || url.trim().isEmpty()) {
            throw new IllegalArgumentException("URL cannot be null or empty");
        }

        try {
            logger.info("Calling external service GET {}", url);
            return restClient.get()
                    .uri(url, uriVariables)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                        logger.error("Client error calling {}. Status: {}", url, response.getStatusCode());
                        throw new RuntimeException("Client error: " + response.getStatusCode());
                    })
                    .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                        logger.error("Server error calling {}. Status: {}", url, response.getStatusCode());
                        throw new RuntimeException("Server error: " + response.getStatusCode());
                    })
                    .body(responseType);
        } catch (RestClientException e) {
            logger.error("RestClientException calling {}: {}", url, e.getMessage(), e);
            throw new RuntimeException("Failed to call external service", e);
        } catch (Exception e) {
            logger.error("Unexpected error calling {}: {}", url, e.getMessage(), e);
            throw new RuntimeException("Unexpected error calling external service", e);
        }
    }

    /**
     * Issue a POST request to the provided URL with the given request payload.
     * If the URL contains URI templates, provide uriVariables. Returns the response body
     * deserialized to responseType or throws a RuntimeException on error.
     */
    public <T> T post(String url, Object requestPayload, Class<T> responseType, Object... uriVariables) {
        if (url == null || url.trim().isEmpty()) {
            throw new IllegalArgumentException("URL cannot be null or empty");
        }

        try {
            logger.info("Calling external service POST {}", url);
            return restClient.post()
                    .uri(url, uriVariables)
                    .body(requestPayload)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                        logger.error("Client error calling {}. Status: {}", url, response.getStatusCode());
                        throw new RuntimeException("Client error: " + response.getStatusCode());
                    })
                    .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                        logger.error("Server error calling {}. Status: {}", url, response.getStatusCode());
                        throw new RuntimeException("Server error: " + response.getStatusCode());
                    })
                    .body(responseType);
        } catch (RestClientException e) {
            logger.error("RestClientException calling {}: {}", url, e.getMessage(), e);
            throw new RuntimeException("Failed to call external service", e);
        } catch (Exception e) {
            logger.error("Unexpected error calling {}: {}", url, e.getMessage(), e);
            throw new RuntimeException("Unexpected error calling external service", e);
        }
    }
}
